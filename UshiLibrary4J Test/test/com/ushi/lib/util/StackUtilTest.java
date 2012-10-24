package com.ushi.lib.util;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.ushi.lib.test.Hoge;

public class StackUtilTest {

	@Test
	public void スタックからの取得() {
		String className = StackUtil.getClassName();
		System.out.println(className);
		assertThat(className, is(StackUtilTest.class.getSimpleName()));
	}

	@Test
	public void クラス名の取得() {
		Hoge hoge = new Hoge();
		String className = hoge.getSimpleName();
		System.out.println(className);
		assertThat(className, is(Hoge.class.getSimpleName()));
	}
}
