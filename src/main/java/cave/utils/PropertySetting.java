package cave.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Jeor on 2016/5/6.
 */
public class PropertySetting implements InitializingBean {

    @Value("${spring.profiles.active}")
    private String profile;
    @Autowired
    private PropertiesFactoryBean propertiesFactoryBean;
    private static Map<String,String> properties=new HashMap<String, String>();

    public void afterPropertiesSet() throws Exception {
        this.initSetting();
    }
    public void initSetting()throws Exception{
        System.out.println("starting.......: with "+profile);
        Properties props = propertiesFactoryBean.getObject() ;
        for(String key:props.stringPropertyNames()){
            properties.put(key,props.getProperty(key));
        }
        System.out.println("properties:"+properties);
    }

    public static Map<String, String> getProperties() {
        return properties;
    }
    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }
}
