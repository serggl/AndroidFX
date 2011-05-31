package com.anjlab.android.fx;

import android.app.Activity;
import android.content.Context;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
	
	public static CheckBox getCheckBox(Activity ctx, int buttonId) {
		return (CheckBox) ctx.findViewById(buttonId);
	}

	public static Button getButton(Activity ctx, int buttonId) {
		return (Button) ctx.findViewById(buttonId);
	}

	public static void setButtonClickListener(Activity ctx, int buttonId,
			OnClickListener listener) {
		getButton(ctx, buttonId).setOnClickListener(listener);
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
			OnItemSelectedListener callback) {
		Spinner s = (Spinner) ctx.findViewById(id);
		ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(ctx, arrayId,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		s.setAdapter(adapter);
		if (callback != null)
			s.setOnItemSelectedListener(callback);
	}
	
	public static TextView getTextView(Activity ctx, int viewId) {
		return (TextView) ctx.findViewById(viewId);
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
}