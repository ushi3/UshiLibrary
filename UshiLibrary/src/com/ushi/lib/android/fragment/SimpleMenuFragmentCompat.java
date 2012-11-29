package com.ushi.lib.android.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

/**
 * リスト形式での簡易的なメニュー表示のFragmentのコンパチバージョンです。<br>
 *
 * @author Ushi
 */
public class SimpleMenuFragmentCompat extends ListFragment implements SimpleMenuHelper.ISimpleMenu {

	private SimpleMenuHelper mHelper = new SimpleMenuHelper(this);

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
		mHelper.onAttach(activity);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setListAdapter(mHelper.getMenuAdapter(getActivity()));
	}

	@Override
	public void setMenuItems(CharSequence... menuItems) {
		mHelper.setMenuItems(getListAdapter(), menuItems);
	}

	@Override
	public void addMenuItems(CharSequence... menuItems) {
		mHelper.addMenuItems(getListAdapter(), menuItems);
	}

	@Override
	public void setSelectedColorEnabled(boolean enabled) {
		mHelper.setSelectedColorEnabled(enabled);
	}

	@Override
	public void setSelectedColor(int color) {
		mHelper.setSelectedColor(color);
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position, long id) {
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
