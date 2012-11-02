package com.ushi.lib.android.fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.ushi.lib.android.fragment.SimpleMenuHelper.ISimpleMenu;

/**
 * 標準APIとCompatの処理を共通化するための汎用クラス。
 *
 * @author Ushi
 */
public class SimpleMenuHelper extends BaseHelper<ISimpleMenu> {

	/**
	 * {@link SimpleMenuFragment} 及び {@link SimpleMenuFragmentCompat} のリスナーです。
	 * <p>
	 *
	 * リスナーは {@link Fragment#onAttach()}のタイミングで、{@link Activity} と
	 * {@link SimpleMenuListener} のinstanceofがtrueの場合に自動的に設定されます。<br>
	 * 1つのActivityで複数の {@link SimpleMenuFragment} を利用する場合は、引数
	 * {@link ISimpleMenu} の {@link ISimpleMenu#getFragmentId()} や
	 * {@link ISimpleMenu#getFragmentTag()} を用いて、<br>
	 * 任意のメニューであることを確認することができます。
	 * <p>
	 *
	 * @author Ushi
	 */
	public static interface SimpleMenuListener {

		/**
		 * メニューが選択されたことの通知です。
		 *
		 * @param caller
		 *            通知を呼び出した {@link ISimpleMenu} 実装インスタンス
		 * @param position
		 *            選択されたメニューのindex
		 * @param name
		 *            選択されたメニューの名前
		 */
		public void onSelected(ISimpleMenu caller, int position, String name);
	}

	private final SelectedColor mSelectedColor = new SelectedColor();
	private int mLastSelectedPosition = ListView.INVALID_POSITION;
	private final List<CharSequence> mMenuItems = new ArrayList<CharSequence>();

	private SimpleMenuListener mListener;

	public SimpleMenuHelper(ISimpleMenu child) {
		super(child);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		if (activity instanceof SimpleMenuListener) {
			mListener = (SimpleMenuListener) activity;
		}
	}

	public MenuAdapter getMenuAdapter(Context context) {
		return new MenuAdapter(context);
	}

	public void setMenuItems(ListAdapter adapter, CharSequence... menuItems) {
		mMenuItems.clear();
		addMenuItems(adapter, menuItems);
	}

	public void addMenuItems(ListAdapter adapter, CharSequence... menuItems) {
		if (menuItems != null) {
			mMenuItems.addAll(Arrays.asList(menuItems));
		}

		updateAdapter(adapter);
	}

	public void setSelectedColorEnabled(boolean enabled) {
		mSelectedColor.isEnabled = enabled;
	}

	public void setSelectedColor(int color) {
		mSelectedColor.color = color;
	}

	public void updateAdapter(ListAdapter adapter) {
		if (adapter == null || adapter instanceof BaseAdapter == false) {
			return;
		}

		if (mMenuItems == null || mMenuItems.size() == 0) {
			((BaseAdapter) adapter).notifyDataSetInvalidated();

		} else {
			((BaseAdapter) adapter).notifyDataSetChanged();
		}
	}

	public void onMenuSelected(ListAdapter adapter, int position) {
		if (mListener != null) {
			Object item = adapter.getItem(position);
			mListener.onSelected(getChild(), position,
					item != null ? item.toString() : null);
		}
		setChildSelected(adapter, position);
	}

	/**
	 * メニューの特定の位置に色を付ける
	 *
	 * @param position
	 *            色を付けたいポジション
	 */
	public void setChildSelected(ListAdapter adapter, int position) {
		mLastSelectedPosition = position;
		updateAdapter(adapter);
	}

	private class MenuAdapter extends ArrayAdapter<CharSequence> {

		public MenuAdapter(Context context) {
			super(context, android.R.layout.simple_list_item_1, mMenuItems);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = super.getView(position, convertView, parent);

			int color = position == mLastSelectedPosition ? mSelectedColor.color
					: Color.TRANSPARENT;
			color = mSelectedColor.isEnabled ? color : Color.TRANSPARENT;

			view.setBackgroundColor(color);
			return view;
		}

		@Override
		public CharSequence getItem(int position) {
			if (mMenuItems == null || mMenuItems.size() <= position) {
				return null;
			}
			return mMenuItems.get(position);
		}
	}

	private static class SelectedColor {
		private boolean isEnabled = false;
		private int color = 0x666600AA;
	}

	/**
	 * Fragmentを作成する場合に実装するインターフェース。
	 *
	 * @author Ushi
	 */
	public static interface ISimpleMenu extends IChild {
		/**
		 * メニューの設定。 (上書き)<br>
		 * {@link SimpleMenuHelper#setMenuItems(ListAdapter, CharSequence...)}
		 * を呼び出してください。
		 *
		 * @param menuItems
		 *            メニュー名
		 */
		public void setMenuItems(CharSequence... menuItems);

		/**
		 * メニューの追加。<br>
		 * {@link SimpleMenuHelper#addMenuItems(ListAdapter, CharSequence...)}
		 * を呼び出してください。
		 *
		 * @param menuItems
		 *            追加するメニュー名
		 */
		public void addMenuItems(CharSequence... menuItems);

		/**
		 * 選択中のメニューの色を変更する設定の有効・無効。<br>
		 * {@link SimpleMenuHelper#setSelectedColorEnabled(boolean)} を呼び出してください。
		 *
		 * @param enabled
		 *            有効にする場合true, 無効の場合false。デフォルトはfalse
		 */
		public void setSelectedColorEnabled(boolean enabled);

		/**
		 * 選択中のメニューの色の設定。<br> {@link SimpleMenuHelper#setSelectedColor(int)}
		 * を呼び出してください。
		 *
		 * @param color
		 *            カラーコード。
		 */
		public void setSelectedColor(int color);

	}

}
