package com.ushi.lib.util;

/**
 * doubleの分数を表すクラス。
 *
 * @author Ushi
 */
public class DoubleFraction extends Fraction<Double> {

	/**
	 * @param molecule 分子
	 * @param denominator 分母
	 */
	public DoubleFraction(double molecule, double denominator) {
		super(molecule, denominator);
	}

}
