package com.ushi.lib.util;


public class StackUtil {
	private static final StackHelper HELPER = new StackHelper(StackUtil.class, "java.lang.Thread");

	private StackUtil() {
	}

	/**
	 * メソッド呼び出し側のクラス名(SimpleName)を取得する. 実際には {@link #getClassName(int)}
	 * に0を取得したのと同様の挙動.
	 *
	 * @return クラス名
	 */
	public static String getClassName() {
		return getClassName(0);
	}

	/**
	 * メソッド呼び出し側のクラス名(SimpleName)を取得する. 取得するクラス名の深さを引数で指定する.
	 *
	 * @param ignoreDepth
	 *            無視するスタックの深さ
	 * @return クラス名
	 */
	public static String getClassName(int ignoreDepth) {
		return HELPER.getClassName(ignoreDepth);
	}

	/**
	 * メソッド呼び出し側のメソッド名を取得する. 実際には {@link #getMethodName(int)} に0を取得したのと同様の挙動.
	 *
	 * @return メソッド名
	 */
	public static String getMethodName() {
		return getMethodName(0);
	}

	/**
	 * メソッド呼び出し側のメソッド名を取得する. 取得するクラス名の深さを引数で指定する.
	 *
	 * @param ignoreDepth
	 *            無視するスタックの深さ
	 * @return クラス名
	 */
	public static String getMethodName(int ignoreDepth) {
		return HELPER.getMethodName(ignoreDepth);
	}
}
