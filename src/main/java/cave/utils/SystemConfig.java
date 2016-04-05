package cave.utils;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Jeor
 * 系统属性配置	
 */
public class SystemConfig implements InitializingBean {
	

	public static  String hostUrl;     //网站地址
	public static  String jsCssVersion;      //js/css的版本，用于识别缓存




	public static void init(){
		hostUrl = SystemConfig.getProperty("host.url");

		jsCssVersion = SystemConfig.getProperty("js.css.version");
	}

	@Autowired
	private PropertiesFactoryBean propertiesFactoryBean ;
	private static Map<String,String> properties = new HashMap<String,String>();

	@Override
	public void afterPropertiesSet() throws Exception {
		Properties props = propertiesFactoryBean.getObject() ;
		for(String key:props.stringPropertyNames()){
			properties.put(key,props.getProperty(key));
		}

		SystemConfig.init();

	}

	public static Map<String, String> getProperties() {
		return properties;
	}

	public static String getProperty(String key){
		return properties.get(key);
	}
}