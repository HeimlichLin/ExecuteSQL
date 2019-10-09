package idv.heimlich.ExecuteSQL.common.db;

import java.util.HashMap;
import java.util.Map;

public class RowMap {
	
	final Map<String, Object> map = new HashMap<String, Object>();

	public void setValue(String key, Object value) {
		if (key != null) {
			this.map.put(key, value);
		}
	}

	public Object getValue(String key) {
		return key != null ? this.map.get(key) : null;
	}
	
	public String getString(String key) {
		if (key != null) {
			Object value = this.getValue(key);
			if (value != null) {
				return value.toString();
			}
		}
		return null;
	}
	
	public int getInt(String key) {
		int n = 0;
		if (key != null) {
			n = Integer.parseInt(this.getString(key));
		}
		return n;
	}

}
