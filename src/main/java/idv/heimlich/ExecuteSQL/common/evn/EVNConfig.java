package idv.heimlich.ExecuteSQL.common.evn;

import idv.heimlich.ExecuteSQL.common.db.code.DBSetting;
import idv.heimlich.ExecuteSQL.common.file.FileLoader;
import idv.heimlich.ExecuteSQL.common.log.LogFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;

public class EVNConfig implements IEVNConfig {
	
	private static Logger LOGGER = LogFactory.getInstance();
	private Properties properties;

	@Override
	public String getDriverClass() {		
		return this.getString(DBSetting.DRIVER_CLASS_NAME.toText());
	}
	
	@Override
	public String getConnectionIP() {
		return this.getString(DBSetting.URL.toText());
	}

	@Override
	public String getUserName() {
		return this.getString(DBSetting.USERNAME.toText());
	}

	@Override
	public String getPassword() {
		return this.getString(DBSetting.PASSWORD.toText());
	}
	
	public Properties inti() {
		if (this.properties == null) {			
//			final ClassLoader classLoader = EVNConfig.class.getClassLoader();
//			final InputStream io = classLoader
//					.getResourceAsStream(EVNSource.DEF_PROPERTIES.getPath());			
			File file = FileLoader.getResourcesFile(EVNConfig.class, EVNSource.DEF_PROPERTIES.getPath());
			this.properties = new Properties();
			try {
//				this.properties.load(new InputStreamReader(io, "utf-8"));
				this.properties.load(new FileInputStream(file));
			} catch (final FileNotFoundException ex) {
				ex.printStackTrace();
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
		}
		return this.properties;
	}
	
	private String getString(String key, String def) {
		String value = this.getString(key);
		return StringUtils.defaultString(value, def);
	}

	private String getString(String key) {
		final Properties properties = inti();
		final String value = ((String) properties.get(key)).trim();
		LOGGER.debug(key + ":" + value);
		return value.trim();
	}

}
