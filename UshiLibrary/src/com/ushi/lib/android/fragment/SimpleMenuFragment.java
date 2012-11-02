package com.ushi.lib.android.fragment;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class SimpleMenuFragment extends ListFragment implements SimpleMenuHelper.ISimpleMenu {

	public SimpleMenuFragment() {
		setRetainInstance(true);
	}

	public static SimpleMenuFragment newInstance(CharSequence... menuItems) {
		SimpleMenuFragment fragment = new SimpleMenuFragment();
		fragment.setMenuItems(menuItems);

		return fragment;
	}

	private SimpleMenuHelper mHelper = new SimpleMenuHelper(this);

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mHelper.onAttach(activity);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		setListAdapter(mHelper.getMenuAdapter(getActivity()));
	}

	public void setMenuItems(CharSequence... menuItems) {
		mHelper.setMenuItems(getListAdapter(), menuItems);
	}

	public void addMenuItems(CharSequence... menuItems) {
		mHelper.addMenuItems(getListAdapter(), menuItems);
	}

	public void setSelectedColorEnabled(boolean enabled) {
		mHelper.setSelectedColorEnabled(enabled);
	}

	public void setSelectedColor(int color) {
		mHelper.setSelectedColor(color);
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position,
			long id) {
		mHelper.onMenuSelected(getListAdapter(), position);
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
}
