package idv.heimlich.ExecuteSQL.domain.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 檔案解析後格式
 */
public class CommandDTO {
	
	private String tableName; // 
	private String setString; // set指令
	private String delString; // 刪除指令
	private List<String> insList = new ArrayList<String>(); // 寫入格式
	private String comString; // 提交指令

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getSetString() {
		return setString;
	}

	public void setSetString(String setString) {
		this.setString = setString;
	}

	public String getDelString() {
		return delString;
	}

	public void setDelString(String delString) {
		this.delString = delString;
	}

	public List<String> getInsList() {
		return insList;
	}

	public void setInsList(List<String> insList) {
		this.insList = insList;
	}

	public String getComString() {
		return comString;
	}

	public void setComString(String comString) {
		this.comString = comString;
	}

}
