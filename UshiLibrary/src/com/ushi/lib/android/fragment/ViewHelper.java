package com.ushi.lib.android.fragment;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.ushi.lib.android.util.Log;

/**
 * Viewのユーティリティ。
 *
 * @author Ushi
 */
public class ViewHelper {

	BaseHelper<?> mCaller;

	public ViewHelper(BaseHelper<?> caller) {
		mCaller = caller;
	}

	/**
	 * バインドされているFragmentのViewを返します。
	 *
	 * @return {@link View}
	 */
	protected View getView() {
		return mCaller.getChild().getParentView();
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
	public void setTextToView(View view, int resId, CharSequence text) {
		if (view == null) {
			view = getView();
		}

		if (view == null) {
			Log.w("view is null.");
			return;
		}

		TextView textView = (TextView) view.findViewById(resId);
		textView.setText(text);
	}

	public void setTextToView(int resId, CharSequence text) {
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
	public String getTextByView(View view, int resId) {
		if (view == null) {
			view = getView();
		}
		if (view == null) {
			Log.w("view is null.");
			return null;
		}

		TextView textView = (TextView) view.findViewById(resId);
		return textView.getText().toString();
	}

	public String getTextByView(int resId) {
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
	public void setOnClickListener(View view, int resId, OnClickListener listener) {
		if (view == null) {
			view = getView();
		}
		if (view == null) {
			Log.w("view is null.");
			return;
		}

		view.findViewById(resId).setOnClickListener(listener);
	}

	public void setOnClickListener(int resId, OnClickListener listener) {
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
	public void setOnLongClickListener(View view, int resId, OnLongClickListener listener) {
		if (view == null) {
			view = getView();
		}
		if (view == null) {
			Log.w("view is null.");
			return;
		}

		view.findViewById(resId).setOnLongClickListener(listener);
	}

	public void setOnLongClickListener(int resId, OnLongClickListener listener) {
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
	public void setOnTouchListener(View view, int resId, OnTouchListener listener) {
		if (view == null) {
			view = getView();
		}
		if (view == null) {
			Log.w("view is null.");
			return;
		}

		view.findViewById(resId).setOnTouchListener(listener);
	}

	public void setOnTouchListener(int resId, OnTouchListener listener) {
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
	public void setOnSeekBarChangeListener(View view, int resId, OnSeekBarChangeListener listener) {
		if (view == null) {
			view = getView();
		}
		if (view == null) {
			Log.w("view is null.");
			return;
		}

		((SeekBar) view.findViewById(resId)).setOnSeekBarChangeListener(listener);
	}

	public void setOnSeekBarChangeListener(int resId, OnSeekBarChangeListener listener) {
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
	public void addSeek(View view, int resId, int add) {
		if (view == null) {
			view = getView();
		}
		if (view == null) {
			Log.w("view is null.");
			return;
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
	public int getSeekProgress(View view, int resId) {
		if (view == null) {
			view = getView();
		}
		if (view == null) {
			Log.w("view is null.");
			return 0;
		}

		SeekBar seekBar = (SeekBar) view.findViewById(resId);
		return seekBar.getProgress();
	}

	public int getSeekProgress(int resId) {
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
	public Object getSelectedItem(View view, int resId) {
		if (view == null) {
			view = getView();
		}
		if (view == null) {
			Log.w("view is null.");
			return null;
		}

		AdapterView<?> adapterView = (AdapterView<?>) view.findViewById(resId);
		return adapterView.getSelectedItem();
	}

	public Object getSelectedItem(int resId) {
		return getSelectedItem(null, resId);
	}

}
