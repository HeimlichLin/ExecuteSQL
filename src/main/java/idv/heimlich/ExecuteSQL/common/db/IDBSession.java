package idv.heimlich.ExecuteSQL.common.db;

import idv.heimlich.ExecuteSQL.common.db.sql.SqlWhere;

import java.sql.Connection;
import java.util.List;

public interface IDBSession {
	
	void beginTransaction();
	
	void commit();

	void close();

	void rollback();

	int count(String sql);

	int executeUpdate(String sql);
	
	Connection getConnection();

	RowMapList query(String sql);
	
	RowMapList query(String sql, SqlWhere sqlWhere);
	
	<Po> List<Po> select(IConverter<Po> converter, String sql);
	
	<Po> List<Po> select(IConverter<Po> converter, String sql, SqlWhere sqlWhere);

}
