package com.anjlab.android.fx.location;

import android.location.Location;

public interface LocationTrackerListener {
	void onGpsTimeout(LocationTracker tracker);
	void onLocationListeningStarted(LocationTracker tracker);
	void onLocationListeningStoped(LocationTracker tracker);
	void onLocationChanged(LocationTracker tracker, Location location);
}
