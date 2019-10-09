package idv.heimlich.ExecuteSQL.common.evn;

/**
 * 環境設定檔案介面
 */
public interface IEVNConfig {
	
	public String getDriverClass();
	
	/**
	 * 對應IP
	 * 
	 * @return
	 */
	public String getConnectionIP();

	/**
	 * 使用者帳號
	 * 
	 * @return
	 */
	public String getUserName();

	/**
	 * 密碼
	 * 
	 * @return
	 */
	public String getPassword();

}
