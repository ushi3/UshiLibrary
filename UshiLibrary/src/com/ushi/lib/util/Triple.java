package com.ushi.lib.util;

/**
 * 3つの組み合わせを扱うクラス。<br>
 * Pairの3つ版。
 * 
 * @author Ushi
 *
 * @param <A>
 * @param <B>
 * @param <C>
 */
public class Triple<F, S, T> {

	public final F first;
	
	public final S second;
	
	public final T third;
	
	public Triple(F first, S second, T third) {
		this.first = first;
		this.second = second;
		this.third = third;
	}
	
	@SuppressWarnings("unchecked")
	@Override
    public boolean equals(Object object) {
        if (object == this) {
        	return true;
        }
        if (!(object instanceof Triple)) {
        	return false;
        }
        
        final Triple<F, S, T> other;
        try {
            other = (Triple<F, S, T>) object;
        } catch (ClassCastException e) {
            return false;
        }
        
        return first.equals(other.first) && second.equals(other.second) && third.equals(other.third);
    }
	
	@Override
	public int hashCode() {
        int result = 17;
        result = 31 * result + first.hashCode();
        result = 31 * result + second.hashCode();
        result = 31 * result + third.hashCode();
        return result;
    }

	/**
	 * インスタンスの簡易生成メソッド
	 * 
	 * @param a
	 * @param b
	 * @param c
	 * @return
	 */
	public static <A, B, C> Triple<A, B, C> create(A a, B b, C c) {
		return new Triple<A, B, C>(a, b, c);
	}
}
