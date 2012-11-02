package com.ushi.lib.android.fragment;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * 共通したSupportFragment
 *
 * @author Ushi
 */
public class BaseFragmentCompat extends Fragment implements IChild {

	private BaseFragmentHelper mHelper = new BaseFragmentHelper(this);

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mHelper.onAttach(activity);
	}

	@Override
	public int getFragmentId() {
		return getId();
	}

	@Override
	public String getFragmentTag() {
		return getTag();
	}

	@Override
	public View getParentView() {
		return getView();
	}

	@Override
	public ViewHelper getViewHelper() {
		return mHelper.getViewHelper();
	}

	@Override
	public Context getApplicationContext() {
		return mHelper.getContext();
	}
}
