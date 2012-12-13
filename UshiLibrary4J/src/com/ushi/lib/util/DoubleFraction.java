package com.ushi.lib.util;

/**
 * doubleの分数を表すクラス。
 *
 * @author Ushi
 */
public class DoubleFraction extends Fraction<Double> {

	/**
	 * @param molecule
	 *            分子
	 * @param denominator
	 *            分母
	 */
	public DoubleFraction(double molecule, double denominator) {
		super(molecule, denominator);
	}

	/**
	 * 分子・分母が0.0、1.0の状態で初期化します。
	 */
	public DoubleFraction() {
		super(0.0, 1.0);
	}
}
