package com.ushi.lib.android.util;

import android.content.Context;
import android.os.Build;
import android.os.Looper;

/**
 * ユーティリティクラス。
 *
 * @author Ushi
 */
public class Util {
	private Util() {
	}

	/**
	 * 端末のAndroidのバージョンが、引数のAPI Level以上であるかを判定します。
	 *
	 * @param apiLevel APIレベル
	 * @return 引数のAPILevel以上であればtrue、それ未満であればfalse
	 */
	public static boolean isMoreThanLevel(int apiLevel) {
		return Build.VERSION.SDK_INT >= apiLevel;
	}

	/**
	 * バージョンがHoneycomb以上であればtrueを返す。
	 *
	 * @return 11以上ならtrue
	 */
	public static boolean isMoreThanHoneycomb() {
		return isMoreThanLevel(Build.VERSION_CODES.HONEYCOMB);
	}

	/**
	 * Android標準のActionBarが利用できるかを返します。
	 *
	 * @return ActionBarが使えるバージョンならtrue
	 */
	public static boolean enabledNativeActionBar() {
		return isMoreThanHoneycomb();
	}

	/**
	 * メソッドを呼び出したスレッドがUIスレッドであるかを判定します。
	 *
	 * @return UIスレッドの場合true、それ以外ならfalse
	 */
	public static boolean isUIThread() {
		return Looper.getMainLooper().getThread().equals(Thread.currentThread());
	}

	/**
	 * このメソッドがUIスレッドで呼び出された場合、ランタイム例外を投げます。
	 *
	 * @throws IllegalStateException UIスレッドで呼び出された場合にスロー
	 */
	public static void throwCallOnUIThread() {
		if (isUIThread()) {
			throw new IllegalStateException("this method should not be called UI Thread.");
		}
	}

	/**
	 * 引数のパーミッションが記述されていなければ例外を投げます。
	 *
	 * @param permissions パーミッション。(可変長引数)
	 */
	public static void enforceCallingOrSelfPermissions(Context context, String... permissions) {
		if (permissions == null) {
			return;
		}

		for (String permission : permissions) {
			if (permission != null) {
				context.enforceCallingOrSelfPermission(permission,
				"this application should have " + permission + " permission. ");
			}
		}
	}

}
