package com.ushi.lib.android.activity.sliding;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingActivity;
import com.ushi.lib.android.util.Util;
import com.ushi.lib.ex.R;

public abstract class BaseSlidingActivity extends SlidingActivity {

	private boolean isToggleSlidingMenuOnMenuKey = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
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

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (isToggleSlidingMenuOnMenuKey
				&& event.getKeyCode() == KeyEvent.KEYCODE_MENU) {
			if (event.getAction() == KeyEvent.ACTION_UP) {
				toggle();
			}

			return true;
		}

		return super.dispatchKeyEvent(event);
	}
	
	/**
	 * メニューにSlidingMenu開閉のメニューアイテムを追加するかどうか。
	 * デフォルトは、ActionBarが使える上で、メニューキー非表示であるか、メニューのトグルをOFFにしている場合true。
	 */
	protected boolean hasToggleMenu() {
		return Util.enabledNativeActionBar() && (!isShowSoftMenuKey() || !isToggleSlidingMenuOnMenuKey);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		boolean b = hasToggleMenu() && Util.enabledNativeActionBar();
		if (b) {
			MenuInflater inf = getMenuInflater();
			inf.inflate(R.menu.default_toggle_menu, menu);
		}
		
		return b;
	}

	/**
	 * SlidingMenuの初期化
	 */
	protected void initSlidingMenu(boolean isToggleMenuKey) {
		isToggleSlidingMenuOnMenuKey = isToggleMenuKey;

		SlidingMenu menu = getSlidingMenu();
		menu.setSlidingEnabled(true);

		// スワイプ判定位置
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		menu.setTouchModeBehind(SlidingMenu.TOUCHMODE_MARGIN);

		// メニュー表示時のオフセット
		menu.setBehindOffset(getResources().getDimensionPixelSize(
				R.dimen.sliding_menu_behind_offset));

		// ActionBarはスライドしない
		setSlidingActionBarEnabled(false);
	}

	@TargetApi(11)
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
		final int id = item.getItemId();
		if (id == android.R.id.home) {
			if (onActionHome()) {
				finish();
			}
			return true;
		}
		if (id == R.id.menu_toggle) {
			toggle();
			return true;
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
