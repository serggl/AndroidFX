package com.anjlab.android.fx.location;

import java.util.ArrayList;
import java.util.List;

import android.location.Location;

public class LocationDataStack {
	List<Location> locations = new ArrayList<Location>();
	float requiredAccuracy;
	int maxStackDepth;
	
	public LocationDataStack(float requiredAccuracy, int maxStackDepth) {
		this.requiredAccuracy = requiredAccuracy;
		this.maxStackDepth = maxStackDepth;
	}
	
	public boolean push(Location location) {
		locations.add(location);
		return (location.getAccuracy() > 0 && location.getAccuracy() <= requiredAccuracy) || locations.size() >= maxStackDepth; 
	}
	
	public Location getBest() {
		if (locations.size() == 0)
			return null;
		
		Location loc = locations.get(0);
		for (Location l : locations)
			if (l.getAccuracy() < loc.getAccuracy())
				loc = l;
		return loc;
	}
	
	public void reset() {
		locations.clear();
	}
}
