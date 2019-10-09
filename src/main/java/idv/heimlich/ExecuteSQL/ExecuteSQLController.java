package idv.heimlich.ExecuteSQL;

import idv.heimlich.ExecuteSQL.common.code.ProStatus;
import idv.heimlich.ExecuteSQL.common.log.LogFactory;
import idv.heimlich.ExecuteSQL.domain.BaseReadFileController;
import idv.heimlich.ExecuteSQL.domain.code.FilePathCode;
import idv.heimlich.ExecuteSQL.domain.dto.FileDTO;
import idv.heimlich.ExecuteSQL.domain.service.ReadService;
import idv.heimlich.ExecuteSQL.domain.service.impl.ReadServiceImpl;

import java.io.File;

import org.slf4j.Logger;

public class ExecuteSQLController extends BaseReadFileController {
	
	private static Logger LOGGER = LogFactory.getInstance();

	public ExecuteSQLController() {
		super(FilePathCode.TXT);
	}

	@Override
	protected ProStatus proFiles(File file) {
		try {
			ReadService service = new ReadServiceImpl();		
			FileDTO dto = new FileDTO();
			dto.setFile(file);
			service.proFile(dto);
			return ProStatus.FINISH;
		} catch (Exception e) {
			return ProStatus.FAIL;
		}
	}

}
