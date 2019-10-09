package idv.heimlich.ExecuteSQL.common.evn;


public class EVNConfigProducer {
	
	/**
	 * 取得設定檔案
	 */
	public static IEVNConfig getConfig() {
		return getFactory(EVNSource.DEF_PROPERTIES).getConfig();
	}

	public static EVNConfigFactory getFactory() {
		return getFactory(EVNSource.DEF_PROPERTIES);
	}
	
	public static EVNConfigFactory getFactory(EVNSource type) {
		return type.getFactory();
	}

}
