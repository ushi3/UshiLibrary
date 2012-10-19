package com.ushi.lib.android.fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ushi.lib.android.fragment.SimpleMenuFragmentCompat.MenuCallback.Picker;

/**
 * リスト形式での簡易的なメニュー表示のFragmentのコンパチバージョンです。<br>
 * ActivityでMenuCallbackかMenuCallbackPickerを実装してください。Pickerが優先的に使用されます。
 *
 * @author Ushi
 */
public class SimpleMenuFragmentCompat extends ListFragment {

	public static interface MenuCallback {
		/**
		 * メニューが選択したことの通知です。
		 *
		 * @param position 押された位置
		 */
		public void onSelected(int position);

		public static interface Picker {
			public MenuCallback getMenuCallback();
		}
	}

	/** コールバック */
	private MenuCallback mCallback;
	private final SelectedColor mSelectedColor = new SelectedColor();
	private int mLastSelectedPosition = ListView.INVALID_POSITION;

	private final List<CharSequence> mMenuItems = new ArrayList<CharSequence>();

	public SimpleMenuFragmentCompat() {
		setRetainInstance(true);
	}

	public static SimpleMenuFragmentCompat newInstance(CharSequence... menuItems) {
		SimpleMenuFragmentCompat fragment = new SimpleMenuFragmentCompat();
		fragment.setMenuItems(menuItems);

		return fragment;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		if (activity instanceof Picker) {
			// Picker優先
			mCallback = ((Picker) activity).getMenuCallback();

		} else  if (activity instanceof MenuCallback) {
			// Pickerがnullなら、MenuCallback必須
			mCallback = (MenuCallback) activity;
		}
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		setListAdapter(new MenuAdapter(getActivity()));
	}

	public void setMenuItems(CharSequence... menuItems) {
		mMenuItems.clear();
		addMenuItems(menuItems);
	}

	public void addMenuItems(CharSequence... menuItems) {
		if (menuItems != null) {
			mMenuItems.addAll(Arrays.asList(menuItems));
		}

		updateAdapter();
	}

	public void setSelectedColorEnabled(boolean enabled) {
		mSelectedColor.isEnabled = enabled;
	}

	public void setSelectedColor(int color) {
		mSelectedColor.color = color;
	}

	private void updateAdapter() {
		MenuAdapter adapter = (MenuAdapter) getListAdapter();
		if (adapter == null) {
			return;
		}

		if (mMenuItems == null || mMenuItems.size() == 0) {
			adapter.notifyDataSetInvalidated();

		} else {
			adapter.notifyDataSetChanged();
		}
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position, long id) {
		if (mCallback != null) {
			mCallback.onSelected(position);
		}

		setChildSelected(position);
	}

	/**
	 * メニューの特定の位置に色を付ける
	 *
	 * @param position
	 *            色を付けたいポジション
	 */
	private void setChildSelected(int position) {
		mLastSelectedPosition = position;
		try {
			MenuAdapter menuAdapter = (MenuAdapter) getListAdapter();
			menuAdapter.notifyDataSetChanged();
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
	}

	private static class SelectedColor {
		private boolean isEnabled = false;
		private int color = 0x666600AA;
	}
}
