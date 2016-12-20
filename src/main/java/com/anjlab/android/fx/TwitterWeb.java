package com.anjlab.android.fx;

import android.content.Context;

public class TwitterWeb {
	
	public static void post(Context ctx, String text, String url, String hashTags) {
		String uri = "http://twitter.com/intent/tweet?text=" + text;
		if (url != null && url != "")
			uri += "&url=" + url;
		if (hashTags != null && hashTags != "")
			uri += "&hashtags=" + hashTags;
		AppTools.openWeb(ctx, uri);
	}
}
