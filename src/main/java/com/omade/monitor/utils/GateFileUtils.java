package com.omade.monitor.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

public class GateFileUtils {

	private static final Logger logger = LoggerFactory
			.getLogger(GateFileUtils.class);
	private static final int MAXSIZE = 1024 * 1024;

	public static String newVersion(String filePath) {

		File file = new File(filePath);

		if (!file.exists()) {
			throw new IllegalStateException("client file folder not config");
		}

		File[] listFiles = file.listFiles();

		if (listFiles != null) {

			String[] versions = new String[listFiles.length];
			int i = 0;
			for (File file2 : listFiles) {
				String name = file2.getName();
				String substring = name.substring(name.lastIndexOf("-") + 1,
						name.lastIndexOf("."));
				versions[i++] = substring;

			}
			Arrays.sort(versions);

			return versions[versions.length - 1];
		}
		throw new IllegalStateException("no client file !!!");

	}

	public static void main(String[] args) {

		String newVersion = newVersion("D:\\workspace_\\stargate\\client");
		logger.info(newVersion);
	}

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
			logger.info(filePath + "create");
		}
	}

	public static boolean folderExist(String filePath) {

		Preconditions.checkArgument(!Strings.isNullOrEmpty(filePath));

		File file = new File(filePath);
		if (file.isDirectory() && file.exists()) {
			logger.info(filePath + "exist");
			return true;
		} else {
			logger.info(filePath + "not exist");
			return false;
		}
	}

	public static int getMaxsize() {
		return MAXSIZE;
	}

}
