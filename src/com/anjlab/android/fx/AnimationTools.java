package com.anjlab.android.fx;

import java.lang.reflect.Method;
import android.app.Activity;

public class AnimationTools extends BaseTools {
	public static void overridePendingTransition(Activity ctx, int enterAnimId, int exitAnimId) {
		try {
		    Method method = Activity.class.getMethod("overridePendingTransition", new Class[]{int.class, int.class});
		    method.invoke(ctx, enterAnimId, exitAnimId);
		} catch (Exception e) {
		    // Can't change animation, so do nothing
		}
	}
}
