package idv.heimlich.ExecuteSQL.common.db.sql;

import idv.heimlich.ExecuteSQL.common.db.utils.SqlFormatUtil;

import java.util.ArrayList;
import java.util.List;

public class SqlColumn {

	private List<String> list;

	protected SqlColumn() {
		this.list = new ArrayList<String>();
	}

	protected void add(String column) {
		this.list.add(column);
	}

	protected int length() {
		return list.size();
	}

	protected String get(int i) {
		return this.list.get(i);
	}

	protected String get() {
		if (list.isEmpty()) {
			return " * ";
		}
		return SqlFormatUtil.selectListToString(list);
	}
	
}
