package com.ushi.lib.android.util;

public class Log {

	/**
	 * {@link android.util.Log#i(String, String)} のラッパ.
	 *
	 * @param msg
	 *            出力するメッセージ
	 */
	public static void i(String msg) {
		android.util.Log.i(StackUtil.getClassName(1), createMessage(msg));
	}

	/**
	 * {@link android.util.Log#i(String, String)} のラッパ. 現在のソースコード上の位置を出力する.
	 *
	 * @param ob
	 *            ob.toString() or null
	 */
	public static void i(Object ob) {
		android.util.Log.i(StackUtil.getClassName(1),
				createMessage(ob));
	}

	/**
	 * {@link android.util.Log#d(String, String)} のラッパ.
	 *
	 * @param msg
	 *            出力するメッセージ
	 */
	public static void d(String msg) {
		android.util.Log.d(StackUtil.getClassName(1), createMessage(msg));
	}

	/**
	 * {@link android.util.Log#d(String, String)} のラッパ. 現在のソースコード上の位置を出力する.
	 */
	public static void d() {
		android.util.Log.d(StackUtil.getClassName(1),
				StackUtil.getMethodName(1));
	}

	/**
	 * {@link android.util.Log#d(String, String)} のラッパ. 現在のソースコード上の位置を出力する.
	 *
	 * @param ob
	 *            ob.toString() or null
	 */
	public static void d(Object ob) {
		android.util.Log.d(StackUtil.getClassName(1),
				createMessage(ob));
	}

	/**
	 * {@link android.util.Log#wtf(String, String)} のラッパ.
	 *
	 * @param msg
	 *            出力するメッセージ
	 */
	public static void wtf(String msg) {
		android.util.Log.wtf(StackUtil.getClassName(1), createMessage(msg));
	}

	/**
	 * {@link android.util.Log#wtf(String, String)} のラッパ.
	 *
	 * @param ob
	 *            ob.toString() or null
	 */
	public static void wtf(Object ob) {
		android.util.Log.wtf(StackUtil.getClassName(1),
				createMessage(ob));
	}

	/**
	 * {@link android.util.Log#w(String, String, Throwable)} のラッパ。
	 *
	 * @param th
	 *            発生した例外
	 */
	public static void w(Throwable th) {
		android.util.Log.w(StackUtil.getClassName(1), "Exception raised.", th);
	}

	/**
	 * {@link android.util.Log#w(String, String, Throwable)} のラッパ。
	 *
	 * @param msg
	 *            出力するメッセージ
	 * @param th
	 *            発生した例外
	 */
	public static void w(String msg, Throwable th) {
		android.util.Log.w(StackUtil.getClassName(1), createMessage(msg), th);
	}

	/**
	 * {@link android.util.Log#w(String, String, Throwable)} のラッパ。
	 *
	 * @param ob
	 *            ob.toString() or null
	 * @param th
	 *            発生した例外
	 */
	public static void w(Object ob, Throwable th) {
		android.util.Log.w(StackUtil.getClassName(1),
				createMessage(ob), th);
	}

	/**
	 * {@link android.util.Log#w(String, String)} のラッパ。
	 *
	 * @param msg
	 *            出力するメッセージ
	 */
	public static void w(String msg) {
		android.util.Log.w(StackUtil.getClassName(1), createMessage(msg));
	}

	/**
	 * {@link android.util.Log#w(String, String)} のラッパ。
	 *
	 * @param ob
	 *            ob.toString() or null
	 */
	public static void w(Object ob) {
		android.util.Log.w(StackUtil.getClassName(1),
				createMessage(ob));
	}

	/**
	 * {@link android.util.Log#d(String, String)} のラッパ。
	 *
	 * @param msg
	 *            出力するメッセージ
	 */
	public static void e(String msg) {
		android.util.Log.e(StackUtil.getClassName(1), createMessage(msg));
	}

	/**
	 * {@link android.util.Log#d(String, String)} のラッパ。
	 *
	 * @param ob
	 *            ob.toString() or null
	 */
	public static void e(Object ob) {
		android.util.Log.e(StackUtil.getClassName(1),
				createMessage(ob));
	}

	/**
	 * {@link android.util.Log#e(String, String, Throwable)} のラッパ。
	 *
	 * @param th
	 *            発生した例外
	 */
	public static void e(Throwable th) {
		android.util.Log.e(StackUtil.getClassName(1), "Exception occured.", th);
	}

	/**
	 * {@link android.util.Log#e(String, String, Throwable)} のラッパ。
	 *
	 * @param msg
	 *            出力するメッセージ
	 * @param th
	 *            発生した例外
	 */
	public static void e(String msg, Throwable th) {
		android.util.Log.e(StackUtil.getClassName(1), createMessage(msg), th);
	}

	/**
	 * {@link android.util.Log#e(String, String, Throwable)} のラッパ。
	 *
	 * @param ob
	 *            ob.toString() or null
	 * @param th
	 *            発生した例外
	 */
	public static void e(Object ob, Throwable th) {
		android.util.Log.e(StackUtil.getClassName(1),
				createMessage(ob), th);
	}

	static String createMessage(Object ob) {
		return new StringBuilder().append(ob != null ? ob.toString() : null).toString();
	}
}