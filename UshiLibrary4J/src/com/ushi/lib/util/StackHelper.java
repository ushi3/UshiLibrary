package com.ushi.lib.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StackHelper {

	private final List<String> exList;

	private static final String CLASS_NAME = StackHelper.class.getCanonicalName();

	public StackHelper(Class<?> user, String... ignores) {
		if (user == null) {
			throw new IllegalArgumentException("user must be not null.");
		}
		int size = ignores != null ? ignores.length : 0;
		exList = new ArrayList<String>(size + 2);

		exList.add(CLASS_NAME);
		exList.add(user.getCanonicalName());

		if (ignores != null) {
			exList.addAll(Arrays.asList(ignores));
		}
	}

	/**
	 * メソッド呼び出し側のクラス名(SimpleName)を取得する. 実際には {@link #getClassName(int)}
	 * に0を取得したのと同様の挙動.
	 *
	 * @return クラス名
	 */
	public String getClassName() {
		return getClassName(0);
	}

	/**
	 * メソッド呼び出し側のクラス名(SimpleName)を取得する. 取得するクラス名の深さを引数で指定する.
	 *
	 * @param ignoreDepth
	 *            無視するスタックの深さ
	 * @return クラス名
	 */
	public String getClassName(int ignoreDepth) {
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
	public String getMethodName() {
		return getMethodName(0);
	}

	/**
	 * メソッド呼び出し側のメソッド名を取得する. 取得するクラス名の深さを引数で指定する.
	 *
	 * @param ignoreDepth
	 *            無視するスタックの深さ
	 * @return クラス名
	 */
	public String getMethodName(int ignoreDepth) {
		StackTraceElement stack = pickupStack(ignoreDepth);
		if (stack != null) {
			return toString(stack);
		} else {
			return null;
		}
	}

	protected StackTraceElement pickupStack(int ignoreDepth) {
		StackTraceElement[] stacks = Thread.currentThread().getStackTrace();

		boolean find = false;
		for (int i = 0; i < stacks.length; i++) {
			StackTraceElement stack = stacks[i];
			String stackClass = stack.getClassName();
			if (find == false && exList.contains(stackClass) == false) {
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

	public static String toClassName(StackTraceElement stack) {
		StringBuilder stb = new StringBuilder();
		stb.append(stack.getFileName().replace(".java", ""));

		return stb.toString();
	}

	public static String toString(StackTraceElement stack) {
		StringBuilder stb = new StringBuilder();
		stb.append(stack.getFileName().replace(".java", ""));
		stb.append("#");
		stb.append(stack.getMethodName());
		stb.append("/L");
		stb.append(stack.getLineNumber());

		return stb.toString();
	}

}
