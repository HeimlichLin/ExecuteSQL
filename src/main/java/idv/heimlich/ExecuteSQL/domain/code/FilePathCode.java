package idv.heimlich.ExecuteSQL.domain.code;

public enum FilePathCode {
	
	TXT("C:\\Users\\6284\\Desktop\\txt", "處理文字檔"), //
	TXT_FAIL("C:\\Users\\6284\\Desktop\\txt\\Fail", "處理文字檔失敗"), //
	TXT_SUCCESS("C:\\Users\\6284\\Desktop\\txt\\Success", "處理文字檔成功"), //
	
	EXCEL("C:\\Users\\6284\\Desktop\\Excel", "處理EXCEL"), //
	EXCEL_FAIL("C:\\Users\\6284\\Desktop\\Excel\\Fail", "處理EXCEL失敗"), //
	EXCEL_SUCCESS("C:\\Users\\6284\\Desktop\\Excel\\Success", "處理EXCEL成功"), //


	WORD("C:\\Users\\6284\\Desktop\\Word", "處理WORD"), //
	WORD_FAIL("C:\\Users\\6284\\Desktop\\Word\\Fail", "處理WORD失敗"), //
	WORD_SUCCESS("C:\\Users\\6284\\Desktop\\Word\\Success", "處理WORD成功"), //
	;
	
	final String cateogry;
	final String path;
	final String description;
	
	private FilePathCode(String path, String description) {
		this.cateogry = this.name();
		this.path = path;
		this.description = description;
	}

	public String getCateogry() {
		return cateogry;
	}

	public String getPath() {
		return path;
	}

	public String getDescription() {
		return description;
	}

}
