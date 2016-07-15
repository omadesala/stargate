package com.omade.monitor.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

public class KettleFileUtils {

	private static final Logger LOG = LoggerFactory
			.getLogger(KettleFileUtils.class);
	private static final int MAXSIZE = 1024 * 1024;

	public static void write(String filename, String line) {

		Preconditions.checkArgument(!Strings.isNullOrEmpty(filename));
		Preconditions.checkArgument(!Strings.isNullOrEmpty(line));

		FileWriter fw = null;
		try {
			fw = new FileWriter(filename, true);
			String s = line;
			fw.write(s, 0, s.length());
			fw.flush();
			OutputStreamWriter osw = new OutputStreamWriter(
					new FileOutputStream("hello2.txt"));
			osw.write(s, 0, s.length());
			osw.flush();
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(
					new FileOutputStream("hello3.txt")), true);
			pw.println(s);

			pw.close();
			osw.close();
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void write(String filename, String line, boolean append) {

		Preconditions.checkArgument(!Strings.isNullOrEmpty(filename),
				"file name is null");
		Preconditions.checkArgument(!Strings.isNullOrEmpty(line),
				"content is null");

		FileWriter fw = null;
		try {
			fw = new FileWriter(filename, append);
			fw.write(line, 0, line.length());
			fw.flush();
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<String> read(String filePath) {

		List<String> lines = Lists.newArrayList();

		try {
			lines = FileUtils.readLines(new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return lines;
	}

	public static void delete(String filePath) {

		Preconditions.checkArgument(!Strings.isNullOrEmpty(filePath));

		File file = new File(filePath);
		if (file.isFile() && file.exists()) {
			file.delete();
		}
	}

	public static void createDir(String filePath) {

		Preconditions.checkArgument(!Strings.isNullOrEmpty(filePath));
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
			LOG.info(filePath + "create");
		}
	}

	public static boolean folderExist(String filePath) {

		Preconditions.checkArgument(!Strings.isNullOrEmpty(filePath));

		File file = new File(filePath);
		if (file.isDirectory() && file.exists()) {
			LOG.info(filePath + "exist");
			return true;
		} else {
			LOG.info(filePath + "not exist");
			return false;
		}
	}

	public static int getMaxsize() {
		return MAXSIZE;
	}

}
