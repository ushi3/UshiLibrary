package com.ushi.lib.android.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.view.View;

import com.ushi.lib.android.fragment.BaseDialogHelper.IChildDialog;

/**
 * DialogFragmentのイクナイ部分をゴニョったやつ。
 *
 * @author Ushi
 */
public class BaseDialogFragment extends DialogFragment implements IChildDialog {

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

	@Override
	public Dialog getChildDialog() {
		return getDialog();
	}
}