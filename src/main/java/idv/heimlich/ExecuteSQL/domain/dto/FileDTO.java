package idv.heimlich.ExecuteSQL.domain.dto;

import java.io.File;

public class FileDTO {
	
	private File file;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	public String getFileName() {
		return this.file.getName();
	}
	
	public String getFilePath() {
		return this.file.getPath();
	}

}
