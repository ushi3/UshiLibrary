package com.ushi.lib.android;

import android.os.AsyncTask;

import com.ushi.lib.android.ThrowableAsyncTask.Result;
import com.ushi.lib.android.util.Util;

/**
 * 例外が発生した場合に処理するAsyncTask
 *
 *
 * @author Hatsuno
 *
 * @param <RESULT_CODE>
 * @param <RESULT_DATA>
 */
public abstract class ThrowableAsyncTask<PROGRESS, RESULT_CODE, RESULT_DATA>
		extends AsyncTask<Void, PROGRESS, Result<RESULT_CODE, RESULT_DATA>> {

	@Override
	protected final Result<RESULT_CODE, RESULT_DATA> doInBackground(
			Void... params) {
		try {
			return Result.create(getSuccessCode(), doThrowableProcess());

		} catch (Exception e) {
			return Result.create(createErrorCode(e), null);
		}
	}

	/**
	 * 例外の投げられるバックグラウンドプロセスです。<br>
	 * このメソッドが返却値を返す場合、処理は正常に終了したとみなされ、 {@link #getSuccessCode()} が呼び出されます。
	 * 例外を返す場合、 {@link #createErrorCode(Exception)} が呼び出されます。
	 *
	 * @return 処理が正常に行われた場合に返るデータ
	 * @throws Exception
	 */
	protected abstract RESULT_DATA doThrowableProcess() throws Exception;

	/**
	 * 処理中に例外が発生した場合に呼び出されます。 instanceofで適当にチェックするのがよさげ。
	 *
	 * @param e
	 *            例外
	 * @return 後処理に伝える任意の結果コード
	 */
	protected abstract RESULT_CODE createErrorCode(Exception e);

	/**
	 * 正しい処理を伝えるコード。
	 *
	 * @return
	 */
	protected abstract RESULT_CODE getSuccessCode();

	/**
	 * UIスレッドで呼び出された場合、例外を投げます。
	 */
	protected void throwCallOnUIThread() {
		Util.throwCallOnUIThread();
	}

	/**
	 *
	 *
	 * @author Hatsuno
	 *
	 * @param <RESULT_CODE>
	 * @param <RESULT_DATA>
	 */
	public static class Result<RESULT_CODE, RESULT_DATA> {
		public RESULT_CODE resultCode;
		public RESULT_DATA data;

		private Result(RESULT_CODE resultCode, RESULT_DATA data) {
			this.resultCode = resultCode;
			this.data = data;
		}

		public static <C, R> Result<C, R> create(C code, R data) {
			return new Result<C, R>(code, data);
		}
	}

}
