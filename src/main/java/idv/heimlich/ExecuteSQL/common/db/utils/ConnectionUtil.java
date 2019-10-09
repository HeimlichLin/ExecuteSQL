package idv.heimlich.ExecuteSQL.common.db.utils;

import idv.heimlich.ExecuteSQL.common.evn.EVNConfigProducer;
import idv.heimlich.ExecuteSQL.common.log.LogFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;

public class ConnectionUtil{

	private static Logger LOGGER = LogFactory.getInstance();

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(EVNConfigProducer.getConfig().getDriverClass());
			conn = DriverManager
					.getConnection(EVNConfigProducer.getConfig()
							.getConnectionIP(), EVNConfigProducer.getConfig()
							.getUserName(), EVNConfigProducer.getConfig()
							.getPassword());
		} catch (final SQLException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (final ClassNotFoundException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return conn;
	}
	
	public static void close(Connection conn, PreparedStatement ps, ResultSet rs) 
			throws SQLException {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
