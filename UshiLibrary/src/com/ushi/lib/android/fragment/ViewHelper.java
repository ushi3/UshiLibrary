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
	 * 指定したIDのViewを返します。
	 *
	 * @param id
	 *            ViewのID
	 * @return Viewまたはnull
	 */
	public View findViewById(int id) {
		View view = getView();
		if (view == null) {
			return null;
		}

		return view.findViewById(id);
	}

	/**
	 * 指定したIDの入れ子式にViewを検索して返します。
	 * <p>
	 *
	 * もし、findViewById(R.id.parent, R.id.child)と呼び出した場合、
	 * 返却されるViewは、R.id.parentのIDを持つViewの中にある、R.id.childのIDを持つViewとなります。
	 *
	 * @param ids
	 *            入れ子式のID、一番最後のIDのViewが返る。
	 * @return
	 */
	public View findViewById(int... ids) {
		if (ids == null) {
			return null;
		}

		View view = getView();
		if (view == null) {
			return null;
		}

		for (int id : ids) {
			view = view.findViewById(id);
		}

		return view;
	}

	/**
	 * 指定したタグのViewを返します。
	 *
	 * @param tag
	 *            タグ
	 * @return Viewまたはnull
	 */
	public View findViewWithTag(Object tag) {
		View view = getView();
		if (view == null) {
			return null;
		}

		return view.findViewWithTag(tag);
	}

	/**
	 * 指定したViewのテキストを設定します。
	 *
	 * @param view
	 *            検索対象のView
	 * @param id
	 *            ViewのID
	 * @param text
	 *            テキスト
	 */
	public void setTextToView(View view, int id, CharSequence text) {
		if (view == null) {
			view = getView();
		}

		if (view == null) {
			Log.w("view is null.");
			return;
		}

		View v = view.findViewById(id);

		if (v instanceof TextView == false) {
			Log.w("target view is not TextView");
			return;
		}
		((TextView) v).setText(text);
	}

	/**
	 * 指定したViewのテキストを設定します。
	 *
	 * @param id
	 *            ViewのID
	 * @param text
	 *            テキスト
	 */
	public void setTextToView(int id, CharSequence text) {
		setTextToView(null, id, text);
	}

	/**
	 * 指定したViewのテキストを設定します。
	 *
	 * @param view
	 *            検索対象のView
	 * @param id
	 *            ViewのID
	 * @param resId
	 *            リソースID
	 */
	public void setTextToView(View view, int id, int resId) {
		if (view == null) {
			view = getView();
		}

		if (view == null) {
			Log.w("view is null.");
			return;
		}

		View v = view.findViewById(id);

		if (v instanceof TextView == false) {
			Log.w("target view is not TextView");
			return;
		}
		((TextView) v).setText(resId);
	}

	/**
	 * 指定したViewのテキストを設定します。
	 *
	 * @param id
	 *            ViewのID
	 * @param resId
	 *            リソースID
	 */
	public void setTextToView(int id, int resId) {
		setTextToView(null, id, resId);
	}

	/**
	 * 指定したViewに設定されているテキストを取得します。
	 *
	 * @param view
	 *            検索対象のView
	 * @param id
	 *            ViewのID
	 * @return 設定されているテキスト、またはnull
	 */
	public String getTextByView(View view, int id) {
		if (view == null) {
			view = getView();
		}
		if (view == null) {
			Log.w("view is null.");
			return null;
		}

		View v = view.findViewById(id);

		if (v instanceof TextView == false) {
			Log.w("target view is not TextView");
			return null;
		}
		return ((TextView) v).getText().toString();
	}

	/**
	 * 指定したViewに設定されているテキストを取得します。
	 *
	 * @param id
	 *            ViewのID
	 * @return 設定されているテキスト、またはnull
	 */
	public String getTextByView(int id) {
		return getTextByView(null, id);
	}

	/**
	 * 指定したViewにクリックリスナーをセットします。
	 *
	 * @param view
	 *            検索対象のView
	 * @param id
	 *            ViewのID
	 * @param listener
	 *            セットするリスナー
	 */
	public void setOnClickListener(View view, int id, OnClickListener listener) {
		if (view == null) {
			view = getView();
		}
		if (view == null) {
			Log.w("view is null.");
			return;
		}

		view.findViewById(id).setOnClickListener(listener);
	}

	/**
	 * 指定したViewにクリックリスナーをセットします。
	 *
	 * @param id
	 *            ViewのID
	 * @param listener
	 *            セットするリスナー
	 */
	public void setOnClickListener(int id, OnClickListener listener) {
		setOnClickListener(null, id, listener);
	}

	/**
	 * 指定したViewにロングクリックリスナーをセットします。
	 *
	 * @param view
	 *            検索対象のView
	 * @param id
	 *            ViewのID
	 * @param listener
	 *            セットするリスナー
	 */
	public void setOnLongClickListener(View view, int id,
			OnLongClickListener listener) {
		if (view == null) {
			view = getView();
		}
		if (view == null) {
			Log.w("view is null.");
			return;
		}

		view.findViewById(id).setOnLongClickListener(listener);
	}

	/**
	 * 指定したViewにロングクリックリスナーをセットします。
	 *
	 * @param id
	 *            ViewのID
	 * @param listener
	 *            セットするリスナー
	 */
	public void setOnLongClickListener(int id, OnLongClickListener listener) {
		setOnLongClickListener(null, id, listener);
	}

	/**
	 * 指定したViewにタッチリスナーをセットします。
	 *
	 * @param view
	 *            検索対象のView
	 * @param id
	 *            ViewのID
	 * @param listener
	 *            セットするリスナー
	 */
	public void setOnTouchListener(View view, int id, OnTouchListener listener) {
		if (view == null) {
			view = getView();
		}
		if (view == null) {
			Log.w("view is null.");
			return;
		}

		view.findViewById(id).setOnTouchListener(listener);
	}

	/**
	 * 指定したViewにタッチリスナーをセットします。
	 *
	 * @param id
	 *            ViewのID
	 * @param listener
	 *            セットするリスナー
	 */
	public void setOnTouchListener(int id, OnTouchListener listener) {
		setOnTouchListener(null, id, listener);
	}

	/**
	 * 指定したSeekBarにシークチェンジリスナーをセットします。
	 *
	 * @param view
	 *            検索対象のView
	 * @param id
	 *            ViewのID
	 * @param listener
	 *            セットするリスナー
	 */
	public void setOnSeekBarChangeListener(View view, int id,
			OnSeekBarChangeListener listener) {
		if (view == null) {
			view = getView();
		}
		if (view == null) {
			Log.w("view is null.");
			return;
		}

		View v = view.findViewById(id);

		if (v instanceof SeekBar == false) {
			Log.w("target is not SeekBar");
			return;
		}

		((SeekBar) v).setOnSeekBarChangeListener(listener);
	}

	/**
	 * 指定したSeekBarにシークチェンジリスナーをセットします。
	 *
	 * @param id
	 *            ViewのID
	 * @param listener
	 *            セットするリスナー
	 */
	public void setOnSeekBarChangeListener(int id,
			OnSeekBarChangeListener listener) {
		setOnSeekBarChangeListener(null, id, listener);
	}

	/**
	 * 指定したSeekBarの値を増加します。
	 *
	 * @param view
	 *            検索対象のView
	 * @param id
	 *            ViewのID
	 * @param add
	 *            増分値 (0～SeekBarのMaxに丸められます。)
	 */
	public void addSeek(View view, int id, int add) {
		if (view == null) {
			view = getView();
		}
		if (view == null) {
			Log.w("view is null.");
			return;
		}

		View v = view.findViewById(id);

		if (v instanceof SeekBar == false) {
			Log.w("target is not SeekBar");
			return;
		}

		SeekBar seekBar = (SeekBar) v;
		seekBar.setProgress(Math.min(seekBar.getMax(),
				Math.max(0, seekBar.getProgress() + add)));
	}

	/**
	 * 指定したシークバーの現在のプログレスを返します。
	 *
	 * @param view
	 *            検索対象のView
	 * @param id
	 *            ViewのID
	 * @return シークバーのプログレス
	 */
	public int getSeekProgress(View view, int id) {
		if (view == null) {
			view = getView();
		}
		if (view == null) {
			Log.w("view is null.");
			return 0;
		}

		View v = view.findViewById(id);

		if (v instanceof SeekBar == false) {
			Log.w("target is not SeekBar");
			return 0;
		}

		SeekBar seekBar = (SeekBar) v;
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
	 * @param id
	 *            ViewのID
	 * @return 選択されている AdapterのgetItem()の返却値
	 */
	public Object getSelectedItem(View view, int id) {
		if (view == null) {
			view = getView();
		}
		if (view == null) {
			Log.w("view is null.");
			return null;
		}

		View v = view.findViewById(id);

		if (v instanceof AdapterView<?> == false) {
			Log.w("target is not AdapterView");
			return null;
		}

		AdapterView<?> adapterView = (AdapterView<?>) v;
		return adapterView.getSelectedItem();
	}

	public Object getSelectedItem(int resId) {
		return getSelectedItem(null, resId);
	}

}
