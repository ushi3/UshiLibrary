package com.ushi.lib.util;

public class NumberUtil {

	private NumberUtil() {
	}

	/**
	 * 引数の文字列をintに直します。
	 *
	 * @param str パースする文字列
	 * @param def パースに失敗した場合に返す値
	 * @return 引数のint表現、またはdef
	 */
	public static int parseInt(String str, int def) {
		try {
			return Integer.parseInt(str);

		} catch (Exception e) {
		}

		return def;
	}

	/**
	 * 引数の文字列をlongに直します。
	 *
	 * @param str パースする文字列
	 * @param def パースに失敗した場合に返す値
	 * @return 引数のlong表現、またはdef
	 */
	public static long parseLong(String str, long def) {
		try {
			return Long.parseLong(str);

		} catch (Exception e) {
		}

		return def;
	}

	/**
	 * 引数の文字列をfloatに直します。
	 *
	 * @param str パースする文字列
	 * @param def パースに失敗した場合に返す値
	 * @return 引数のfloat表現、またはdef
	 */
	public static float parseFloat(String str, float def) {
		try {
			return Float.parseFloat(str);

		} catch (Exception e) {
		}

		return def;
	}

	/**
	 * 引数の文字列をdoubleに直します。
	 *
	 * @param str パースする文字列
	 * @param def パースに失敗した場合に返す値
	 * @return 引数のdouble表現、またはdef
	 */
	public static double parseDouble(String str, double def) {
		try {
			return Double.parseDouble(str);

		} catch (Exception e) {
		}

		return def;
	}

	/**
	 * 引数の文字列をshortに直します。
	 *
	 * @param str パースする文字列
	 * @param def パースに失敗した場合に返す値
	 * @return 引数のshort表現、またはdef
	 */
	public static double parseShort(String str, short def) {
		try {
			return Short.parseShort(str);

		} catch (Exception e) {
		}

		return def;
	}

	/**
	 * 引数の文字列をbyteに直します。
	 *
	 * @param str パースする文字列
	 * @param def パースに失敗した場合に返す値
	 * @return 引数のbyte表現、またはdef
	 */
	public static double parseByte(String str, byte def) {
		try {
			return Byte.parseByte(str);

		} catch (Exception e) {
		}

		return def;
	}

	/**
	 * 引数のfloatの桁の、指定した小数桁より以下を切り捨てます。<p>
	 *
	 * <li>
	 * num = 3.3333333
	 * </li><li>
	 * digit = 3
	 * </li>
	 *
	 * の結果は、3.333です。<br>
	 * digitが0以下のばあい、小数桁を切り捨てた値を返します。
	 *
	 * @param num 素となる数字
	 * @param digit 残す総数桁数
	 * @return
	 */
	public static float truncateDigit(float num, int digit) {
		int multi = (int) Math.pow(10, Math.max(digit, 0));
		int n = (int) (num * multi);
		return (float) n / multi;
	}

}
