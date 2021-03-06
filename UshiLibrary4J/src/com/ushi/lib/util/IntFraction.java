package com.ushi.lib.util;

/**
 * intの分数を表すクラス。
 *
 * @author Ushi
 */
public class IntFraction extends Fraction<Integer> {

	/**
	 * @param molecule
	 *            分子
	 * @param denominator
	 *            分母
	 */
	public IntFraction(int molecule, int denominator) {
		super(molecule, denominator);
	}

	/**
	 * 分子・分母が0、1の状態で初期化します。
	 */
	public IntFraction() {
		super(0, 1);
	}
}
