package com.ushi.lib.android.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.view.View;

/**
 * 共通Fragment
 *
 * @author Ushi
 */
public class BaseFragment extends Fragment implements IChild {

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
