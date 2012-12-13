package com.ushi.lib.util;

/**
 * floatの分数を表すクラス。
 *
 * @author Ushi
 */
public class FloatFraction extends Fraction<Float> {

	/**
	 * @param molecule
	 *            分子
	 * @param denominator
	 *            分母
	 */
	public FloatFraction(float molecule, float denominator) {
		super(molecule, denominator);
	}

	/**
	 * 分子・分母が0.0f, 1.0fの状態で初期化します。
	 */
	public FloatFraction() {
		super(0f, 1f);
	}
}