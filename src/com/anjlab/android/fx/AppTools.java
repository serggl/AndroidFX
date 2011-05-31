package com.anjlab.android.fx;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.preference.PreferenceManager;

public class AppTools {
	public static String getSettingsValueAsSting(Context ctx, String prefName, String defaultValue){
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
		return preferences.getString(prefName, defaultValue);
	}

	public static boolean isSettingsValueDefined(Context ctx, String prefName){
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
		return preferences.contains(prefName);
	}
	
	public static boolean getSettingsValueAsBool(Context ctx, String prefName, boolean defaultValue){
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
		return preferences.getBoolean(prefName, defaultValue);
	}
	
	public static void setSettingsValue(Context ctx, String prefName, boolean value){
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putBoolean(prefName, value);
		editor.commit();
	}
	
	public static void openAndroidMarket(Activity ctx){
		ctx.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+ctx.getClass().getPackage().getName())));
	}
	
	public static void openWeb(Context ctx, String url){
		ctx.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
	}
	
	public static int getVersion(Context ctx) {
	    int v = 0;
	    try {
	        v = ctx.getPackageManager().getPackageInfo(ctx.getClass().getPackage().getName(), 0).versionCode;
	    } catch (NameNotFoundException e) {
	        // Huh? Really?
	    }
	    return v;
	}
}
