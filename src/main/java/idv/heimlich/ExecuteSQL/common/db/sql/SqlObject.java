package idv.heimlich.ExecuteSQL.common.db.sql;

public class SqlObject {
	
	private SqlColumn sqlColumn; // 查詢欄位
	private SqlWhere sqlWhere; // 查詢條件
	private SqlValue sqlValue; // insert的value
	private SqlSet sqlSet; // update的value
	private SqlOrderBy sqlOrderBy; // 排序方式
	private String sqlTableName; // table的名稱
	
	public SqlObject(String sqlTableName) {
		this.sqlTableName = sqlTableName;
		this.sqlColumn = new SqlColumn();
		this.sqlWhere = new SqlWhere();
		this.sqlValue = new SqlValue();
		this.sqlSet = new SqlSet();
		this.sqlOrderBy = new SqlOrderBy();
	}
	
	public String getSqlColumn() {
		return sqlColumn.get();
	}
	
	public void setSqlColumn(String string) {
		this.sqlColumn.add(string);
	}
	
	public String getSqlWhere() {
		return sqlWhere.get();
	}
	
	public void setSqlWhere(String key, String value) {
		this.sqlWhere.add(key, value);
	}
	
	public String getSqlValue() {
		return sqlValue.get();
	}

	public void setSqlValue(String key, String value) {
		this.sqlValue.add(key, value);
	}

	public String getSqlSet() {
		return sqlSet.get();
	}
	
	public void setSqlSet(String key, String value) {
		this.sqlSet.add(key, value);
	}
	
	public String getSqlOrderBy() {
		return sqlOrderBy.get();
	}
	
	public void setSqlOrderBy(String string) {
		this.sqlOrderBy.add(string);
	}
	
	public void setSqlOrderBy(String string, String style) {
		this.sqlOrderBy.add(string, style);
	}

	public String getSqlTableName() {
		return sqlTableName;
	}

}
