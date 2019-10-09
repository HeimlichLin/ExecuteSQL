package idv.heimlich.ExecuteSQL.common.file;

import idv.heimlich.ExecuteSQL.common.utils.TextMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReaderUtil {

	public static List<String> readLines(File file) {
		List<String> lines = new ArrayList<String>();
		try {
			FileReader reader = new FileReader(file);
			final BufferedReader br = new BufferedReader(reader);
			String line;
			while ((line = br.readLine()) != null) {
				lines.add(line);
			}
			br.close();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}

	public static <T> List<T> readLines(TextMapper<T> mapper, File file) {
		final List<T> list = new ArrayList<T>();
		final List<String> lineStrings = readLines(file);

		for (String line : lineStrings) {
			try {
				T t = mapper.toVo(line);
				if (t != null) {
					list.add(t);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

}
