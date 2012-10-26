package com.ushi.lib.util;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class FractionTest {

	@Test
	public void ジェネリクスのテスト() {
		byte m = 10;
		byte d = 20;

		Fraction<Byte> fr = new Fraction<Byte>(m, d);

		assertThat(fr.getMolecule(), is(m));
		assertThat(fr.getMoleculeToInt(), is(10));
		assertThat(fr.getMoleculeToFloat(), is(10.0f));

		assertThat(fr.getDenominator(), is(d));
		assertThat(fr.getDenominatorToInt(), is(20));
		assertThat(fr.getDenominatorToFloat(), is(20.0f));

		assertThat(fr.getFractionValue(), is(0.5f));
		assertThat(fr.getInverseValue(), is(2.0f));

		assertThat(fr.getComplementaryValue(), is(0.5f));
	}

	@Test
	public void 逆数のテスト() {
		{ // 分子 < 分母

			IntFraction fr = new IntFraction(2, 10);

			assertThat(fr.getFractionValue(), is(0.2f));
			assertThat(fr.getInverseValue(), is(5.0f));

			assertThat(fr.getComplementaryValue(), is(0.8f));
		}

		{ // 分子 < 分母

			IntFraction fr = new IntFraction(10, 2);

			assertThat(fr.getFractionValue(), is(5.0f));
			assertThat(fr.getInverseValue(), is(0.2f));

			// TODO 分子＞分母の場合、余事象ってどーなるべきなんや？
			assertThat(fr.getComplementaryValue(), is(0.8f));
		}
	}

}
