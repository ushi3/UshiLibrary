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
	 * バージョンがHoneycomb以上であればtrueを返す。
	 *
	 * @return 11以上ならtrue
	 */
	public static boolean isMoreThanHoneycomb() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
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
	 */
	public static void throwCallOnUIThread() {
		if (isUIThread()) {
			throw new IllegalStateException("this method should not be called UI thread.");
		}
	}

	/**
	 * 引数のパーミッションが記述されていなければ例外を投げます。
	 *
	 * @param permissions
	 */
	public static void enforceCallingOrSelfPermissions(Context context, String... permissions) {
		if (permissions == null) {
			return;
		}

		for (String permission : permissions) {
			if (permission != null) {
				context.enforceCallingOrSelfPermission(permission,
				"this application should have" + permission + "permission. ");
			}
		}

	}

}
