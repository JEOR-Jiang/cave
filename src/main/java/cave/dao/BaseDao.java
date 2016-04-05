package cave.dao;

import cave.utils.Page;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 定义了几种比较常用的方法,可扩展
 * @author Jeor
 *
 */
public interface BaseDao<T> {
	
	public void saveOrUpdate(T entity);
	public void save(T entity);
	public void delete(T entity);
	public void update(T entity);
	public T get(Class<T> clazz, int id);
	public Page<T> getByPage(Page<T> page, String countHql, String selHql, Object... vals);
	public void deleteById(Class<T> clazz, int id);
	
	/**
	 * 根据hql语句拿到对应的字符串字段
	 * @param hql
	 * @return
	 */
	public String getStringByHql(String hql, Object... vals);
	
	/**
	 * 根据hql语句拿到对应的整形字段
	 * @param hql
	 * @return
	 */
	public Integer getIntegerByHql(String hql, Object... vals);
	
	public void deleteByIds(Class<T> clazz, Collection<Integer> ids);
	
	/**
	 * 根据hql统计一些记录
	 * @param hql
	 * @return
	 */
	public long countRecord(String hql, Object... vals);
	
	/**
	 * 根据hql拿到一个List<Object[]>放在Page里面
	 * 如果countHql为空，则不统计记录数
	 * @param page
	 * @param countHql
	 * @param selHql
	 * @return
	 */
	@SuppressWarnings({"rawtypes" })
	public Page<T> getByObjectPage(Page page, String countHql, String selHql, Object... vals);
	
	/**
	 * 根据hql查询持久化对象
	 * @param hql
	 * @return
	 */
	public Object getObjectByHql(String hql, Object... vals);
	
	/**
	 * 根据hql查询返回一列对象列表
	 * @param hql
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getListByHql(String hql, Object... vals);
	
	/**
	 * 根据hql更新实体
	 * @param hql
	 */
	public void updateByHql(String hql, Object... vals);
	
	/**
	 * 用SQL实现分页
	 * @param page
	 * @param countSql
	 * @param selSql
	 * @return
	 */
	public Page<T> getPageBySQL(Page page, Class clazz, String countSql, String selSql, Object... vals);
	
	/**
	 * 用于使用SQL查询，返回具体的字段集
	 * @param sql
	 * @return
	 */
	public List getListBySQL(String sql, Object... vals);
	
	/**根据SQL获取对象
	 * 
	 * @param sql
	 * @return
	 */
	public Object getObjectBySQL(String sql, Object... vals);
	
	/**用于SQL查询记录数
	 * 
	 * @param sql
	 * @return
	 */
	public long countSQLRecord(String sql, Object... vals);
	
	/**
	 * 拿到指定大小的list
	 * @param selHql
	 * @param size
	 * @return
	 */
	public List<Object> getListLimitSize(String selHql, int size, Object... vals);
	
	/**
	 * 创建SqlQuery对象
	 * @param sql
	 * @return Query
	 */
	public SQLQuery createSqlQuery(String sql, Object... vals);
	
	/**
	 * 创建Query对象
	 * @param hql
	 * @return Query
	 */
	public Query createQuery(String hql, Object... vals);
	
	/**
	 * 添加实体对象
	 * @param entity 实体对象
	 * @return  实体序列化id
	 */
	public Serializable addEntity(T entity);
	
	/**
	 * 查询实体对象(get)
	 * @param clazz  实体类型
	 * @param id     实体id
	 * @return       实体对象
	 */
	public T getEntity(Class<T> clazz, Serializable id);
	
	/**
	 * 执行hql返回一个想要的对象<br>
	 * 多个请使用，queryAll(String hql, List<Object> list)
	 * @param hql
	 * @param list
	 * @return
	 */
	public Object getEntity(String hql, List<Object> list);
	
	/**
	 * 获取实体列表
	 * @param clazz 实体类型
	 * @return 实体列表
	 */
	public List<T> queryAll(Class<T> clazz);
	
	/**
	 * 根据exampleEntity中有值的属性进行查询
	 * @param exampleEntity
	 * @return list
	 */
	public List<T> findByExample(T exampleEntity);
	
	/**
	 * 获取结果集<br>
	 * 1条结果集，请使用getEntity(String hql, List<Object> list)
	 * @param hql
	 * @param list 条件参数
	 * @return
	 */
	public List<Object> queryAll(String hql, List<Object> list);
	
	/**
	 * 根据条件分页操作
	 * @param hql           数据库语句
	 * @param startPageNum  开始条数
	 * @param pageSize      每页显示条数
	 * @param list 			参数集(HQL拼接的？参数)
	 * @return              一页的数据 list
	 */
	public List<T> list(String hql, int startPageNum, int pageSize, List<Object> list);
	
	/**
	 * 根据条件动态删除数据
	 * @param clazz	 实体类
	 * @param whereName 条件的字段名
	 * @param whereValue 条件值
	 * @throws DAOException
	 */
	public boolean delEntity(String hql, Object... vals);
	
	/**动态修改单表字段值
	 * 
	 * @param hql
	 * @param vals
	 * @return
	 */
	public boolean updateEntity(String hql, Object... vals);
}
