package idv.heimlich.ExecuteSQL.domain.service.impl;

import idv.heimlich.ExecuteSQL.common.db.IDBSession;
import idv.heimlich.ExecuteSQL.common.db.IDBSessionFactory;
import idv.heimlich.ExecuteSQL.common.db.impl.DBSessionFactoryImpl;
import idv.heimlich.ExecuteSQL.common.db.sql.SqlCode;
import idv.heimlich.ExecuteSQL.common.db.sql.SqlObject;
import idv.heimlich.ExecuteSQL.common.exception.TxBusinessException;
import idv.heimlich.ExecuteSQL.common.log.LogFactory;
import idv.heimlich.ExecuteSQL.domain.code.CommandCode;
import idv.heimlich.ExecuteSQL.domain.dto.CommandDTO;
import idv.heimlich.ExecuteSQL.domain.dto.FileDTO;
import idv.heimlich.ExecuteSQL.domain.service.ReadService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;

public class ReadServiceImpl implements ReadService {
	
	private static Logger LOGGER = LogFactory.getInstance();
	private IDBSessionFactory sessionFactory = new DBSessionFactoryImpl();

	@Override
	public void proFile(FileDTO dto) {
		try {
			LOGGER.debug(String.format("------ pro %s start ------", dto.getFileName()));
			CommandDTO commandDTO = this.load(dto);
			this.process(commandDTO);
			this.ckeckFile(commandDTO);
			LOGGER.debug(String.format("------ pro %s end ------", dto.getFileName()));			
		} catch (Exception e) { 
			LOGGER.debug("ReadServiceImpl proFile", e);
			throw new TxBusinessException("ReadServiceImpl proFile", e);
		}		
	}	
	
	/**
	 * FILE load
	 */
	private CommandDTO load(FileDTO dto) {
		String[] fileName = dto.getFileName().split("_");
		CommandDTO commandDTO = new CommandDTO();
		commandDTO.setTableName(fileName[2]);
		if (!dto.getFile().exists()) {
			LOGGER.info(String.format("[%s]: 路徑無此資料：[%s].", dto.getFilePath(), dto.getFileName()));
			throw new TxBusinessException(String.format("[%s]: 路徑無此資料：[%s].", dto.getFilePath(), dto.getFileName()));
		}
		return this.fileConvertCommandDto(dto.getFile(), commandDTO);
	}
	
	/**
	 * FILE轉換成指令DTO
	 */
	private CommandDTO fileConvertCommandDto(File file, CommandDTO dto) {
		try {
			FileReader reader = new FileReader(file);
			final BufferedReader br = new BufferedReader(reader);
			String line;
			while ((line = br.readLine()) != null) {
				String text = line.toUpperCase().trim();
				if (text.startsWith(CommandCode.SET.name())) {
					dto.setSetString(line.replace(";", ""));
				} else if(text.startsWith(CommandCode.DELETE.name())) {
					dto.setDelString(line.replace(";", ""));
				} else if(text.startsWith(CommandCode.INSERT.name())) {
					dto.getInsList().add(line.replace(";", ""));				
				} else if(text.startsWith(CommandCode.COMMIT.name())) {				
					dto.setComString(line.replace(";", ""));
				}
			}
			br.close();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	/**
	 * 指令處理
	 */
	private void process(CommandDTO dto) {
		this.deleteOldData(dto);
		this.insertNewData(dto);
	}
	
	/**
	 * 刪除原資料
	 */
	private int deleteOldData(CommandDTO dto){
		int count = this.getDBSession().executeUpdate(dto.getDelString());
		LOGGER.debug(String.format("delete [%s]", count) );
		return count;
	}
	
	/**
	 * insert新資料
	 */
	private int insertNewData(CommandDTO dto){
		try {
			int count = 0;
			this.getDBSession().beginTransaction();
			for (String sql : dto.getInsList()) {
				int num = this.getDBSession().executeUpdate(sql);
				count +=num;
			}
			this.getDBSession().commit();
			LOGGER.debug(String.format("insert [%s]", count) );
			return count;
		} catch (Exception e) {
			this.getDBSession().rollback();
			throw new TxBusinessException("ReadServiceImpl insertNewData fail", e);
		}
	}
	
	/**
	 *檢核insert內容
	 */
	private void ckeckFile(CommandDTO dto) {
		this.ckeckAboutCount(dto);
		this.ckeckDetail(dto);
	}
	
	/**
	 * 檢核insert筆數是否正確
	 */
	private void ckeckAboutCount(CommandDTO dto) {
		String sql = dto.getDelString().toUpperCase().replace(" DELETE ", " SELECT * ");
		int count = this.getDBSession().count(sql);
		if (count != dto.getInsList().size()) {
			throw new TxBusinessException(String.format("筆數不合:[%s]", count));
		}
		LOGGER.debug(String.format("筆數正確:[%s]", count));
	}
	
	/**
	 * 檢核insert的各筆數內容是否完全正確(避免亂碼、重複)
	 */
	private void ckeckDetail(CommandDTO dto) {
		for (String content : dto.getInsList()) {
			String sql = this.contentBecomeSql(dto.getTableName(), content);
			int count = this.getDBSession().count(sql);	
			if ((count) != 1) {
				throw new TxBusinessException(String.format("內容不合:[%s]", sql));
			}
		}
		LOGGER.debug("內容正確");
	}
	
	/**
	 * 取原insert sql產生select sql進行檢核
	 */
	private String contentBecomeSql(String tableName, String content) {
		String[] key = this.contentTakeOutKey(content);			
		String [] value = this.contentTakeOutValue(content);	
		SqlObject sqlObject = new SqlObject(tableName);	
		for (int i = 0; i < key.length; i++) {
			sqlObject.setSqlWhere(key[i], StringUtils.substringBeforeLast(StringUtils.substringAfter(value[i].trim(), "'"), "'"));
		}
		return SqlCode.creatSelectSql(sqlObject);
	}
	
	/**
	 * 取原insert sql擷取出key
	 */
	private String[] contentTakeOutKey(String content) {
		return StringUtils.substringBetween(content, "(", ")").split(",");
	}
	
	/**
	 * 取原insert sql擷取出value
	 */
	private String[] contentTakeOutValue(String content) {
		String contentWithoutKey = StringUtils.substringAfter(content,")");
		String contentCleanHalf = StringUtils.substringAfter(contentWithoutKey, "(");
		String contentCleanAll = StringUtils.substringBeforeLast(contentCleanHalf, ")");			
		return contentCleanAll.split(",");
	}
	
	private IDBSession getDBSession() {
		return sessionFactory.getXdaoSession("");
	}

}
