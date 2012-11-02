package com.ushi.lib.android.fragment;

import android.app.Activity;
import android.content.Context;


/**
 * コールバックで、必要な処理があったら考えるか…
 *
 * @author Ushi
 *
 */
public abstract class BaseHelper<C extends IChild> {

	private C mChild;

	private Context mContext;

	private ViewHelper mViewHelper = new ViewHelper(this);

	protected BaseHelper(C child) {
		mChild = child;
	}

	public void onAttach(Activity activity) {
		mContext = activity.getApplicationContext();
	}

	protected C getChild() {
		return mChild;
	}

	protected Context getContext() {
		return mContext;
	}

	protected ViewHelper getViewHelper() {
		return mViewHelper;
	}
}