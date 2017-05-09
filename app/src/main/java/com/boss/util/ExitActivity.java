package com.boss.util;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

public class ExitActivity {
	public static List<Activity> activityList = new ArrayList<Activity>();

	// finish所有list中的activity
	public static void exit() {
		int siz = activityList.size();
		for (int i = 0; i < siz; i++) {
			if (activityList.get(i) != null) {
				((Activity) activityList.get(i)).finish();
			}
		}
	}
}
