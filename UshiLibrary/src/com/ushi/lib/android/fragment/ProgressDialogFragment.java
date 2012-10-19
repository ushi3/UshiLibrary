package com.ushi.lib.android.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * プログレスダイアログを表示するためのFragment。<br>
 * このバージョンは、標準APIのFragmentに対応しています。
 *
 * @author Ushi
 */
public class ProgressDialogFragment extends DialogFragment {

	private static final int MSG_SET_MAX = 1;

	private DialogConfig mConfig;

	private Handler mHandler = new Handler(Looper.getMainLooper()) {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {

			case MSG_SET_MAX:
			{
				ProgressDialog dialog = (ProgressDialog) getDialog();

				if (dialog != null) {
					dialog.setMax(msg.arg1);
				}
				break;
			}

			default:
				break;

			}
		}
	};

	public ProgressDialogFragment(DialogConfig config) {
		mConfig = config != null ? config : new DialogConfig();
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		ProgressDialog dialog = new ProgressDialog(getActivity());

		dialog.setTitle(mConfig.title);

		if (mConfig.isIndeterminate) {
			// 不定
			dialog.setIndeterminate(true);
			dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

		} else {
			dialog.setIndeterminate(false);
			dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		}

		dialog.setMessage(mConfig.message);

		if (mConfig.posButtonName != null) {
			dialog.setButton(DialogInterface.BUTTON_POSITIVE, mConfig.posButtonName, mConfig.posButtonListener);
		}

		if (mConfig.negButtonName != null) {
			dialog.setButton(DialogInterface.BUTTON_NEGATIVE, mConfig.negButtonName, mConfig.negButtonListener);
		}

		if (mConfig.neuButtonName != null) {
			dialog.setButton(DialogInterface.BUTTON_NEUTRAL, mConfig.neuButtonName, mConfig.neuButtonListener);
		}

		dialog.setCancelable(mConfig.isCancelable);
		setCancelable(mConfig.isCancelable);
		dialog.setCanceledOnTouchOutside(mConfig.isCanceledOnTouchOutside);

		return dialog;
	}
	
	@Override
	public void dismiss() {
		if (getFragmentManager() != null) {
			// 重複したdismissの抑制
			super.dismiss();
		}
	}

	@Override
	public void onDismiss(DialogInterface dialog) {
		super.onDismiss(dialog);

		if (mConfig.dismissListener != null) {
			mConfig.dismissListener.onDismiss(dialog);
		}
	}

	@Override
	public void onCancel(DialogInterface dialog) {
		super.onCancel(dialog);

		if (mConfig.cancelListener != null) {
			mConfig.cancelListener.onCancel(dialog);
		}
	}

	/**
	 * ProgressDialogFragmentの進捗表示を更新します。<br>
	 * このメソッドはUIスレッド以外で呼んでも差し支えありません。
	 *
	 * @param value
	 */
	public void setProgress(int value) {
		// onCreateDialogで作ったダイアログがProgressDialogなので、大丈夫だと思う。
		ProgressDialog dialog = (ProgressDialog) getDialog();

		if (dialog != null) {
			dialog.setProgress(value);
		}

	}

	/**
	 * ProgressDialogFragmentの最大値を設定します。
	 * このメソッドはUIスレッド以外で呼んでも差し支えありません。
	 *
	 * @param value
	 */
	public void setMax(int value) {
		if (isUIThread()) {
			ProgressDialog dialog = (ProgressDialog) getDialog();

			if (dialog != null) {
				dialog.setMax(value);
			}

		} else {
			Message msg = mHandler.obtainMessage(MSG_SET_MAX);
			msg.arg1 = value;
			msg.sendToTarget();
		}
	}

	/**
	 * メソッドを呼び出したスレッドがUIスレッドであるかを判定します。
	 *
	 * @return UIスレッドの場合true、それ以外ならfalse
	 */
	public static boolean isUIThread() {
		return Looper.getMainLooper().getThread().equals(Thread.currentThread());
	}

	/**
	 * ダイアログの設定
	 *
	 * @author Ushi
	 */
	public static class DialogConfig {
		/** ダイアログのタイトル */
		public String title = null;
		/** ダイアログのメッセージ */
		public String message = null;

		/** 不定かどうか */
		public boolean isIndeterminate = true;
		/** スタイル */
		public int style = ProgressDialog.STYLE_SPINNER;

		/** 肯定ボタンの名前 */
		public String posButtonName = null;
		/** 肯定ボタンのリスナー */
		public DialogInterface.OnClickListener posButtonListener;

		/** 否定ボタンの名前 */
		public String negButtonName = null;
		/** 否定ボタンのリスナー */
		public DialogInterface.OnClickListener negButtonListener;

		/** 中立ボタンの名前 */
		public String neuButtonName = null;
		/** 中立ボタンのリスナー */
		public DialogInterface.OnClickListener neuButtonListener;

		/** バックキーキャンセルが可能かどうか */
		public boolean isCancelable = true;
		/** ダイアログ外をタッチしたらキャンセルとなるかどうか */
		public boolean isCanceledOnTouchOutside = true;

		/** ダイアログが閉じたときのリスナー */
		public DialogInterface.OnDismissListener dismissListener;
		/** ダイアログをキャンセルされたときのリスナー */
		public DialogInterface.OnCancelListener cancelListener;
	}


	/**
	 * ProgressDialogのコンフィグを設定して生成できるクラス
	 *
	 * @author Ushi
	 */
	public static class Builder {

		private final DialogConfig mConfig = new DialogConfig();

		private Context mContext;

		public Builder(Context context) {
			if (context == null) {
				throw new IllegalArgumentException("arg context should not be null");
			}

			mContext = context;
		}

		/**
		 * 現在までの設定に基づいた、新しいProgressDialogFragmentインスタンスを生成します。
		 *
		 * @return {@link ProgressDialogFragment}
		 */
		public ProgressDialogFragment create() {
			return new ProgressDialogFragment(mConfig);
		}

		/**
		 * ダイアログのタイトルを設定します。
		 *
		 * @param title タイトル
		 * @return このインスタンス
		 */
		public Builder setTitle(CharSequence title) {
			mConfig.title = title != null ? title.toString() : null;

			return this;
		}

		/**
		 * ダイアログのタイトルを設定します。
		 *
		 * @param resId タイトルのリソースID
		 * @return このインスタンス
		 */
		public Builder setTitle(int resId) {
			return setTitle(mContext.getString(resId));
		}

		/**
		 * ダイアログに表示するメッセージを設定します。
		 *
		 * @param message メッセージ
		 * @return このインスタンス
		 */
		public Builder setMessage(CharSequence message) {
			mConfig.message = message != null ? message.toString() : null;

			return this;
		}

		/**
		 * ダイアログに表示するメッセージを設定します。
		 *
		 * @param resId メッセージのリソースID
		 * @return このインスタンス
		 */
		public Builder setMessage(int resId) {
			return setMessage(mContext.getString(resId));
		}

		/**
		 * ダイアログの進捗を不定かどうか設定します。
		 *
		 * @param isIndeterminate 進捗が不定ならtrue、そうでなければfalse
		 * @return このインスタンス
		 */
		public Builder setIndeterminate(boolean isIndeterminate) {
			mConfig.isIndeterminate = isIndeterminate;

			return this;
		}

		/**
		 * ダイアログが閉じられたときのリスナーを設定します。
		 *
		 * @param listener
		 * @return このインスタンス
		 */
		public Builder setOnDismissListener(DialogInterface.OnDismissListener listener) {
			mConfig.dismissListener = listener;

			return this;
		}

		/**
		 * ダイアログがキャンセルされたときのリスナーを設定します。
		 *
		 * @param listener
		 * @return このインスタンス
		 */
		public Builder setOnCancelListener(DialogInterface.OnCancelListener listener) {
			mConfig.cancelListener = listener;

			return this;
		}

		/**
		 * 肯定ボタンを設定します。
		 *
		 * @param name ボタンに表示する名前
		 * @param listener
		 * @return このインスタンス
		 */
		public Builder setPositiveButton(String name, DialogInterface.OnClickListener listener) {
			mConfig.posButtonName = name;
			mConfig.posButtonListener = listener;

			return this;
		}

		/**
		 * 肯定ボタンを設定します。
		 *
		 * @param resId このボタンに表示する名前のリソースID
		 * @param listener
		 * @return このインスタンス
		 */
		public Builder setPositiveButton(int resId, DialogInterface.OnClickListener listener) {
			return setPositiveButton(mContext.getString(resId), listener);
		}

		/**
		 * 否定ボタンを設定します。
		 *
		 * @param name このボタンに表示する名前
		 * @param listener
		 * @return このインスタンス
		 */
		public Builder setNegativeButton(String name, DialogInterface.OnClickListener listener) {
			mConfig.negButtonName = name;
			mConfig.negButtonListener = listener;

			return this;
		}

		/**
		 * 否定ボタンを設定します。
		 *
		 * @param resId このボタンに表示する名前のリソースID
		 * @param listener
		 * @return このインスタンス
		 */
		public Builder setNegativeButton(int resId, DialogInterface.OnClickListener listener) {
			return setNegativeButton(mContext.getString(resId), listener);
		}

		/**
		 * 中立ボタンを設定します。
		 *
		 * @param name このボタンに表示する名前
		 * @param listener
		 * @return このインスタンス
		 */
		public Builder setNeutralButton(String name, DialogInterface.OnClickListener listener) {
			mConfig.neuButtonName = name;
			mConfig.neuButtonListener = listener;

			return this;
		}

		/**
		 * 中立ボタンを設定します。
		 *
		 * @param name このボタンに表示する名前のリソースID
		 * @param listener
		 * @return このインスタンス
		 */
		public Builder setNeutralButton(int resId, DialogInterface.OnClickListener listener) {
			return setNeutralButton(mContext.getString(resId), listener);
		}

		/**
		 * ダイアログがバックキーキャンセル可能であるかを設定します。
		 *
		 * @param isCancelable
		 * @return このインスタンス
		 */
		public Builder setCancelable(boolean isCancelable) {
			mConfig.isCancelable = isCancelable;

			return this;
		}

		/**
		 * ダイアログの外をタッチした場合に、キャンセルとなるかを設定します。
		 *
		 * @param isCanceledOnTouchOutside
		 * @return このインスタンス
		 */
		public Builder setCanceledOnTouchOutside(boolean isCanceledOnTouchOutside) {
			mConfig.isCanceledOnTouchOutside = isCanceledOnTouchOutside;

			return this;
		}
	}

}
