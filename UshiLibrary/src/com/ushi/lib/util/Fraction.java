package com.ushi.lib.util;

/**
 * 分数を表すクラス
 *
 * @author Ushi
 */
public class Fraction<N extends Number> {
	protected final N mMolecule;
	protected final N mDenominator;

	/**
	 * @param molecule
	 *            分子
	 * @param denominator
	 *            分母
	 */
	public Fraction(N molecule, N denominator) {
		if (molecule == null || denominator == null) {
			throw new IllegalArgumentException("args must be not null");
		}
		this.mMolecule = molecule;
		this.mDenominator = denominator;
	}

	/**
	 * 結果が正当になるかを返す。
	 *
	 * @return このインスタンスの分母が0でなければtrue、0であればfalse
	 */
	public boolean isClear() {
		return mDenominator.intValue() != 0;
	}

	/**
	 * このインスタンスの分子/分母の値をfloatで返します。
	 *
	 * @return 分子/分母または無限
	 */
	public float getFractionValue() {
		return getMoleculeToFloat() / getDenominatorToFloat();
	}

	/**
	 * このインスタンスの逆数をfloatで返します。
	 *
	 * @return 分母/分子または無限
	 */
	public float getInverseValue() {
		return getDenominatorToFloat() / getMoleculeToFloat();
	}

	/**
	 * このインスタンスの表す値の余事象となる値をfloatで返します。
	 *
	 * @return 1 - 分子/分母
	 */
	public float getComplementaryValue() {
		float denom = getDenominatorToFloat();
		return (denom - getMoleculeToFloat()) / denom;
	}

	/**
	 * 分子を返す。
	 *
	 * @return 分子
	 */
	protected final N getMolecule() {
		return mMolecule;
	}

	public float getMoleculeToFloat() {
		return mMolecule.floatValue();
	}

	public float getMoleculeToInt() {
		return mMolecule.intValue();
	}

	/**
	 * 分母を返す。
	 *
	 * @return 分母
	 */
	protected final N getDenominator() {
		return mDenominator;
	}

	public float getDenominatorToFloat() {
		return mDenominator.floatValue();
	}

	public int getDenominatorToInt() {
		return mDenominator.intValue();
	}

	@Override
	public String toString() {
		return String.valueOf(getFractionValue());
	}
}
