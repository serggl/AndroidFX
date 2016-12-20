package com.anjlab.android.fx;

import android.os.AsyncTask;

public abstract class SafeAsyncTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {

	protected abstract Result doSafeInBackground(Params... params);
	
	@Override
	protected Result doInBackground(Params... params) {
		try 
		{
			return doSafeInBackground(params);
		}
		catch (Exception e) {
			onSafeActionfailed(e);
			return null;
		}
	}
	
	protected void onSafePreExecute() { }
	
	@Override
    protected void onPreExecute() {
		try 
		{
			onSafePreExecute();
		}
		catch (Exception e) {
			onSafeActionfailed(e);
		}
    }
    
	protected void onSafePostExecute(Result result) { }
	
    @Override
    protected void onPostExecute(Result result) {
    	try 
		{
    		onSafePostExecute(result);
		}
		catch (Exception e) {
			onSafeActionfailed(e);
		}
    }
    
    protected void onSafeActionfailed(Exception e) { }

}