package idv.heimlich.ExecuteSQL.common.db.code;

public enum DBCommand {
	
	SELECT() { // 查詢
		@Override
		public String getSql() {			
			return " select %s from %s %s %s ";
		}
	},
	INSERT() { // 新增
		@Override
		public String getSql() {			
			return " insert into %s %s ";
		}
	},
	UPDATE() { // 修改
		@Override
		public String getSql() {			
			return " update %s %s %s ";
		}
	},
	DELETE() { // 刪除
		@Override
		public String getSql() {			
			return " delete from %s %s";
		}
	},
	;
	
	final String command;
	
	private DBCommand(){
		this.command = name();
	}
	
	public abstract String getSql();

}
