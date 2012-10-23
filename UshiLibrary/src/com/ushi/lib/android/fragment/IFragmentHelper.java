package com.ushi.lib.android.fragment;

public interface IFragmentHelper {

	/**
	 * このインターフェースを実装したFragmentインスタンスのIDを返します。<br>
	 *
	 * @return FragmentのID
	 */
	public int getFragmentId();

	/**
	 * このインターフェースを実装したFragmentインスタンスに割り当てたタグ名を返します。<br>
	 *
	 * @return Fragmentに割り当てたタグ名
	 */
	public String getFragmentTag();

}
