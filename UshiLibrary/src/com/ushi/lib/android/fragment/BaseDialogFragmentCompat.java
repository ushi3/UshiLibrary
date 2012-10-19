package com.ushi.lib.android.fragment;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;

/**
 * DialogFragmentのイクナイ部分をゴニョったやつ。
 *
 * @author Ushi
 *
 */
public class BaseDialogFragmentCompat extends DialogFragment {

	private Context mContext;
	private LayoutInflater mInflater;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		mContext = activity.getApplicationContext();
		mInflater = activity.getLayoutInflater();
	}

	/**
	 * ライフサイクルに影響されないContextを返します。<br>
	 * {@link #getActivity()} はライフサイクル次第でnullになるので。<br>
	 * ただし、ApplicationContextなので、Viewの引数にすると、テーマが適用されなくなるかも。
	 *
	 * @return {@link Context}
	 */
	protected Context getContext() {
		return mContext;
	}

	/**
	 * ライフサイクルに依存せずに、{@link LayoutInflater} を返す。
	 *
	 * @return {@link LayoutInflater}
	 */
	protected LayoutInflater getLayoutInflater() {
		return mInflater;
	}

	@Override
	public void dismiss() {
		if (getFragmentManager() != null) {
			// dismissを複数回呼べない部分を調節
			super.dismiss();
		}
	}
}
