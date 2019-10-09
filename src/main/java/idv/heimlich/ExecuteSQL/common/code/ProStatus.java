package idv.heimlich.ExecuteSQL.common.code;

import idv.heimlich.ExecuteSQL.domain.code.FilePathCode;

import java.io.File;

public enum ProStatus {
	
	FINISH {
		@Override
		public void move(final FilePathCode filePathCode, final File file) {
			final File moveFile = ProStatus.createFile(FilePathCode.TXT_SUCCESS.getPath(), file.getName());
			file.renameTo(moveFile);
			
			// 測試複製
//			Path sourcePath      = Paths.get(FilePathCode.TXT_SUCCESS.getPath());
//			Path destinationPath = Paths.get(FilePathCode.TXT_FAIL.getPath());
//			try {
//				Files.copy(sourcePath, destinationPath);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	}, //
	
	FAIL {
		@Override
		public void move(final FilePathCode filePathCode, final File file) {
			final File moveFile = ProStatus.createFile(FilePathCode.TXT_FAIL.getPath(), file.getName());
			file.renameTo(moveFile);
		}
	} //
	
	;
	
	public abstract void move(final FilePathCode filePathCode, final File f);
	
	private static File createFile(String filepath, String fileName) {
		final File moveFile = new File(filepath, fileName);
		ProStatus.createFileParentFolder(moveFile);
		return moveFile;
	}
	
	private static void createFileParentFolder(File file) {
		if (!file.exists()) {
			file.getParentFile().mkdirs();
//		} else if (file.exists()) {
//			file.delete();
		}
	}

}
