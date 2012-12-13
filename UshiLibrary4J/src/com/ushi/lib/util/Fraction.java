package com.ushi.lib.util;

/**
 * 分数を表すクラス
 *
 * @author Ushi
 */
public class Fraction<N extends Number> {
	protected N mMolecule;
	protected N mDenominator;

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
	 * @param molecule
	 *            分子
	 * @param denominator
	 *            分母
	 */
	public void set(N molecule, N denominator) {
		if (molecule == null || denominator == null) {
			throw new IllegalArgumentException("args must be not null");
		}
		this.mMolecule = molecule;
		this.mDenominator = denominator;
	}

	/**
	 * @param molecule
	 *            分子
	 */
	public void setMolecule(N molecule) {
		if (molecule == null) {
			throw new IllegalArgumentException("args must be not null");
		}
		this.mMolecule = molecule;
	}

	/**
	 * @param denominator
	 *            分母
	 */
	public void setDenominator(N denominator) {
		if (denominator == null) {
			throw new IllegalArgumentException("args must be not null");
		}
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
	 * このインスタンスの表す値の余事象となる値をfloatで返します。<br>
	 * 分子 ＞ 分母の場合、このメソッドの結果は保証されません。
	 *
	 * @return (分母 - 分子)/分母
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

	/**
	 * 分子をfloatで返す。
	 *
	 * @return float型の分子
	 */
	public float getMoleculeToFloat() {
		return mMolecule.floatValue();
	}

	/**
	 * 分子をintで返す。
	 *
	 * @return int型の分子
	 */
	public int getMoleculeToInt() {
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

	/**
	 * 分母をfloatで返す。
	 *
	 * @return float型の分母
	 */
	public float getDenominatorToFloat() {
		return mDenominator.floatValue();
	}

	/**
	 * 分母をintで返す。
	 *
	 * @return int型の分子
	 */
	public int getDenominatorToInt() {
		return mDenominator.intValue();
	}

	@Override
	public String toString() {
		return getMolecule() + "/" + getDenominator() + " : "
				+ getFractionValue();
	}
}
