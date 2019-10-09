package idv.heimlich.ExecuteSQL.common.evn;


/**
 * 設定檔案來源
 */
public enum EVNSource {
	
	DEF_PROPERTIES("evn.properties") {//預設環境環境變數
		@Override
		public EVNConfigFactory getFactory() {
			return new EVNConfigFactoryImpl();
		}
	}, //
	;

	final String path;

	private EVNSource(String path) {
		this.path = path;
	}

	abstract public EVNConfigFactory getFactory();

	public String getPath() {
		return path;
	}

}
