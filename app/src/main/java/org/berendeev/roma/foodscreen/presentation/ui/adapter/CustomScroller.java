package org.berendeev.roma.foodscreen.presentation.ui.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import java.lang.reflect.Field;

/**
 * Android Studio
 *
 * @author Roman Berendeev (roman.berendeev@rentateam.ru)
 */
public class CustomScroller extends Scroller{

	private int mDuration = 400;

	public CustomScroller(Context context) {
		super(context);
	}

	public CustomScroller(Context context, Interpolator interpolator) {
		super(context, interpolator);
	}

	public CustomScroller(Context context, Interpolator interpolator, boolean flywheel) {
		super(context, interpolator, flywheel);
	}

	@Override
	public void startScroll(int startX, int startY, int dx, int dy, int duration) {
		// Ignore received duration, use fixed one instead
		super.startScroll(startX, startY, dx, dy, mDuration);
	}

	@Override
	public void startScroll(int startX, int startY, int dx, int dy) {
		// Ignore received duration, use fixed one instead
		super.startScroll(startX, startY, dx, dy, mDuration);
	}

	public int getmDuration() {
		return mDuration;
	}

	public void setmDuration(int mDuration) {
		this.mDuration = mDuration;
	}

	private static final Interpolator sInterpolator = new Interpolator() {
		@Override
		public float getInterpolation(float t) {
			t -= 1.0f;
			return t * t * t * t * t + 1.0f;
		}
	};

	public static void setupPager(ViewPager mPager){
		try {
			Field mScroller;
			mScroller = ViewPager.class.getDeclaredField("mScroller");
			mScroller.setAccessible(true);
			CustomScroller scroller = new CustomScroller(mPager.getContext(), sInterpolator);
			// scroller.setFixedDuration(5000);
			mScroller.set(mPager, scroller);
		} catch (NoSuchFieldException e) {
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		}
	}
}
