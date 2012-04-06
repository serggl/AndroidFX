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

public class UITools {
	
	static int defaultToastGravity = Gravity.CENTER;
	static int defaultToastDuration = Toast.LENGTH_LONG;
	
	public static void showToastMessage(Context ctx, String text){
		Toast toast = Toast.makeText(ctx, text, defaultToastDuration);
    	toast.setGravity(defaultToastGravity, 0, 0);
    	toast.show();
	}
	
	public static void showToastMessage(Context ctx, int textResId){
		showToastMessage(ctx, ctx.getString(textResId));
	}
	
	public static void showToastMessage(Context ctx, String text, int imageId){
		Toast toast = Toast.makeText(ctx, text, defaultToastDuration);
    	toast.setGravity(defaultToastGravity, 0, 0);
    	LinearLayout toastView = (LinearLayout) toast.getView();
    	ImageView imageWorld = new ImageView(ctx);
    	imageWorld.setImageResource(imageId);
    	toastView.addView(imageWorld, 0);
    	toast.show();
	}
	
	public static void showToastMessage(Context ctx, int textResId, int imageId){
		showToastMessage(ctx, ctx.getString(textResId), imageId);
	}
	
	public static void hide(Activity ctx, int viewId) {
		ctx.findViewById(viewId).setVisibility(View.INVISIBLE);
	}
	
	public static void show(Activity ctx, int viewId) {
		ctx.findViewById(viewId).setVisibility(View.VISIBLE);
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
	
	public static void removeFromParentView(Activity ctx, int viewId) {
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
	
	public static CheckBox getCheckBox(Activity ctx, int buttonId) {
		return (CheckBox) ctx.findViewById(buttonId);
	}
	
	public static CheckBox getCheckBox(View ctx, int buttonId) {
		return (CheckBox) ctx.findViewById(buttonId);
	}
	
	public static void setCheckBoxChecked(Activity ctx, int buttonId, boolean checked) {
		getCheckBox(ctx, buttonId).setChecked(checked);
	}
	
	public static CheckedTextView getCheckedTextView(Activity ctx, int buttonId) {
		return (CheckedTextView) ctx.findViewById(buttonId);
	}
	
	public static boolean getCheckedTextViewChecked(Activity ctx, int buttonId) {
		return getCheckedTextView(ctx, buttonId).isChecked();
	}
	
	public static void setCheckedTextViewChecked(Activity ctx, int buttonId, boolean checked) {
		getCheckedTextView(ctx, buttonId).setChecked(checked);
	}

	public static Button getButton(Activity ctx, int buttonId) {
		return (Button) ctx.findViewById(buttonId);
	}

	public static void setOnClickListener(Activity ctx, int buttonId,
			OnClickListener listener) {
		ctx.findViewById(buttonId).setOnClickListener(listener);
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
		return getEditText(ctx, viewId).getText().toString();
	}
	
	public static void setText(Activity ctx, int textViewId, int textId){
		getTextView(ctx, textViewId).setText(textId);
	}
	
	public static void setText(Activity ctx, int textViewId, String text){
		getTextView(ctx, textViewId).setText(text);
	}

	public static SeekBar getSeekBar(Activity ctx, int viewId) {
		return (SeekBar) ctx.findViewById(viewId);
	}
	
	public static RadioButton getRadioButton(Activity ctx, int viewId) {
		return (RadioButton) ctx.findViewById(viewId);
	}
	
	public static void setRadioButtonCheckedChangeListener(Activity ctx, int buttonId,
			OnCheckedChangeListener listener) {
		getRadioButton(ctx, buttonId).setOnCheckedChangeListener(listener);
	}
	
	public static LinearLayout getLinearLayout(Activity ctx, int viewId) {
		return (LinearLayout) ctx.findViewById(viewId);
	}
	
	public static RelativeLayout getRelativeLayout(Activity ctx, int viewId) {
		return (RelativeLayout) ctx.findViewById(viewId);
	}
	
	public static ImageView getImageView(Activity ctx, int viewId) {
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
	
	public static void showAlertDialog(Context ctx, String title, String message, int icon, 
			String positiveButton, String negativeButton, DialogInterface.OnClickListener okClickListener, 
			DialogInterface.OnClickListener cancelClickListener){
		createAlertDialog(ctx, title, message, icon, false, positiveButton, negativeButton, okClickListener, cancelClickListener).show();
    }
	
	public static void showAlertDialog(Context ctx, int title, int message, int icon,
			int positiveButton, int negativeButton, DialogInterface.OnClickListener okClickListener, 
			DialogInterface.OnClickListener cancelClickListener){
    	showAlertDialog(ctx, ctx.getString(title), ctx.getString(message), icon, ctx.getString(positiveButton), 
    			ctx.getString(negativeButton), okClickListener, cancelClickListener);
    }
	
	public static void showAlertDialog(Context ctx, int title, int message, int icon,
			int positiveButton, DialogInterface.OnClickListener okClickListener) {
    	showAlertDialog(ctx, ctx.getString(title), ctx.getString(message), icon, ctx.getString(positiveButton), 
    			null, okClickListener, null);
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
		((InputMethodManager)activity.getSystemService("input_method")).hideSoftInputFromWindow(activity.findViewById(buttonId).getWindowToken(), 0);
	}
}
