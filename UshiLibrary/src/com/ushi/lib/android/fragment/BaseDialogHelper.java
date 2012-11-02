package com.ushi.lib.android.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.view.LayoutInflater;

import com.ushi.lib.android.fragment.BaseDialogHelper.IChildDialog;

public class BaseDialogHelper extends BaseHelper<IChildDialog> {

	private Context mContext;
	private LayoutInflater mInflater;

	public BaseDialogHelper(IChildDialog child) {
		super(child);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		mContext = activity.getApplicationContext();
		mInflater = activity.getLayoutInflater();
	}

	/**
	 * Contextを返します。<br> {@link #getActivity()} はライフサイクル次第でnullになるので。<br>
	 * ただし、ApplicationContextなので、Viewの引数にすると、テーマが適用されなくなるかも。
	 *
	 * @return {@link Context}
	 */
	public Context getContext() {
		return mContext;
	}

	/**
	 * {@link LayoutInflater} を返します。
	 *
	 * @return {@link LayoutInflater}
	 */
	public LayoutInflater getLayoutInflater() {
		return mInflater;
	}

	/**
	 * ダイアログを閉じます。
	 *
	 * @param fragmentManager {@link Fragment#getFragmentManager()}
	 */
	public void dismiss(Object fragmentManager) {
		if (fragmentManager != null) {
			getChild().dismissDialog();
		}
	}

	/**
	 * Fragmentを作成する場合に実装するインターフェース。
	 *
	 * @author Ushi
	 */
	public static interface IChildDialog extends IChild {
		/**
		 * 親クラスの {@link DialogFragment#dismiss()} を呼び出してください。
		 */
		public void dismissDialog();

		/**
		 * ダイアログを返してください。
		 */
		public Dialog getChildDialog();
	}

}
