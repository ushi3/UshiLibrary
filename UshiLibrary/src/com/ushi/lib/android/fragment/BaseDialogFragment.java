package com.ushi.lib.android.fragment;

import android.app.Activity;
import android.app.DialogFragment;

import com.ushi.lib.android.fragment.BaseDialogHelper.BaseDialog;

/**
 * DialogFragmentのイクナイ部分をゴニョったやつ。
 *
 * @author Ushi
 */
public class BaseDialogFragment extends DialogFragment implements BaseDialog {

	private BaseDialogHelper mHelper = new BaseDialogHelper(this);

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mHelper.onAttach(activity);
	}

	@Override
	public void dismiss() {
		mHelper.dismiss(getFragmentManager());
	}

	@Override
	public void dismissDialog() {
		super.dismiss();
	}

	@Override
	public int getFragmentId() {
		return getId();
	}

	@Override
	public String getFragmentTag() {
		return getTag();
	}
}