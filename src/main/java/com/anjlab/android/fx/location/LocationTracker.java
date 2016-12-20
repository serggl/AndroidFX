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
	int minTime = 0;
	int minDistance = 0;

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
					locationManager.requestLocationUpdates(provider, minTime, minDistance, this);
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

	public Location getLastKnownLocation(String[] preferedProviders) {
		try {
			for(String provider: preferedProviders)
				if (locationManager.isProviderEnabled(provider)){
					Location l = locationManager.getLastKnownLocation(provider);
					if (l != null)
						return l;
				}
		}
		catch (Exception ex) {
		}
		return null;
	}

	public int getMinTime() {
		return minTime;
	}

	public void setMinTime(int value) {
		minTime = value;
	}

	public int getMinDistance() {
		return minDistance;
	}

	public void setMinDistance(int value) {
		minDistance = value;
	}

	public static long distanceInMeters(Location from, Location to) {
		return distanceInMeters(from.getLatitude(), from.getLongitude(), to.getLatitude(), to.getLongitude());
	}

	public static long distanceInMeters(double fromLat, double fromLng, double toLat, double toLng) {
		double earthRadius = 3958.75;
		double dLat = Math.toRadians(toLat - fromLat);
		double dLng = Math.toRadians(toLng - fromLng);
		double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
				Math.cos(Math.toRadians(fromLat)) * Math.cos(Math.toRadians(toLat)) *
				Math.sin(dLng/2) * Math.sin(dLng/2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double dist = earthRadius * c;

		int meterConversion = 1609;

		return Math.round(dist * meterConversion);
	}

}
