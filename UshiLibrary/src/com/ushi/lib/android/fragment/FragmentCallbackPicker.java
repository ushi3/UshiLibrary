package com.ushi.lib.android.fragment;

import android.support.v4.app.Fragment;

/**
 * Fragmentのコールバックピッカーのメソッドが被らないようにするためのPicker
 *
 * FIXME 同じFragmentを複数配置した場合ってどうすりゃいいんだろ？無視？
 *
 * @author Ushi
 */
public interface FragmentCallbackPicker {

	/**
	 * コールバックを返す。<br>
	 * このメソッドは、callerFragmentを引数に渡して呼び出されます。<p>
	 * 実装側は、引数のintanceofによって、適切なFragmentCallbackを返してください。
	 *
	 * @param callerFragment 呼び出し元インスタンス
	 * @return
	 */
	public FragmentCallback getCallcback(Fragment callerFragment);
}
