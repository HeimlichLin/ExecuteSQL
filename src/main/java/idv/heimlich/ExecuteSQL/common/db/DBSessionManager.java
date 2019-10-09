package idv.heimlich.ExecuteSQL.common.db;

public class DBSessionManager extends AbstractDBSessionManager {
	
	public static final String PROP_DEFAULT_CONN_ID = "PCLMSPool"; // "apConn";

	@Override
	protected String getConnId() {
		return PROP_DEFAULT_CONN_ID;
	}

}
