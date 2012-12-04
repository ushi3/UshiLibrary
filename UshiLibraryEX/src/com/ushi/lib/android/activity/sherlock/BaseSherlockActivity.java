package com.ushi.lib.android.activity.sherlock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;

public abstract class BaseSherlockActivity extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initActionBar();
	}

	@Override
	public void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		if (isShowSoftMenuKey()) {
			getWindow().addFlags(0x08000000);
		}
	}

	protected void initActionBar() {
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			// isEntrance()の状態によって、<がついたりする
			actionBar.setDisplayHomeAsUpEnabled(!isEntrance());
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
	public boolean onOptionsItemSelected(
			com.actionbarsherlock.view.MenuItem item) {
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
	 * アプリの導入画面であるかを返します。 falseの場合、ActionBarのアイコンに「<」が付加されます。
	 *
	 * @return 導入画面であればtrue。デフォルトはfalse
	 */
	protected boolean isEntrance() {
		return false;
	}

	/**
	 * ソフトキーのMenuを強制表示するフラグを設定するかを返します。
	 *
	 * @return Menuを強制表示する場合true
	 */
	protected boolean isShowSoftMenuKey() {
		return false;
	}

	protected void startActivity(Class<? extends Activity> activity) {
		Intent intent = new Intent(this, activity);
		startActivity(intent);
	}

	protected void startActivityForResult(Class<? extends Activity> activity,
			int requestCode) {
		Intent intent = new Intent(this, activity);
		startActivityForResult(intent, requestCode);
	}

	protected void startActivityForResult(Class<? extends Activity> activity,
			int requestCode, Bundle extras) {
		Intent intent = new Intent(this, activity);
		intent.putExtras(extras);

		startActivityForResult(intent, requestCode);
	}

	protected void startActivity(Class<? extends Activity> activity,
			Bundle extras) {
		Intent intent = new Intent(this, activity);
		intent.putExtras(extras);

		startActivity(intent);
	}

}
