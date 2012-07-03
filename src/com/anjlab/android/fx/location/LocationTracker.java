package com.anjlab.android.fx.location;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;

public class LocationTracker implements android.location.LocationListener {
	
	LocationManager locationManager;
	int timeout = 60000;
	LocationTrackerListener listener;
	
	Runnable gpsTimeoutRunnable = new Runnable() {
		@Override
		public void run() {
			stop();
			if (listener != null)
				listener.onGpsTimeout(LocationTracker.this);
		}
	};
	Handler gpsTimeoutHandler = new Handler();
	
	public LocationTracker(Context ctx, int timeout) {
		locationManager = (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);
		this.timeout = timeout;
	}
	
	public void stop() {
		gpsTimeoutHandler.removeCallbacks(gpsTimeoutRunnable);
		locationManager.removeUpdates(this);
		if (listener != null)
			listener.onLocationListeningStoped(this);
	}
	
	public boolean start(String[] preferedProviders) {
		try {
			for(String provider: preferedProviders)
			if (locationManager.isProviderEnabled(provider)){
				locationManager.requestLocationUpdates(provider, 0, 0, this);
				if (listener != null)
					listener.onLocationListeningStarted(this);
				gpsTimeoutHandler.postDelayed(gpsTimeoutRunnable, timeout);
				return true;
			}
		}
		catch (Exception ex) {
		}
		return false;
	}
	
	public void setDelegate(LocationTrackerListener delegate) {
		this.listener = delegate;
	}

	@Override
	public void onLocationChanged(Location location) {
		if (listener != null)
			listener.onLocationChanged(this, location);
	}

	@Override
	public void onProviderDisabled(String provider) {
	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

}
