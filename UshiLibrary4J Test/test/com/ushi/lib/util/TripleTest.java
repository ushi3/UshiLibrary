package com.ushi.lib.util;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;


public class TripleTest {

	@Test
	public void ファクトリメソッド() {
		Triple<Integer,String,Double> tri = Triple.create(1, "2", 3.0);

		assertThat(tri, not(nullValue()));

		assertThat(tri.first, is(instanceOf(Integer.class)));
		assertThat(tri.first, is(1));

		assertThat(tri.second, is(instanceOf(String.class)));
		assertThat(tri.second, is("2"));

		assertThat(tri.third, is(instanceOf(Double.class)));
		assertThat(tri.third, is(3.0));
	}
}
