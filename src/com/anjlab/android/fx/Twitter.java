package com.anjlab.android.fx;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.HTTP;

import com.anjlab.android.fx.AppTools;
import com.anjlab.android.fx.UITools;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.EditText;
import oauth.signpost.OAuth;
import oauth.signpost.OAuthProvider;
import oauth.signpost.basic.DefaultOAuthProvider;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthNotAuthorizedException;

public class Twitter {
	private static final String PREF_TOKEN = "Twitter_OAuth_Token";
	private static final String PREF_TOKEN_SECRET = "Twitter_OAuth_Token_Secret";
	
	private static CommonsHttpOAuthConsumer consumer = new CommonsHttpOAuthConsumer("rNYHdNfKTsixK98INnKAew", "kCTOlAWcOTgcVtVTuYTIZqEthWiyWgHoB9dI1eGLE");  
	  
	private static OAuthProvider provider = new DefaultOAuthProvider(
			"http://api.twitter.com/oauth/request_token", 
			"http://api.twitter.com/oauth/access_token",  
	        "http://twitter.com/oauth/authorize");
	
	private static String callbackUrl = "myapp://tweet";
	private static boolean autorized = false;
	
	private static boolean needToShowDialog = false;
	private static int dialogIconId = -1;
	private static String dialogTitle = "Post your status";
	private static String dialogTweetText = "Tweet!";
	private static String dialogCancelText = "Cancel";
	
	public static void initialize(String callbckUrl, int dlgIconId, String dlgTitle, 
			String dlgOkText, String dlgCancelText){
		callbackUrl = callbckUrl;
		dialogIconId = dlgIconId;
		dialogCancelText = dlgCancelText;
		dialogTitle = dlgTitle;
		dialogTweetText = dlgOkText;
	}
	
	public static boolean authorize(Context ctx){
		if (autorized)
			return true;
		
		String token = AppTools.getSettingsValueAsSting(ctx, PREF_TOKEN, "");
		String tokenSecret = AppTools.getSettingsValueAsSting(ctx, PREF_TOKEN_SECRET, "");
		
		if (token != "" && tokenSecret != "")
		{
			consumer.setTokenWithSecret(token, tokenSecret);
			autorized = true;
			return true;
		}
		
		String authUrl;
		try {
			authUrl = provider.retrieveRequestToken(consumer, callbackUrl);
			ctx.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(authUrl)));
		} catch (Exception e) {
			UITools.showToastMessage(ctx, e.getLocalizedMessage());			
		}
		return false;
	}
	
	public static boolean processAutorizationResponse(Context ctx, Intent intent){
		Uri uri = intent.getData();  
        if (uri != null && uri.toString().startsWith(callbackUrl)) {  
            String verifier = uri.getQueryParameter(OAuth.OAUTH_VERIFIER);  
            try {
				provider.retrieveAccessToken(consumer, verifier);
				AppTools.setSettingsValue(ctx, PREF_TOKEN, consumer.getToken());
				AppTools.setSettingsValue(ctx, PREF_TOKEN_SECRET, consumer.getTokenSecret());
				autorized = true;
				if (needToShowDialog)
					showTweetWindow(ctx);
				return true;
			} catch (Exception e) {
				UITools.showToastMessage(ctx, e.getLocalizedMessage());					
			} 
        }
        return false; 
	}
	
	public static void postStatus(String status) throws Exception {
		if (!autorized)
			return;
		
    	HttpPost post = new HttpPost("http://twitter.com/statuses/update.xml");  
    	final List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
    	// 'status' here is the update value you collect from UI  
    	nvps.add(new BasicNameValuePair("status", status));  
    	post.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));  
    	// set this to avoid 417 error (Expectation Failed)  
    	post.getParams().setBooleanParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, false);  
    	// sign the request  
    	consumer.sign(post);  
    	// send the request  
    	final HttpResponse response = new DefaultHttpClient().execute(post);  
    	// response status should be 200 OK  
    	int statusCode = response.getStatusLine().getStatusCode();  
    	final String reason = response.getStatusLine().getReasonPhrase();  
    	// release connection  
    	response.getEntity().consumeContent();  
    	if (statusCode != 200) {  
    	    Log.e("TwitterConnector", reason);  
    	    throw new OAuthNotAuthorizedException();  
    	}  
    }
	
	public static void showTweetWindow(final Context ctx){
		if (!autorized)
		{
			needToShowDialog = true;
			if (!authorize(ctx))
				return;
		}
    	AlertDialog.Builder alert = new AlertDialog.Builder(ctx);
		alert.setTitle(dialogTitle);
		final EditText input = new EditText(ctx);
		input.setSingleLine(false);
		alert.setView(input);

		alert.setPositiveButton(dialogTweetText,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						try {
							Twitter.postStatus(input.getText().toString());
						} catch (Exception e) {
							UITools.showToastMessage(ctx, e.getMessage());
						}
					}
				});

		alert.setNegativeButton(dialogCancelText,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {					
					}
				});
		alert.setCancelable(true);
		if (dialogIconId != -1)
			alert.setIcon(dialogIconId);
		needToShowDialog = false;
		alert.show();
    }
}
