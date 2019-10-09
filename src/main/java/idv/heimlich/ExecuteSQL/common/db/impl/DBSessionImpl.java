package idv.heimlich.ExecuteSQL.common.db.impl;

import idv.heimlich.ExecuteSQL.common.db.IConverter;
import idv.heimlich.ExecuteSQL.common.db.IDBSession;
import idv.heimlich.ExecuteSQL.common.db.RowMap;
import idv.heimlich.ExecuteSQL.common.db.RowMapList;
import idv.heimlich.ExecuteSQL.common.db.sql.SqlWhere;
import idv.heimlich.ExecuteSQL.common.db.utils.ConnectionUtil;
import idv.heimlich.ExecuteSQL.common.exception.TxBusinessException;
import idv.heimlich.ExecuteSQL.common.log.LogFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;

public class DBSessionImpl implements IDBSession {
	
	private static Logger LOGGER = LogFactory.getInstance();
	private Connection connection;
	
	private Connection initial() {
		if (this.connection == null) {
			this.connection = ConnectionUtil.getConnection();
		}
		return this.connection;
	}

	@Override
	public Connection getConnection() {
		return this.initial();
	}

	@Override
	public void beginTransaction() {
		try {
			this.initial().setAutoCommit(false);
		} catch (final SQLException e) {
			LOGGER.debug("beginTransaction fail", e);
			throw new TxBusinessException("beginTransaction fail", e);
		}
	}

	@Override
	public void commit() {
		try {
			this.initial().commit();
		} catch (final SQLException e) {
			LOGGER.debug("commit fail", e);
			throw new TxBusinessException("commit fail", e);
		}
	}

	@Override
	public void close() {
		try {
			this.initial().close();
		} catch (final SQLException e) {
			LOGGER.debug("close fail", e);
			throw new TxBusinessException("close fail", e);
		}
	}
	
	@Override
	public void rollback() {
		try {
			this.initial().rollback();
			this.initial().close();
		} catch (final SQLException e) {
			LOGGER.debug("rollback fail", e);
			throw new TxBusinessException("rollback fail", e);
		}
	}

	@Override
	public RowMapList query(String sql) {
		try {
			final Connection connection = this.initial();
			final PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			final ResultSet resultSet = preparedStatement.executeQuery();
			RowMapList result = this.result2RowMapList(resultSet);
			return result;
		} catch (final Exception e) {
			LOGGER.debug("query fail", e);
			throw new TxBusinessException("query fail", e);
		}
	}

	@Override
	public RowMapList query(String sql, SqlWhere sqlWhere) {
		try {
			final Connection connection = this.initial();
			final PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			for (String key : sqlWhere.toMap().keySet()) {
				preparedStatement.setString(Integer.parseInt(key), sqlWhere.toMap().get(key));
			}
			final ResultSet resultSet = preparedStatement.executeQuery();
			RowMapList result = this.result2RowMapList(resultSet);
			return result;
		} catch (final Exception e) {
			LOGGER.debug("query fail", e);
			throw new TxBusinessException("query fail", e);
		}
	}

	@Override
	public <Po> List<Po> select(IConverter<Po> converter, String sql) {
		final RowMapList rowMapList = this.query(sql);
		final List<Po> pos = new ArrayList<Po>();
		final Iterator<RowMap> rowMapIterator = rowMapList.iterator();
		while (rowMapIterator.hasNext()) {
			pos.add(converter.convert(rowMapIterator.next()));
		}
		return pos;
	}

	@Override
	public <Po> List<Po> select(IConverter<Po> converter, String sql,
			SqlWhere sqlWhere) {
		final RowMapList rowMapList = this.query(sql, sqlWhere);
		final List<Po> pos = new ArrayList<Po>();
		final Iterator<RowMap> rowMapIterator = rowMapList.iterator();
		while (rowMapIterator.hasNext()) {
			pos.add(converter.convert(rowMapIterator.next()));
		}
		return pos;
	}
	
	public int count(final String sql) {		
		try {
			final Connection connection = this.initial();
			final PreparedStatement preparedStatement = connection
					.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			final ResultSet resultSet =  preparedStatement.executeQuery(sql);	
			return (resultSet.last() == true)?resultSet.getRow():0;
		} catch (SQLException e) {
			LOGGER.debug("count fail", e);
			throw new TxBusinessException("count fail", e);
		}
	}
	
	@Override
	public int executeUpdate(final String sql) {		
		try {
			final Connection connection = this.initial();
			final PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			return preparedStatement.executeUpdate(sql);			
		} catch (SQLException e) {
			LOGGER.debug("executeUpdate fail", e);
			throw new TxBusinessException("executeUpdate fail", e);
		}
	}
	
	private RowMapList result2RowMapList(final ResultSet resultSet) {
		int count;
		try {
			final ResultSetMetaData rsmd = resultSet.getMetaData();
			count = rsmd.getColumnCount();
			final RowMapList rowMapList = new RowMapList();
			while (resultSet.next()) {
				final RowMap rowMap = new RowMap();
				for (int i = 1; i <= count; i++) {
					rowMap.setValue(rsmd.getColumnName(i),
							resultSet.getObject(i));
				}
				rowMapList.add(rowMap);
			}
			return rowMapList;
		} catch (final Exception e) {
			LOGGER.debug("result2RowMapList fail", e);
			throw new TxBusinessException("result2RowMapList fail", e);
		}
	}

}
