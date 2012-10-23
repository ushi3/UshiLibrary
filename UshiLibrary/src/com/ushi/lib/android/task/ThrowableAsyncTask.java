package com.ushi.lib.android.task;

import android.os.AsyncTask;
import android.os.Looper;

import com.ushi.lib.android.task.ThrowableAsyncTask.Result;
import com.ushi.lib.android.util.Log;

/**
 * 例外が発生した場合に処理するAsyncTask.
 * AsyncTaskと違って、execute()に引数を使えない。(AsyncTaskは再利用できないため、そもそも意味がないので)
 * 先にデータが必要な場合は、コンストラクタやsetterを利用すること。
 *
 * @author Ushi
 *
 * @param <PROGRESS>
 *            進捗データの型
 * @param <RESULT_CODE>
 *            結果コードの型
 * @param <RESULT_DATA>
 *            処理の結果として得たデータの型
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

	@Override
	protected final void onPreExecute() {
		try {
			onPreProcess();

		} catch (Exception e) {
			// コールバック処理内で例外が発生して落ちると、アプリの動きとしてダサいので例外をスルーする
			Log.e(e);
		}
	}

	@Override
	protected final void onPostExecute(Result<RESULT_CODE, RESULT_DATA> result) {
		try {
			onPostProcess(result);

		} catch (Exception e) {
			// コールバック処理内で例外が発生して落ちると、アプリの動きとしてダサいので例外をスルーする
			Log.e(e);
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
	 * タスクの前処理です。
	 */
	protected void onPreProcess() {
	}

	/**
	 * タスクの後処理です。
	 *
	 * @param result
	 */
	protected abstract void onPostProcess(
			Result<RESULT_CODE, RESULT_DATA> result);

	/**
	 * 処理中に例外が発生した場合に呼び出されます。 instanceofで適当にチェックするのがよさげ。
	 *
	 * @param e
	 *            例外
	 * @return 後処理に伝える任意の結果コード
	 */
	protected abstract RESULT_CODE createErrorCode(Exception e);

	/**
	 * 正しい処理を伝えるコード。 {@link #doThrowableProcess()}
	 * で例外なく処理が終わった場合に、このメソッドの結果が付加されます。
	 *
	 * @return
	 */
	protected abstract RESULT_CODE getSuccessCode();

	/**
	 * UIスレッドで呼び出された場合、例外を投げます。
	 */
	protected void throwCallOnUIThread() {
		if (Looper.getMainLooper().getThread().equals(Thread.currentThread())) {
			throw new IllegalStateException(
					"this method should not be called on UI Thread.");
		}
	}

	/**
	 * タスクの結果
	 *
	 * @author Ushi
	 *
	 * @param <RESULT_CODE>
	 *            コードの型
	 * @param <RESULT_DATA>
	 *            データの型
	 */
	public static class Result<RESULT_CODE, RESULT_DATA> {
		/** 結果コード */
		public RESULT_CODE resultCode;
		/** データ */
		public RESULT_DATA data;

		private Result(RESULT_CODE resultCode, RESULT_DATA data) {
			this.resultCode = resultCode;
			this.data = data;
		}

		/**
		 * 楽してインスタンスを作るためのメソッド。
		 *
		 * @param <C>
		 *            コードの型
		 * @param <R>
		 *            データの型
		 * @param code
		 *            コード
		 * @param data
		 *            データ
		 * @return {@link Result}
		 */
		public static <C, R> Result<C, R> create(C code, R data) {
			return new Result<C, R>(code, data);
		}
	}

}
