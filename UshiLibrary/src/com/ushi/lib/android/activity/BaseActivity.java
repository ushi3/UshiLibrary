package com.ushi.lib.android.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.ushi.lib.android.util.Util;

public abstract class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initActionBar();
	}

	protected void initActionBar() {
		if (Util.isMoreThanHoneycomb()) {
			ActionBar actionBar = getActionBar();
			if (actionBar != null) {
				// isEntrance()の状態によって、<がついたりする
				actionBar.setDisplayHomeAsUpEnabled(!isEntrance());
			}
		}
	}
	
	/**
	 * android.R.homeが通知されたときの処理。
	 * 
	 * @return trueを返すとActivityを閉じます。
	 */
	protected boolean onActionHome() {
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case android.R.id.home:
			if (onActionHome()) {
				finish();
			}
			return true;
			
		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	/**
	 * アプリの導入画面であるかを返します。
	 * falseの場合、ActionBarのアイコンに「<」が付加されます。
	 * 
	 * @return 導入画面であればtrue。デフォルトはfalse
	 */
	protected boolean isEntrance() {
		return false;
	}
	
	protected void startActivity(Class<? extends Activity> activity) {
		Intent intent = new Intent(this, activity);
		startActivity(intent);
	}
	
	protected void startActivityForResult(Class<? extends Activity> activity, int requestCode) {
		Intent intent = new Intent(this, activity);
		startActivityForResult(intent, requestCode);
	}
	
	protected void startActivityForResult(Class<? extends Activity> activity, int requestCode, Bundle extras) {
		Intent intent = new Intent(this, activity);
		intent.putExtras(extras);
		
		startActivityForResult(intent, requestCode);
	}
	
	protected void startActivity(Class<? extends Activity> activity, Bundle extras) {
		Intent intent = new Intent(this, activity);
		intent.putExtras(extras);

		startActivity(intent);
	}

}
