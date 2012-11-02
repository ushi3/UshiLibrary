package com.ushi.lib.android.fragment;

import android.app.Fragment;
import android.view.View;

public interface IChild {

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

	/**
	 * このインターフェースを実装したFragmentインスタンスの、トップレベルのViewを返します。<>br
	 *
	 * @return {@link Fragment#getView()}
	 */
	public View getParentView();

	/**
	 * {@link ViewHelper} を返します。
	 *
	 * @return {@link ViewHelper}
	 */
	public ViewHelper getViewHelper();
}
