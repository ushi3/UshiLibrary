package com.ushi.lib.android.util;

import com.ushi.lib.util.Util;

public class Log {

	/**
	 * {@link android.util.Log#d(String, String)} のラッパ.
	 *
	 * @param msg
	 *            出力するメッセージ
	 */
	public static void d(String msg) {
		android.util.Log.d(Util.getClassName(1), msg);
	}

	/**
	 * {@link android.util.Log#d(String, String)} のラッパ. 現在のソースコード上の位置を出力する.
	 */
	public static void d() {
		android.util.Log.d(Util.getClassName(1), Util.getMethodName(1));
	}

	/**
	 * {@link android.util.Log#wtf(String, String)} のラッパ.
	 *
	 * @param msg
	 *            出力するメッセージ
	 */
	public static void wtf(String msg) {
		android.util.Log.wtf(Util.getClassName(1), msg);
	}

	/**
	 * {@link android.util.Log#w(String, String, Throwable)} のラッパ。
	 *
	 * @param th
	 *            発生した例外
	 */
	public static void w(Throwable th) {
		android.util.Log.w(Util.getClassName(1), "Exception raised.", th);
	}

	/**
	 * {@link android.util.Log#w(String, String, Throwable)} のラッパ。
	 *
	 * @param msg 出力するメッセージ
	 * @param th
	 *            発生した例外
	 */
	public static void w(String msg, Throwable th) {
		android.util.Log.w(Util.getClassName(1), msg, th);
	}

	/**
	 * {@link android.util.Log#w(String, String)} のラッパ。
	 *
	 * @param msg
	 *            出力するメッセージ
	 */
	public static void w(String msg) {
		android.util.Log.w(Util.getClassName(1), msg);
	}

	/**
	 * {@link android.util.Log#d(String, String)} のラッパ。
	 *
	 * @param msg
	 *            出力するメッセージ
	 */
	public static void e(String msg) {
		android.util.Log.e(Util.getClassName(1), msg);
	}

	/**
	 * {@link android.util.Log#e(String, String, Throwable)} のラッパ。
	 *
	 * @param th
	 *            発生した例外
	 */
	public static void e(Throwable th) {
		android.util.Log.e(Util.getClassName(1), "Exception occured.", th);
	}

	/**
	 * {@link android.util.Log#e(String, String, Throwable)} のラッパ。
	 *
	 * @param msg 出力するメッセージ
	 * @param th
	 *            発生した例外
	 */
	public static void e(String msg, Throwable th) {
		android.util.Log.e(Util.getClassName(1), msg, th);
	}
}