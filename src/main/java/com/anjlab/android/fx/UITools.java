package com.anjlab.android.fx;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class UITools extends BaseTools {
	
	static int defaultToastGravity = Gravity.CENTER;
	static int defaultToastDuration = Toast.LENGTH_LONG;
	
	public static boolean showToastMessage(Context ctx, String text){
		try {
			Toast toast = Toast.makeText(ctx, text, defaultToastDuration);
			toast.setGravity(defaultToastGravity, 0, 0);
			toast.show();
			return true;
		} 
		catch (Exception e)
		{
			return false;
		}
	}
	
	public static void showToastMessage(Context ctx, int textResId){
		showToastMessage(ctx, ctx.getString(textResId));
	}
	
	public static boolean showToastMessage(Context ctx, String text, int imageId){
		try {
			Toast toast = Toast.makeText(ctx, text, defaultToastDuration);
			toast.setGravity(defaultToastGravity, 0, 0);
			LinearLayout toastView = (LinearLayout) toast.getView();
			ImageView imageWorld = new ImageView(ctx);
			imageWorld.setImageResource(imageId);
			toastView.addView(imageWorld, 0);
			toast.show();
			return true;
		} 
		catch (Exception e)
		{
			return false;
		}
	}
	
	public static void showToastMessage(Context ctx, int textResId, int imageId){
		showToastMessage(ctx, ctx.getString(textResId), imageId);
	}
	
	public static void hide(Activity ctx, int viewId) {
		View v = ctx.findViewById(viewId);
		if (v != null)
			v.setVisibility(View.INVISIBLE);
	}
	
	public static void hide(View ctx, int viewId) {
		View v = ctx.findViewById(viewId);
		if (v != null)
			v.setVisibility(View.INVISIBLE);
	}
	
	public static void show(Activity ctx, int viewId) {
		View v = ctx.findViewById(viewId);
		if (v != null)
			v.setVisibility(View.VISIBLE);
	}
	
	public static void show(View ctx, int viewId) {
		View v = ctx.findViewById(viewId);
		if (v != null)
			v.setVisibility(View.VISIBLE);
	}
	
	public static void collapse(View view) {
		if (view != null) {
			ViewGroup.LayoutParams lp = view.getLayoutParams();
			lp.height = 0;
			view.setLayoutParams(lp);
		}
	}
	
	public static void collapse(Activity ctx, int viewId) {
		hide(ctx, viewId);
		collapse(ctx.findViewById(viewId));
	}
	
	public static void collapse(View ctx, int viewId) {
		hide(ctx, viewId);
		collapse(ctx.findViewById(viewId));
	}
	
	public static void expand(View view, boolean fillParent) {
		if (view != null) {
			ViewGroup.LayoutParams lp = view.getLayoutParams();
			lp.height = fillParent ? ViewGroup.LayoutParams.FILL_PARENT : ViewGroup.LayoutParams.WRAP_CONTENT;
			view.setLayoutParams(lp);
		}
	}
	
	public static void expand(Activity ctx, int viewId, boolean fillParent) {
		expand(ctx.findViewById(viewId), fillParent);
		show(ctx, viewId);
	}
	
	public static void expand(View ctx, int viewId, boolean fillParent) {
		expand(ctx.findViewById(viewId), fillParent);
		show(ctx, viewId);
	}
	
	public static void removeFromParentView(Activity ctx, int viewId) {
		removeFromParentView(ctx.findViewById(viewId));
	}
	
	public static void removeFromParentView(View ctx, int viewId) {
		removeFromParentView(ctx.findViewById(viewId));
	}
	
	public static void removeFromParentView(View v) {
		if (v != null) {
			ViewParent parent = v.getParent();
			if (parent instanceof ViewGroup)
				((ViewGroup)parent).removeView(v);	
		}
	}
	
	public static boolean hasView(Activity ctx, int viewId) {
		return ctx.findViewById(viewId) != null;
	}
	
	public static void setEnabled(Activity ctx, int viewId, boolean enabled) {
		View v = ctx.findViewById(viewId);
		if (v != null)
			v.setEnabled(enabled);
	}
	
	public static void setVisible(Activity ctx, int viewId, boolean visible) {
		View v = ctx.findViewById(viewId);
		if (v != null)
			v.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
	}
	
	public static ListView getListView(Activity ctx, int buttonId) {
		return (ListView) ctx.findViewById(buttonId);
	}
	
	public static ListView getListView(View ctx, int buttonId) {
		return (ListView) ctx.findViewById(buttonId);
	}
	
	public static CheckBox getCheckBox(Activity ctx, int buttonId) {
		return (CheckBox) ctx.findViewById(buttonId);
	}
	
	public static CheckBox getCheckBox(View ctx, int buttonId) {
		return (CheckBox) ctx.findViewById(buttonId);
	}

	public static void setCheckBoxChecked(Activity ctx, int buttonId, boolean checked) {
		CheckBox cb = getCheckBox(ctx, buttonId);
		if (cb != null)
			cb.setChecked(checked);
	}

	public static void setCheckBoxChecked(View view, int buttonId, boolean checked) {
		CheckBox cb = getCheckBox(view, buttonId);
		if (cb != null)
			cb.setChecked(checked);
	}

    public static CheckedTextView getCheckedTextView(Activity ctx, int buttonId) {
        return (CheckedTextView) ctx.findViewById(buttonId);
    }

    public static CheckedTextView getCheckedTextView(View view, int buttonId) {
        return (CheckedTextView) view.findViewById(buttonId);
    }

    public static boolean getCheckedTextViewChecked(Activity ctx, int buttonId) {
        return getCheckedTextView(ctx, buttonId).isChecked();
    }

    public static boolean getCheckedTextViewChecked(View view, int buttonId) {
        return getCheckedTextView(view, buttonId).isChecked();
    }
	
	public static void setCheckedTextViewChecked(Activity ctx, int buttonId, boolean checked) {
		getCheckedTextView(ctx, buttonId).setChecked(checked);
	}

	public static Button getButton(Activity ctx, int buttonId) {
		return (Button) ctx.findViewById(buttonId);
	}

	public static void setOnClickListener(Activity ctx, int buttonId, OnClickListener listener) {
		View v = ctx.findViewById(buttonId);
		if (v != null)
			v.setOnClickListener(listener);
	}

	public static void setOnClickListener(View ctx, int buttonId, OnClickListener listener) {
		View v = ctx.findViewById(buttonId);
		if (v != null)
			v.setOnClickListener(listener);
	}

	public static void setOnClickListener(Activity ctx, int buttonId) {
		if (ctx instanceof OnClickListener)
			setOnClickListener(ctx, buttonId, (OnClickListener)ctx);
	}
	
	public static Preference getPreference(PreferenceActivity ctx, CharSequence key) {
		return (Preference) ctx.findPreference(key);
	}
	
	public static void setPreferenceClickListener(PreferenceActivity ctx, CharSequence key,
			OnPreferenceClickListener listener) {
		getPreference(ctx, key).setOnPreferenceClickListener(listener);
	}

	public static Spinner getSpinner(Activity ctx, int spinnerId) {
		return (Spinner) ctx.findViewById(spinnerId);
	}

	public static Spinner getSpinner(View ctx, int spinnerId) {
		return (Spinner) ctx.findViewById(spinnerId);
	}

	public static void fillSpinner(Activity ctx, int id, int arrayId, 
			int listItemResId, OnItemSelectedListener callback) {
		Spinner s = (Spinner) ctx.findViewById(id);
		ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(ctx, arrayId,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(listItemResId);
		s.setAdapter(adapter);
		if (callback != null)
			s.setOnItemSelectedListener(callback);
	}
	
	public static void fillSpinner(Activity ctx, int id, int arrayId,
			OnItemSelectedListener callback) {
		fillSpinner(ctx, id, arrayId, android.R.layout.simple_spinner_dropdown_item, callback);
	}
	
	public static TextView getTextView(Activity ctx, int viewId) {
		return (TextView) ctx.findViewById(viewId);
	}
	
	public static TextView getTextView(View ctx, int viewId) {
		return (TextView) ctx.findViewById(viewId);
	}
	
	public static EditText getEditText(View ctx, int viewId) {
		return (EditText) ctx.findViewById(viewId);
	}
	
	public static EditText getEditText(Activity ctx, int viewId) {
		return (EditText) ctx.findViewById(viewId);
	}
	
	public static String getEditTextText(Activity ctx, int viewId) {
		return getText(ctx, viewId);
	}
	
	public static String getText(Activity ctx, int viewId) {
		return getTextView(ctx, viewId).getText().toString();
	}
	
	public static String getText(View ctx, int viewId) {
		return getTextView(ctx, viewId).getText().toString();
	}
	
	public static void setText(Activity ctx, int textViewId, int textId){
		TextView v = getTextView(ctx, textViewId);
		if (v != null)
			v.setText(textId);
	}
	
	public static void setText(View ctx, int textViewId, int textId){
		TextView v = getTextView(ctx, textViewId);
		if (v != null)
			v.setText(textId);
	}
	
	public static void setText(Activity ctx, int textViewId, String text){
		TextView v = getTextView(ctx, textViewId);
		if (v != null)
			v.setText(text);
	}
	
	public static void setText(View ctx, int textViewId, String text){
		TextView v = getTextView(ctx, textViewId);
		if (v != null)
			v.setText(text);
	}

	public static SeekBar getSeekBar(Activity ctx, int viewId) {
		return (SeekBar) ctx.findViewById(viewId);
	}
	
	public static RadioButton getRadioButton(Activity ctx, int viewId) {
		return (RadioButton) ctx.findViewById(viewId);
	}
	
	public static RadioButton getRadioButton(View ctx, int viewId) {
		return (RadioButton) ctx.findViewById(viewId);
	}
	
	public static void setRadioButtonCheckedChangeListener(Activity ctx, int buttonId,
			OnCheckedChangeListener listener) {
		getRadioButton(ctx, buttonId).setOnCheckedChangeListener(listener);
	}
	
	public static LinearLayout getLinearLayout(Activity ctx, int viewId) {
		return (LinearLayout) ctx.findViewById(viewId);
	}
	
	public static LinearLayout getLinearLayout(View ctx, int viewId) {
		return (LinearLayout) ctx.findViewById(viewId);
	}
	
	public static RelativeLayout getRelativeLayout(Activity ctx, int viewId) {
		return (RelativeLayout) ctx.findViewById(viewId);
	}
	
	public static ImageView getImageView(Activity ctx, int viewId) {
		return (ImageView) ctx.findViewById(viewId);
	}
	
	public static ImageView getImageView(View ctx, int viewId) {
		return (ImageView) ctx.findViewById(viewId);
	}
	
	public static ToggleButton getToggleButton(Activity ctx, int buttonId) {
		return (ToggleButton) ctx.findViewById(buttonId);
	}
	
	public static boolean getToggleButtonState(Activity ctx, int buttonId) {
		return getToggleButton(ctx, buttonId).isChecked();
	}
	
	public static void setToggleButtonState(Activity ctx, int buttonId, boolean checked) {
		getToggleButton(ctx, buttonId).setChecked(checked);
	}
	
	public static AlertDialog createAlertDialog(Context ctx, String title, String message, int icon, 
			boolean cancelable, String positiveButton, String negativeButton, DialogInterface.OnClickListener okClickListener, 
			DialogInterface.OnClickListener cancelClickListener){
    	AlertDialog.Builder alertDialog = new AlertDialog.Builder(ctx);
    	alertDialog.setTitle(title);
    	alertDialog.setMessage(message);
    	alertDialog.setCancelable(cancelable);
    	if (icon != 0)
    		alertDialog.setIcon(icon);
    	alertDialog.setPositiveButton(positiveButton, okClickListener);
    	if (negativeButton != null)
    		alertDialog.setNegativeButton(negativeButton, cancelClickListener);
    	return alertDialog.create();
    }
	
	public static AlertDialog createAlertDialog(Context ctx, String title, View messageView, int icon, 
			boolean cancelable, String positiveButton, String negativeButton, DialogInterface.OnClickListener okClickListener, 
			DialogInterface.OnClickListener cancelClickListener){
    	AlertDialog.Builder alertDialog = new AlertDialog.Builder(ctx);
    	alertDialog.setTitle(title);
    	alertDialog.setView(messageView);
    	alertDialog.setCancelable(cancelable);
    	if (icon != 0)
    		alertDialog.setIcon(icon);
    	alertDialog.setPositiveButton(positiveButton, okClickListener);
    	if (negativeButton != null)
    		alertDialog.setNegativeButton(negativeButton, cancelClickListener);
    	return alertDialog.create();
    }
	
	public static boolean showAlertDialog(Context ctx, String title, View view, int icon, 
			String positiveButton, String negativeButton, DialogInterface.OnClickListener okClickListener, 
			DialogInterface.OnClickListener cancelClickListener, boolean cancelable){
		try {
			createAlertDialog(ctx, title, view, icon, cancelable, positiveButton, negativeButton, okClickListener, cancelClickListener).show();
		} 
		catch (Exception e) {
			return false;
		}
		return true;
    }
	
	public static boolean showAlertDialog(Context ctx, String title, String message, int icon, 
			String positiveButton, String negativeButton, DialogInterface.OnClickListener okClickListener, 
			DialogInterface.OnClickListener cancelClickListener, boolean cancelable){
		try {
			createAlertDialog(ctx, title, message, icon, cancelable, positiveButton, negativeButton, okClickListener, cancelClickListener).show();
		} 
		catch (Exception e) {
			return false;
		}
		return true;
    }
	
	public static boolean showAlertDialog(Context ctx, int title, int message, int icon,
			int positiveButton, int negativeButton, DialogInterface.OnClickListener okClickListener, 
			DialogInterface.OnClickListener cancelClickListener, boolean cancelable){
		try {
			showAlertDialog(ctx, ctx.getString(title), ctx.getString(message), icon, ctx.getString(positiveButton), 
				ctx.getString(negativeButton), okClickListener, cancelClickListener, cancelable);
		} 
		catch (Exception e) {
			return false;
		}
		return true;
    }
	
	public static boolean showAlertDialog(Context ctx, int title, int message, int icon,
			int positiveButton, DialogInterface.OnClickListener okClickListener, boolean cancelable) {
		try {
			showAlertDialog(ctx, ctx.getString(title), ctx.getString(message), icon, ctx.getString(positiveButton), 
					null, okClickListener, null, cancelable);
		} 
		catch (Exception e) {
			return false;
		}
		return true;
    }
	
	public static void applyCustomRegularBoldFont(Context ctx, ViewGroup rootView, String regularFontPath, String boldFontPath) {
		Typeface regular = Typeface.createFromAsset(ctx.getAssets(), regularFontPath);
		Typeface bold = Typeface.createFromAsset(ctx.getAssets(), boldFontPath);

		for(int i = 0; i <rootView.getChildCount(); i++) {
			View v = rootView.getChildAt(i);
			if(v instanceof TextView ) {
				Typeface t = ((TextView)v).getTypeface();
				if (t != null)
					((TextView)v).setTypeface(t.isBold() ? bold : regular);
			} else if(v instanceof Button) {
				Typeface t = ((Button)v).getTypeface();
				((Button)v).setTypeface(t.isBold() ? bold : regular);
			} else if(v instanceof EditText) {
				Typeface t = ((EditText)v).getTypeface();
				((EditText)v).setTypeface(t.isBold() ? bold : regular);
			} else if(v instanceof ViewGroup) {
				applyCustomRegularBoldFont(ctx, (ViewGroup)v, regularFontPath, boldFontPath);
			}
		}
	}
	
	public static void hideKeyboard(Activity activity, int buttonId)
	{
		hideKeyboard(activity, activity.findViewById(buttonId));
	}
	
	public static void hideKeyboard(Context ctx, View view)
	{
		((InputMethodManager)ctx.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(), 0);
	}
	
	public static void showKeyboard(Activity activity, int buttonId)
	{
		showKeyboard(activity, activity.findViewById(buttonId));
	}
	
	public static void showKeyboard(final Context activity, final View view)
	{
		view.postDelayed(new Runnable() {
            @Override
            public void run() {
            	try {
            		view.requestFocus();
            		InputMethodManager keyboard = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            		keyboard.showSoftInput(view, 0);
            	}
            	catch (Exception ex) {
            		LogError(ex.toString());
            	}
            }
        }, 200);
	}
}
