package com.ushi.lib.android.fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class SimpleMenuHelper extends BaseFragmentHelper {

	public static interface SimpleMenuListener {
		public void onSelected(BaseSimpleMenu caller, int position, String name);
	}

	private final SelectedColor mSelectedColor = new SelectedColor();
	private int mLastSelectedPosition = ListView.INVALID_POSITION;
	private final List<CharSequence> mMenuItems = new ArrayList<CharSequence>();

	private BaseSimpleMenu mChild;

	private SimpleMenuListener mListener;

	public SimpleMenuHelper(BaseSimpleMenu child) {
		mChild = child;
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

	public void setMenuItems(BaseAdapter adapter, CharSequence... menuItems) {
		mMenuItems.clear();
		addMenuItems(adapter, menuItems);
	}

	public void addMenuItems(BaseAdapter adapter, CharSequence... menuItems) {
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

	public void updateAdapter(BaseAdapter adapter) {
		if (adapter == null) {
			return;
		}

		if (mMenuItems == null || mMenuItems.size() == 0) {
			adapter.notifyDataSetInvalidated();

		} else {
			adapter.notifyDataSetChanged();
		}
	}

	public void onMenuSelected(BaseAdapter adapter, int position) {
		if (mListener != null) {
			Object item = adapter.getItem(position);
			mListener.onSelected(mChild, position, item != null ? item.toString() : null);
		}
		setChildSelected(adapter, position);
	}

	/**
	 * メニューの特定の位置に色を付ける
	 *
	 * @param position
	 *            色を付けたいポジション
	 */
	public void setChildSelected(BaseAdapter adapter, int position) {
		mLastSelectedPosition = position;
		try {
			adapter.notifyDataSetChanged();
		} catch (Exception e) {
			// こんなとこでエラってもらっても困る
		}
	}

	private class MenuAdapter extends ArrayAdapter<CharSequence> {

		public MenuAdapter(Context context) {
			super(context, android.R.layout.simple_list_item_1, mMenuItems);
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			View view = super.getView(position, convertView, parent);

			int color = position == mLastSelectedPosition ? mSelectedColor.color : Color.TRANSPARENT;
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
	 *
	 * @author Ushi
	 */
	public static interface BaseSimpleMenu {
		public void setMenuItems(CharSequence... menuItems);

		public void addMenuItems(CharSequence... menuItems);

		public void setSelectedColorEnabled(boolean enabled);

		public void setSelectedColor(int color);
	}

}
