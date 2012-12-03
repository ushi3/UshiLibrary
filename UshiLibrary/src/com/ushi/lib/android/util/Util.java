package com.ushi.lib.android.util;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.text.TextUtils;

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
	 * @return ActionBarが使えるバージョン(11以上)ならtrue
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

	/**
	 * SDカードがマウントされているかを判定します。
	 *
	 * @return SDがマウント済みならtrue、そうでなければfalse
	 */
	public static boolean isMountedSdCard() {
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}

	/**
	 * SDカード内の特定のファイルパスを返します。<p>
	 * 引数がnullの場合、SDカードのルートパスを返します。
	 * 空文字の場合、SDカードのルートパスに"/"をつけて返します。
	 *
	 * @param filePath SDカードルート以下のまたはファイルパス
	 * @return
	 */
	public static String getFilePathInSdCard(String filePath) {
		StringBuilder sb = new StringBuilder(Environment.getExternalStorageDirectory().getPath());

		if (filePath != null) {
			sb.append(filePath.startsWith("/") ? "" : "/").append(filePath);
		}

		return sb.toString();
	}

}
