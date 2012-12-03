package com.ushi.lib.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileUtil {

	private static Logger logger = Logger.getLogger(FileUtil.class
			.getSimpleName());

	private FileUtil() {
	}

	/**
	 * ファイルをコピーする。
	 *
	 * @param fromPath コピー元ファイルパス
	 * @param toPath コピー先ファイルパス
	 * @return コピーできたらtrue
	 */
	public static boolean copy(String fromPath, String toPath) {
		if (fromPath == null || toPath == null) {
			logger.log(Level.SEVERE, "files if null. " + fromPath + " -> " + toPath);
			return false;
		}

		return copy(new File(fromPath), new File(toPath));
	}

	/**
	 * ファイルをコピーする。
	 *
	 * @param from コピー元
	 * @param to コピー先
	 * @return コピーできたらtrue
	 */
	public static boolean copy(File from, File to) {
		if (from == null || to == null) {
			logger.log(Level.SEVERE, "files if null. " + from + " -> " + to);
			return false;
		}

		FileChannel out = null;
		FileChannel in = null;
		try {
			out = new FileOutputStream(to).getChannel();
			in = new FileInputStream(from).getChannel();

			long pos = 0;
			long size = in.size() - pos;

			return out.transferFrom(in, pos, size) == size;

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

}
