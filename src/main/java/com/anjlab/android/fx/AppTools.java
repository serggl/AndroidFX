package com.anjlab.android.fx;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.preference.PreferenceManager;

public class AppTools extends BaseTools {
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
	
	public static float getSettingsValueAsFloat(Context ctx, String prefName, float defaultValue){
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
		return preferences.getFloat(prefName, defaultValue);
	}
	
	private static SharedPreferences.Editor getSettingsEditor(Context ctx){
		return PreferenceManager.getDefaultSharedPreferences(ctx).edit();
	}
	
	public static void setSettingsValue(Context ctx, String prefName, boolean value){
		getSettingsEditor(ctx).putBoolean(prefName, value).commit();
	}
	
	public static void setSettingsValue(Context ctx, String prefName, float value){
		getSettingsEditor(ctx).putFloat(prefName, value).commit();
	}
	
	public static int getSettingsValueAsInt(Context ctx, String prefName, int defaultValue){
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
		return preferences.getInt(prefName, defaultValue);
	}
	
	public static long getSettingsValueAsLong(Context ctx, String prefName, long defaultValue){
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
		return preferences.getLong(prefName, defaultValue);
	}
	
	public static void setSettingsValue(Context ctx, String prefName, int value){
		getSettingsEditor(ctx).putInt(prefName, value).commit();
	}
	
	public static void setSettingsValue(Context ctx, String prefName, long value){
		getSettingsEditor(ctx).putLong(prefName, value).commit();
	}
	
	public static void setSettingsValue(Context ctx, String prefName, String value){
		getSettingsEditor(ctx).putString(prefName, value).commit();
	}
	
	public static void openAndroidMarket(Context ctx){
		ctx.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+ctx.getApplicationContext().getClass().getPackage().getName())));
	}
	
	public static String getAndroidMarketUrl(Activity ctx){
		return "https://market.android.com/details?id="+ctx.getApplicationContext().getClass().getPackage().getName();
	}
	
	public static void openWeb(Context ctx, String url){
		ctx.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
	}
	
	public static int getVersion(Context ctx) {
	    int v = 0;
	    try {
	        v = ctx.getPackageManager().getPackageInfo(ctx.getApplicationContext().getPackageName(), 0).versionCode;
	    } catch (NameNotFoundException e) {
	        // Huh? Really?
	    }
	    return v;
	}
	
	public static String getVersionName(Context ctx) {
	    try {
	        return ctx.getPackageManager().getPackageInfo(ctx.getApplicationContext().getPackageName(), 0).versionName;
	    } catch (NameNotFoundException e) {
	        // Huh? Really?
	    }
	    return "";
	}
}
