package cave.utils;

import org.hibernate.Hibernate;
import org.hibernate.dialect.SQLServerDialect;
import java.sql.Types;


/**
 * 自定义Hibernate方言
 */
public class MSSQLDialect extends SQLServerDialect {
	/**
	 * 必须要加入nvarchar类型。默认的SqlServer方言不支持nvarchar类型。
	 */
	public MSSQLDialect(){
		super();
		registerHibernateType(Types.NVARCHAR, Hibernate.STRING.getName());
	}

}
