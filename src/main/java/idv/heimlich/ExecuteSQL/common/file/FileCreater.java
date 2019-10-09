package idv.heimlich.ExecuteSQL.common.file;

import idv.heimlich.ExecuteSQL.common.exception.ApBusinessException;
import idv.heimlich.ExecuteSQL.common.log.LogFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;

public class FileCreater {
	
	private static Logger LOGGER = LogFactory.getInstance();
	
	private File file;
	private List<String> contents;
	private boolean isAutoCreate = true;// 自動建立相關資料夾位置
	
	public FileCreater(final File file, final List<String> contents) {
		super();
		this.file = file;
		this.contents = contents;
	}

	public void execute() {
		try {
			if (this.isAutoCreate) {
				this.createFileParentFolder();
			}

			final FileWriter writer = new FileWriter(this.file);
			for (final String line : this.contents) {
				writer.write(line);
			}
			writer.flush();
			writer.close();
			LOGGER.debug("建立檔案成功:{}", this.file.getAbsolutePath());
		} catch (final IOException e) {
			throw new ApBusinessException("執行失敗", e);
		}
	}

	public void unio() {
		if (this.file.exists()) {
			this.file.delete();
		}
	}
	
	private void createFileParentFolder() {
		if (!this.file.exists()) {
			this.file.getParentFile().mkdirs();
		} else if (this.file.exists()) {
			LOGGER.debug("檔案存在將使用檔案覆蓋之方式產製檔案");
			this.file.delete();
		}
	}

}
