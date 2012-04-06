package com.anjlab.android.fx;

import android.app.Activity;
import android.content.SharedPreferences;

public abstract class AppRateReminder {
	
	protected int daysBeforePromt;
	protected int eventsBeforePromt;
	protected Activity ctx;
	protected String rateId;
    
    public AppRateReminder(Activity ctx, String rateId, int daysBeforePromt, int eventsBeforePromt) {
    	this.daysBeforePromt = daysBeforePromt;
    	this.eventsBeforePromt = eventsBeforePromt;
    	this.ctx = ctx;
    	this.rateId = rateId;
    }
    
    public boolean incrementEventAndRate() {
        return checkConditionsAndShowDialog(true);
    } 
    
    public boolean rate() {
        return checkConditionsAndShowDialog(false);
    }
    
    private SharedPreferences getPreferences() {
    	return ctx.getSharedPreferences("apprater"+rateId, 0);
    }
    
    private boolean checkConditionsAndShowDialog(boolean incrementUploads) {
        SharedPreferences prefs = getPreferences();
        if (prefs.getBoolean("dontshowagain"+rateId, false)) { return false; }
        
        SharedPreferences.Editor editor = prefs.edit();
        
        // Increment uploads counter
        long launch_count = prefs.getLong("uploads_count"+rateId, 0);
        if (incrementUploads){
        	launch_count++;
        	editor.putLong("uploads_count"+rateId, launch_count);
        }        

        // Get date of first launch
        Long date_firstLaunch = prefs.getLong("date_firstlaunch"+rateId, 0);
        if (date_firstLaunch == 0) {
            date_firstLaunch = System.currentTimeMillis();
            editor.putLong("date_firstlaunch"+rateId, date_firstLaunch);
        }
        
        // Wait at least n days before opening
		if (launch_count >= eventsBeforePromt
				&& (System.currentTimeMillis() >= date_firstLaunch + (daysBeforePromt * 24 * 60 * 60 * 1000))) {
			showRateDialog(true);
			return true;
		}
        
        editor.commit();
        return false;
    }
    
    protected void commitPositiveResult() {
    	SharedPreferences.Editor editor = getPreferences().edit();
    	editor.putBoolean("dontshowagain"+rateId, true);
        editor.commit();
    }
    
    protected void commitNegativeResult() {
    	SharedPreferences.Editor editor = getPreferences().edit();
    	editor.putLong("uploads_count"+rateId, 0);
        editor.commit();
    }
    
    public abstract void showRateDialog(boolean commit);
}