package com.ushi.lib.android.fragment;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

/**
 * 便利メソッドをまとめただけ。
 *
 * @author Ushi
 */
public class BaseFragmentCompat extends Fragment {

	private Context mContext;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		mContext = activity.getApplicationContext();
	}

	protected Context getApplicationContext() {
		return mContext;
	}

	/**
	 * 指定したViewのテキストを設定します。
	 *
	 * @param view
	 *            検索対象のView
	 * @param resId
	 *            リソースID
	 * @param text
	 *            テキスト
	 */
	protected void setTextToView(View view, int resId, CharSequence text) {
		if (view == null) {
			view = getView();
		}

		TextView textView = (TextView) view.findViewById(resId);
		textView.setText(text);
	}

	protected void setTextToView(int resId, CharSequence text) {
		setTextToView(null, resId, text);
	}

	/**
	 * 指定したViewに設定されているテキストを取得します。
	 *
	 * @param view
	 *            検索対象のView
	 * @param resId
	 *            リソースID
	 * @return 設定されているテキスト
	 */
	protected String getTextByView(View view, int resId) {
		if (view == null) {
			view = getView();
		}

		TextView textView = (TextView) view.findViewById(resId);
		return textView.getText().toString();
	}

	protected String getTextByView(int resId) {
		return getTextByView(null, resId);
	}

	/**
	 * 指定したViewにクリックリスナーをセットします。
	 *
	 * @param view
	 *            検索対象のView
	 * @param resId
	 *            リソースID
	 * @param listener
	 *            セットするリスナー
	 */
	protected void setOnClickListener(View view, int resId, OnClickListener listener) {
		if (view == null) {
			view = getView();
		}

		view.findViewById(resId).setOnClickListener(listener);
	}

	protected void setOnClickListener(int resId, OnClickListener listener) {
		setOnClickListener(null, resId, listener);
	}

	/**
	 * 指定したViewにロングクリックリスナーをセットします。
	 *
	 * @param view
	 *            検索対象のView
	 * @param resId
	 *            リソースID
	 * @param listener
	 *            セットするリスナー
	 */
	protected void setOnLongClickListener(View view, int resId, OnLongClickListener listener) {
		if (view == null) {
			view = getView();
		}

		view.findViewById(resId).setOnLongClickListener(listener);
	}

	protected void setOnLongClickListener(int resId, OnLongClickListener listener) {
		setOnLongClickListener(null, resId, listener);
	}

	/**
	 * 指定したViewにタッチリスナーをセットします。
	 *
	 * @param view
	 *            検索対象のView
	 * @param resId
	 *            リソースID
	 * @param listener
	 *            セットするリスナー
	 */
	protected void setOnTouchListener(View view, int resId, OnTouchListener listener) {
		if (view == null) {
			view = getView();
		}

		view.findViewById(resId).setOnTouchListener(listener);
	}

	protected void setOnTouchListener(int resId, OnTouchListener listener) {
		setOnTouchListener(null, resId, listener);
	}

	/**
	 * 指定したViewにシークチェンジリスナーをセットします。
	 *
	 * @param view
	 *            検索対象のView
	 * @param resId
	 *            リソースID
	 * @param listener
	 *            セットするリスナー
	 */
	protected void setOnSeekBarChangeListener(View view, int resId, OnSeekBarChangeListener listener) {
		if (view == null) {
			view = getView();
		}

		((SeekBar) view.findViewById(resId)).setOnSeekBarChangeListener(listener);
	}

	protected void setOnSeekBarChangeListener(int resId, OnSeekBarChangeListener listener) {
		setOnSeekBarChangeListener(null, resId, listener);
	}

	/**
	 * 指定したSeekBarの値を増加します。
	 *
	 * @param view
	 *            検索対象のView
	 * @param resId
	 *            リソースID
	 * @param add
	 *            増分値 (0～SeekBarのMaxに丸められます。)
	 */
	protected void addSeek(View view, int resId, int add) {
		if (view == null) {
			view = getView();
		}

		SeekBar seekBar = (SeekBar) view.findViewById(resId);
		seekBar.setProgress(Math.min(seekBar.getMax(), Math.max(0, seekBar.getProgress() + add)));
	}

	/**
	 * 指定したシークバーの現在のプログレスを返します。
	 *
	 * @param view
	 *            検索対象のView
	 * @param resId
	 *            リソースID
	 * @return シークバーのプログレス
	 */
	protected int getSeekProgress(View view, int resId) {
		if (view == null) {
			view = getView();
		}

		SeekBar seekBar = (SeekBar) view.findViewById(resId);
		return seekBar.getProgress();
	}

	protected int getSeekProgress(int resId) {
		return getSeekProgress(null, resId);
	}

	/**
	 * 指定したAdapterViewの現在選択されているObjectを返します。
	 *
	 * @param view
	 *            検索対象のView
	 * @param resId
	 *            リソースID
	 * @return 選択されている AdapterのgetItem()の返却値
	 */
	protected Object getSelectedItem(View view, int resId) {
		if (view == null) {
			view = getView();
		}

		AdapterView<?> adapterView = (AdapterView<?>) view.findViewById(resId);
		return adapterView.getSelectedItem();
	}

	protected Object getSelectedItem(int resId) {
		return getSelectedItem(null, resId);
	}
}
