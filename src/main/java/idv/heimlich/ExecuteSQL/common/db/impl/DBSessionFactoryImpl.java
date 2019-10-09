package idv.heimlich.ExecuteSQL.common.db.impl;

import idv.heimlich.ExecuteSQL.common.db.DBSessionManager;
import idv.heimlich.ExecuteSQL.common.db.IDBSession;
import idv.heimlich.ExecuteSQL.common.db.IDBSessionFactory;
import idv.heimlich.ExecuteSQL.common.db.code.DBConfig;
import idv.heimlich.ExecuteSQL.common.log.LogFactory;

import java.util.Objects;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;

public class DBSessionFactoryImpl implements IDBSessionFactory {
	
	private static Logger LOGGER = LogFactory.getInstance();

	@Override
	public IDBSession getXdaoSession(String conn) {
		final String connid = StringUtils.defaultIfEmpty(conn, DBSessionManager.PROP_DEFAULT_CONN_ID);
		Objects.requireNonNull(connid, "無此定義coonid" + conn);
		DBConfig dbConfig = DBConfig.valueOf(connid);
//		LOGGER.debug("use connid:" + connid);
		return dbConfig.getXdaoSession();
	}

}
