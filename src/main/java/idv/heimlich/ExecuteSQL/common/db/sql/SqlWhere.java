package idv.heimlich.ExecuteSQL.common.db.sql;

import idv.heimlich.ExecuteSQL.common.db.utils.SqlFormatUtil;
import idv.heimlich.ExecuteSQL.common.exception.ApBusinessException;

import java.util.HashMap;
import java.util.Map;

public class SqlWhere {
	
	private Map<String, String> values;

	protected SqlWhere() {
		this.values = new HashMap<String, String>();
	}

	protected SqlWhere(Map<String, String> value) {
		if (values == null) {
			throw new ApBusinessException(
					"The parameter of SqlWhere value cannot be null!");
		} else {
			this.values = value;
		}
	}

	public Map<String, String> toMap() {
		return this.values;
	}

	public String toString() {
		return this.values.toString();
	}

	protected void add(String key, String value) {
		if (key != null) {
			this.values.put(key.toUpperCase(), value);
		}
	}

	protected Object getObjectValue(String key) {
		Object value = null;
		if (key != null) {
			value = (this.values.get(key) != null ? this.values.get(key) : null);
		}
		return value;
	}

	protected String getString(String key) {
		String value = null;
		if (key != null) {
			value = (this.values.get(key) != null ? this.values.get(key)
					.toString() : null);
		}
		return value;
	}

	protected Object[] getKeys() {
		return this.values.keySet().toArray();
	}

	protected Object[] getValues() {
		return this.values.values().toArray();
	}

	protected void clear() {
		this.values.clear();
	}

	protected Object remove(Object key) {
		return this.values.remove(key);
	}

	protected int size() {
		return this.values.size();
	}

	protected String get() {
		if (values == null) {
			return "";
		}
		return SqlFormatUtil.whereMapToString(values);
	}

}
