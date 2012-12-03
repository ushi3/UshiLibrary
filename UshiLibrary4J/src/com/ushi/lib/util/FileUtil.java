package com.ushi.lib.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileUtil {

	private static Logger logger = Logger.getLogger(FileUtil.class.getSimpleName());

	private FileUtil() {
	}

	/**
	 * ファイルをコピーする。
	 * 
	 * @param fromPath
	 *            コピー元ファイルパス
	 * @param toPath
	 *            コピー先ファイルパス
	 * @return コピーできたらtrue
	 */
	public static boolean copy(String fromPath, String toPath) {
		if (fromPath == null || toPath == null) {
			logger.log(Level.SEVERE, "filePath is null. " + fromPath + " -> " + toPath);
			return false;
		}

		return copy(new File(fromPath), new File(toPath));
	}

	/**
	 * ファイルをコピーする。
	 * 
	 * @param fromPath
	 *            コピー元ファイルパス
	 * @param toPath
	 *            コピー先ファイルパス
	 * @return コピーできたらtrue
	 */
	public static boolean copy(FileInputStream from, FileOutputStream to) {
		if (from == null || to == null) {
			logger.log(Level.SEVERE, "streams is null. " + from + " -> " + to);
			return false;
		}

		try {
			return copy(from.getChannel(), to.getChannel());
		} finally {
			close(from, to);
		}
	}

	/**
	 * ファイルをコピーする。
	 * 
	 * @param fromCh
	 *            コピー元ファイルチャネル
	 * @param toCh
	 *            コピー先ファイルチャネル
	 * @return
	 */
	public static boolean copy(FileChannel fromCh, FileChannel toCh) {
		if (fromCh == null || toCh == null) {
			logger.log(Level.SEVERE, "channels is null. " + fromCh + " -> " + toCh);
			close(fromCh, toCh);
			return false;
		}

		try {
			long pos = 0;
			long size = fromCh.size() - pos;

			return toCh.transferFrom(fromCh, pos, size) == size;

		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			close(fromCh, toCh);
		}

		return false;
	}

	/**
	 * ファイルをコピーする。
	 * 
	 * @param from
	 *            コピー元
	 * @param to
	 *            コピー先
	 * @return コピーできたらtrue
	 */
	public static boolean copy(File from, File to) {
		if (from == null || to == null) {
			logger.log(Level.SEVERE, "files is null. " + from + " -> " + to);
			return false;
		}

		FileInputStream in = null;
		FileOutputStream out = null;

		try {
			in = new FileInputStream(from);
			out = new FileOutputStream(to);
			return copy(in.getChannel(), out.getChannel());

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} finally {
			close(in, out);
		}

		return false;
	}

	/**
	 * Stream の内容をコピーします。
	 * 
	 * @param from コピー元
	 * @param to コピー先
	 * @return
	 */
	public static boolean copy(InputStream from, OutputStream to) {
		if (from == null || to == null) {
			logger.log(Level.SEVERE, "args is null. " + from + " -> " + to);
			return false;
		}

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(from);
			bos = new BufferedOutputStream(to);
			
			byte[] bin = new byte[1024];
			while (bis.read(bin, 0, bin.length) != -1) {
				bos.write(bin);
			}
			
			bos.flush();
			return true;

		} catch (IOException e) {
			e.printStackTrace();
			
		} finally {
			close(bis, bos, from, to);
		}

		return false;
	}

	/**
	 * 引数の {@link Closeable} をcloseする。
	 * 
	 * @param closes
	 */
	public static void close(Closeable... closes) {
		if (closes == null) {
			return;
		}

		for (Closeable c : closes) {
			try {
				if (closes != null) {
					c.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
