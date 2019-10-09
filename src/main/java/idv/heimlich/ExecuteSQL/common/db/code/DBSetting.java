package idv.heimlich.ExecuteSQL.common.db.code;

public enum DBSetting {
	
	DRIVER_CLASS_NAME("jdbc.driverClassName"), //
	URL("jdbc.url"), //
	USERNAME("jdbc.username"), //
	PASSWORD("jdbc.password"), //
	;
	
	final String name;
	final String code;

	private DBSetting(String code) {
		this.name = name();
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public String toText() {
		return code;
	}

}
