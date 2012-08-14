package com.anjlab.android.fx;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.anjlab.android.fx.UITools;

public class ProgressHUD
{
	private static ProgressDialog dialogInstance;

	public static void dismiss()
	{
		if (dialogInstance != null)
		{
			dialogInstance.dismiss();
			dialogInstance = null;
		}
	}

	public static void dismissWithError(Context ctx, String message)
	{
		dismiss();
		//UITools.showToastMessage(ctx, message + "\n", R.drawable.error);
		UITools.showToastMessage(ctx, message);
	}

	public static void dismissWithSuccess(Context ctx, String message)
	{
		dismiss();
		//UITools.showToastMessage(ctx, message + "\n", R.drawable.success);
		UITools.showToastMessage(ctx, message);
	}

	public static void show(Activity ctx, String message)
	{
		try {
			if (dialogInstance != null)
				dialogInstance.dismiss();
			dialogInstance = new ProgressDialog(ctx);
			dialogInstance.setCancelable(false);
			dialogInstance.setMessage(message);
			dialogInstance.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			dialogInstance.show();
		}
		catch (Exception ex) {
			Log.e("HUD", String.format("Unable to show progress dialog: %s", ex.toString()));
		}
	}

	public static void update(Activity ctx, String message)
	{
		try {
			if (dialogInstance == null)
				show(ctx, message);
			else
				dialogInstance.setMessage(message);
		}
		catch (Exception ex) {
			Log.e("HUD", String.format("Unable to update progress dialog: %s", ex.toString()));
		}
	}

	public static void showIfNotVisible(Activity ctx, String message)
	{
		if (dialogInstance == null)
			show(ctx, message);
	}
	
	public static void showWait(Activity ctx)
	{
		show(ctx, "Wait...");
	}
}