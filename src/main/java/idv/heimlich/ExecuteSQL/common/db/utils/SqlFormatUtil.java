package idv.heimlich.ExecuteSQL.common.db.utils;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class SqlFormatUtil {
	
	public static String selectListToString(List<String> list) {
		if (list.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (String string : list) {
			if (first) {
				first = false;
			} else {
				sb.append(",");
			}
			sb.append(string);
		}
		return sb.toString();
	}
	
	public static String whereMapToString(Map<String, String> values) {
		if (values.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (String key : values.keySet()) {
			if (first) {
				first = false;
				sb.append(" where ");
			} else {
				sb.append(" and ");
			}
//			sb.append(key + " = '" + values.get(key) + "'");
			sb.append(StringUtils.isBlank(values.get(key))? key + " is null ":key + " = '" + values.get(key) + "'");
			
		}
		return sb.toString();
	}
	
	public static String setMapToString(Map<String, String> values) {
		if (values.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (String key : values.keySet()) {
			if (first) {
				first = false;
				sb.append(" set ");
			} else {
				sb.append(", ");
			}
			sb.append(key + " = '" + values.get(key) + "'");
		}
		return sb.toString();
	}
	
	public static String valueMapToString(Map<String, String> values) {
		if (values.isEmpty()) {
			return "";
		}
		StringBuilder result = new StringBuilder();
		StringBuilder insertKey = new StringBuilder();
		StringBuilder insertValue = new StringBuilder();
		boolean first = true;
		for (String key : values.keySet()) {
			if (first) {
				first = false;
			} else {
				insertKey.append(", ");
				insertValue.append(", ");
			}
			insertKey.append(key);
			insertValue.append("'" + values.get(key) + "'");			
		}
		return result.append(" (" + insertKey + ") ").append(" values (" + insertValue + ") ").toString();
	}
	
	public static String orderByeMapToString(Map<String, String> values) {
		if (values.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (String key : values.keySet()) {
			if (first) {
				first = false;
				sb.append(" order by ");
			} else {
				sb.append(", ");
			}
			sb.append(key + " " + StringUtils.defaultIfEmpty(values.get(key), ""));
		}
		return sb.toString();
	}

}
