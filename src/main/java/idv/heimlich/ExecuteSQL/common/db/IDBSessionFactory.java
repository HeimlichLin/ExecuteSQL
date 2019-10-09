package idv.heimlich.ExecuteSQL.common.db;

public interface IDBSessionFactory {

	IDBSession getXdaoSession(String conn);

}
