package com.ushi.lib.util;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;


public class NumberUtilTest {

	@Test
	public void 切り捨てのテスト() {
		assertThat(NumberUtil.truncateDigit(10f/3, 1), is(3.3f));
		assertThat(NumberUtil.truncateDigit(10f/3, 2), is(3.33f));
		assertThat(NumberUtil.truncateDigit(10f/3, 3), is(3.333f));
		assertThat(NumberUtil.truncateDigit(10f/3, 0), is(3f));
		assertThat(NumberUtil.truncateDigit(10f/3, -10), is(3f));
	}


}
