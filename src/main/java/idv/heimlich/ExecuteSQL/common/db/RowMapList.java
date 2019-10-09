package idv.heimlich.ExecuteSQL.common.db;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RowMapList {
	
	private List<RowMap> rowMapList = new ArrayList<RowMap>();

	public void add(RowMap rowMap) {
		this.rowMapList.add(rowMap);
	}
	
	public void remove(RowMap rowMap){
		this.rowMapList.remove(rowMap);
	}
	
	public Iterator<RowMap> iterator() {
		return this.rowMapList.iterator();
	}

}
