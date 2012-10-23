package com.ushi.lib.util;

import java.util.Arrays;
import java.util.List;

public class Util {

	private static final List<String> EXCLUDE_CLASSES = Arrays.asList(
			"dalvik.system.VMStack", "java.lang.Thread",
			Util.class.getCanonicalName());

	private Util() {
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
		StackTraceElement stack = pickupStack(ignoreDepth);
		if (stack != null) {
			return toClassName(stack);
		} else {
			return null;
		}
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
		StackTraceElement stack = pickupStack(ignoreDepth);
		if (stack != null) {
			return toString(stack);
		} else {
			return null;
		}
	}

	static StackTraceElement pickupStack(int ignoreDepth) {
		StackTraceElement[] stacks = Thread.currentThread().getStackTrace();

		boolean find = false;
		for (int i = 0; i < stacks.length; i++) {
			StackTraceElement stack = stacks[i];
			String stackClass = stack.getClassName();
			if (find == false && !EXCLUDE_CLASSES.contains(stackClass)) {
				find = true;
			}
			if (find && ignoreDepth == 0) {
				return stack;
			} else if (find) {
				ignoreDepth--;
			}
		}

		return null;
	}

	static String toClassName(StackTraceElement stack) {
		StringBuilder stb = new StringBuilder();
		stb.append(stack.getFileName().replace(".java", ""));

		return stb.toString();
	}

	static String toString(StackTraceElement stack) {
		StringBuilder stb = new StringBuilder();
		stb.append(stack.getFileName().replace(".java", ""));
		stb.append("#");
		stb.append(stack.getMethodName());
		stb.append("/L");
		stb.append(stack.getLineNumber());

		return stb.toString();
	}

}
