package com.haikuowuya.sport.helper;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import com.haikuowuya.sport.view.SwipeBackFrameLayout;

import java.lang.reflect.Method;

/**
 * @author Yrom
 */
public class SwipeBackActivityHelper
{
	private Activity mActivity;

	private SwipeBackFrameLayout mSwipeBackLayout;

	public SwipeBackActivityHelper(Activity activity)
	{
		mActivity = activity;
	}
	@SuppressWarnings("deprecation")
	public void onActivityCreate()
	{
		mActivity.getWindow().setBackgroundDrawable(new ColorDrawable(0));
		mActivity.getWindow().getDecorView().setBackgroundDrawable(null);
		mSwipeBackLayout = new SwipeBackFrameLayout(mActivity);
		mSwipeBackLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		mSwipeBackLayout.addSwipeListener(new SwipeBackFrameLayout.SwipeListener()
		{
			@Override
			public void onScrollStateChange(int state, float scrollPercent)
			{
				if (state == SwipeBackFrameLayout.STATE_IDLE && scrollPercent == 0)
				{
					convertActivityFromTranslucent();
				}
			}

			@Override
			public void onEdgeTouch(int edgeFlag)
			{
				convertActivityToTranslucent();
			}

			@Override
			public void onScrollOverThreshold()
			{

			}
		});
	}

	public void onPostCreate()
	{
		mSwipeBackLayout.attachToActivity(mActivity);
		convertActivityFromTranslucent();
	}

	public View findViewById(int id)
	{
		if (mSwipeBackLayout != null)
		{
			return mSwipeBackLayout.findViewById(id);
		}
		return null;
	}

	public SwipeBackFrameLayout getSwipeBackLayout()
	{
		return mSwipeBackLayout;
	}

	/**
	 * Convert a translucent themed Activity
	 * {@link android.R.attr#windowIsTranslucent} to a fullscreen opaque
	 * Activity.
	 * <p>
	 * Call this whenever the background of a translucent Activity has changed
	 * to become opaque. Doing so will allow the {@link android.view.Surface} of
	 * the Activity behind to be released.
	 * <p>
	 * This call has no effect on non-translucent activities or on activities
	 * with the {@link android.R.attr#windowIsFloating} attribute.
	 */
	public void convertActivityFromTranslucent()
	{
		try
		{
			Method method = Activity.class.getDeclaredMethod("convertFromTranslucent", new Class[0]);
			method.setAccessible(true);
			method.invoke(mActivity, new Object[]{});
		}
		catch (Throwable t)
		{}
	}

	/**
	 * Convert a translucent themed Activity
	 * {@link android.R.attr#windowIsTranslucent} back from opaque to
	 * translucent following a call to {@link #convertActivityFromTranslucent()}
	 * .
	 * <p>
	 * Calling this allows the Activity behind this one to be seen again. Once
	 * all such Activities have been redrawn
	 * <p>
	 * This call has no effect on non-translucent activities or on activities
	 * with the {@link android.R.attr#windowIsFloating} attribute.
	 */
	public void convertActivityToTranslucent()
	{
		try
		{
			Class<?>[] classes = Activity.class.getDeclaredClasses();
			Class<?> translucentConversionListenerClazz = null;
			for (Class clazz : classes)
			{
				if (clazz.getSimpleName().contains("TranslucentConversionListener"))
				{
					translucentConversionListenerClazz = clazz;
				}
			}
			Method method = Activity.class.getDeclaredMethod("convertToTranslucent", translucentConversionListenerClazz);
			method.setAccessible(true);
			method.invoke(mActivity, new Object[] { null });
		}
		catch (Throwable t)
		{}
	}
}
