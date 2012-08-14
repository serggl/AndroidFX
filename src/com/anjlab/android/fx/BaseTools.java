package com.anjlab.android.fx;

import android.util.Log;

public class BaseTools {
	
	protected static final String LOG_TAG = "anjlab.fx";
	
	protected static void LogError(String msg) {
		Log.e(LOG_TAG, msg);
	}
	
	protected static void LogDebug(String msg) {
		Log.d(LOG_TAG, msg);
	}
	
	protected static void LogInfo(String msg) {
		Log.i(LOG_TAG, msg);
	}

}
