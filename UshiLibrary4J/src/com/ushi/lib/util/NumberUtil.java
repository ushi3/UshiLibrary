package com.ushi.lib.util;

public class NumberUtil {

	private NumberUtil() {
	}

	public static int parseInt(String str, int def) {
		try {
			return Integer.parseInt(str);

		} catch (Exception e) {
		}

		return def;
	}

	public static float parseFloat(String str, float def) {
		try {
			return Float.parseFloat(str);

		} catch (Exception e) {
		}

		return def;
	}

	public static double parseDouble(String str, double def) {
		try {
			return Double.parseDouble(str);

		} catch (Exception e) {
		}

		return def;
	}

	public static double parseShort(String str, short def) {
		try {
			return Short.parseShort(str);

		} catch (Exception e) {
		}

		return def;
	}

	public static double parseByte(String str, byte def) {
		try {
			return Byte.parseByte(str);

		} catch (Exception e) {
		}

		return def;
	}

}
