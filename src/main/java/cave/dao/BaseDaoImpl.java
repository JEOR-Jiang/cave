package cave.dao;

import cave.utils.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.connection.ConnectionProvider;
import org.hibernate.engine.SessionFactoryImplementor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.*;

/**
 * 实现了BaseDao接口。
 *
 * @param <T>
 * @author Jeor
 */
@Component("baseDao")
public class BaseDaoImpl<T> implements BaseDao<T> {


    private static Logger log = Logger.getLogger(BaseDaoImpl.class);

    @Resource
    private HibernateTemplate hibernateTemplate;
    @Resource
    private JdbcTemplate jdbcTemplate;


    private static ConnectionProvider cp;
    private static Map<Object, Object> map = new HashMap<Object, Object>();


    public void delete(T entity) {
        if (entity == null) {
            return;
        }
        hibernateTemplate.delete(entity);
    }

    public T get(Class<T> clazz, int id) {
        if (id < 1) {
            return null;
        }
        return hibernateTemplate.get(clazz, id);
    }

    public void save(T entity) {
        if (entity == null) {
            return;
        }
        hibernateTemplate.save(entity);
    }

    public void saveOrUpdate(T entity) {
        if (entity == null) {
            return;
        }
        hibernateTemplate.saveOrUpdate(entity);
    }

    public void update(T entity) {
        if (entity == null) {
            return;
        }
        hibernateTemplate.update(entity);
    }

    public String getStringByHql(String hql, Object... vals) {
        Object obj = this.getObjectByHql(hql, vals);
        if (obj == null) {
            return null;
        }
        return (String) obj;
    }


    public long countRecord(String hql, Object... vals) {
        Object obj = this.getObjectByHql(hql, vals);
        if (obj == null) {
            return 0;
        }
        return (Long) obj;
    }

    @SuppressWarnings("unchecked")
    public void deleteById(Class<T> clazz, final int id) {
        StringBuilder hql = new StringBuilder();
        hql.append("delete from ").append(clazz.getName()).append(" where id=?");
        createQuery(hql.toString(), id).executeUpdate();
    }

    @SuppressWarnings("unchecked")
    public void deleteByIds(Class<T> clazz, final Collection<Integer> ids) {

        if (ids == null || ids.isEmpty()) {
            return;
        }
        StringBuilder hql = new StringBuilder();
        hql.append("delete from ").append(clazz.getName()).append(" where id in(:ids)");
        final String delHql = hql.toString();
        getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                session.createQuery(delHql).setParameterList("ids", ids).executeUpdate();
                return null;
            }
        });


    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public Page<T> getByObjectPage(Page page, String countHql, final String selHql, final Object... vals) {
        //设置总行数
        page.setTotalCount(this.countRecord(countHql, vals));

        final int first = page.getFirst();
        final int pageSize = page.getPageSize();

        if (page.getTotalCount() > 0) {

            List rs = hibernateTemplate.executeFind(new HibernateCallback() {
                public List doInHibernate(Session session) throws HibernateException,
                        SQLException {
                    return createQuery(selHql, vals).setFirstResult(first - 1).setMaxResults(pageSize).list();
                }
            });
            page.setResult(rs);
        }
        return page;
    }

    @SuppressWarnings("unchecked")
    public Page<T> getByPage(Page<T> page, String countHql, String selHql, Object... vals) {
        page.setTotalCount(this.countRecord(countHql, vals));
        int first = page.getFirst();
        int pageSize = page.getPageSize();
        if (page.getTotalCount() > 0) {
            List<T> rs = (List<T>) createQuery(selHql, vals).setFirstResult(first - 1)
                    .setMaxResults(pageSize).list();
            page.setResult(rs);
        }
        return page;
    }


    public Integer getIntegerByHql(String hql, Object... vals) {
        Object obj = this.getObjectByHql(hql, vals);
        if (obj == null) {
            return 0;
        }
        return (Integer) obj;
    }

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
        cp = ((SessionFactoryImplementor) sessionFactory).getConnectionProvider();
        this.hibernateTemplate = hibernateTemplate;
    }

    public Object getObjectByHql(final String hql, final Object... vals) {
        if (StringUtils.isEmpty(hql)) {
            return null;
        }
        return createQuery(hql, vals).setFirstResult(0).setMaxResults(1).uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List getListByHql(String hql, Object... vals) {
        List list = hibernateTemplate.find(hql, vals);
        return list;
    }


    @SuppressWarnings("unchecked")
    public void updateByHql(final String hql, final Object... vals) {
        createQuery(hql, vals).executeUpdate();
    }

    @SuppressWarnings("unchecked")
    public void updateBySql(final String sql, final Object... vals) {
        createSqlQuery(sql, vals).executeUpdate();
    }

    @SuppressWarnings("unchecked")
    public List getByParams(String hql, String[] paramNames,
                            Object[] paramValues) {
        return getHibernateTemplate().findByNamedParam(hql, paramNames, paramValues);
    }

    //TODO 返回VO的实现函数
    @SuppressWarnings({"rawtypes", "unchecked"})
    public Page getPageBySQL(Page page, Class clazz, String countSql, String selSql, Object... vals) {

        page.setTotalCount(this.countSQLRecord(countSql, vals));//设置总行数

        int first = page.getFirst();
        int pageSize = page.getPageSize();

        if (page.getTotalCount() > 0) {
            SQLQuery sqlquery = createSqlQuery(selSql, vals);
            if (null != clazz) {
                sqlquery.addEntity(clazz);
            }
            List rs = sqlquery.setFirstResult(first - 1).setMaxResults(pageSize).list();//一页的数据
            page.setResult(rs);
        }
        return page;
    }

    public List getListBySQL(String sql, Object... vals) {
        return createSqlQuery(sql, vals).list();
    }

    public Object getObjectBySQL(final String sql, final Object... vals) {
        if (StringUtils.isEmpty(sql)) {
            return null;
        }
        return createSqlQuery(sql, vals).setFirstResult(0).setMaxResults(1).uniqueResult();
    }

    public long countSQLRecord(String sql, Object... vals) {
        Integer bigCount = (Integer) this.getObjectBySQL(sql, vals);
        if (bigCount == null) {
            return 0;
        }
        return bigCount.longValue();
    }

    @SuppressWarnings("unchecked")
    public List<Object> getListLimitSize(final String selHql, final int size, final Object... vals) {
        return (List<Object>) createQuery(selHql, vals).setFirstResult(0)
                .setMaxResults(size).list();
    }

    protected Session getSession() {
        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        //HibernateSessionHolder.setCurrent(session);不需要动态数据源
        return session;
    }

    public SQLQuery createSqlQuery(String sql, Object... vals) {
        SQLQuery sqlquery = getSession().createSQLQuery(sql);
        for (int i = 0; i < vals.length; i++) {
            sqlquery.setParameter(i, vals[i]);
        }
        return sqlquery;
    }

    public Query createQuery(String hql, Object... vals) {
        Session session = getSession();
        Query query = session.createQuery(hql);
        for (int i = 0; i < vals.length; i++) {
            query.setParameter(i, vals[i]);
        }
        return query;
    }


    public Serializable addEntity(T entity) {
        Serializable id = getHibernateTemplate().save(entity);
        getSession().flush();
        return id;
    }

    public boolean delEntity(String hql, Object... vals) {

        log.debug("动态删除生成的HQL：" + hql.toString());
        return (createQuery(hql, vals).executeUpdate() > 0);
    }

    @SuppressWarnings("unchecked")
    public List<T> list(String hql, int startPageNum, int pageSize,
                        List<Object> lists) {
        List list = new ArrayList();
        try {
            list = createQuery(hql, lists.toArray()).setFirstResult(startPageNum).setMaxResults(pageSize).list();
        } catch (HibernateException e) {
            log.error("list()执行失败...", e);
            throw e;
        }
        return list;
    }

    public List<T> queryAll(Class<T> clazz) {
        try {
            return (List<T>) getHibernateTemplate().loadAll(clazz);
        } catch (DataAccessException e) {
            log.error("queryAll()执行失败...", e);
            throw e;
        }
    }

    public List<T> findByExample(T exampleEntity) {
        try {
            return (List<T>) getHibernateTemplate().findByExample(exampleEntity);
        } catch (DataAccessException e) {
            log.error("queryAll()执行失败...", e);
            throw e;
        }
    }


    @SuppressWarnings("unchecked")
    public List<Object> queryAll(String hql, List<Object> lists) {
        return createQuery(hql, lists.toArray()).list();
    }

    public boolean updateEntity(String hql, Object... vals) {
        return (createQuery(hql, vals).executeUpdate() > 0);
    }

    public T getEntity(Class<T> clazz, Serializable id) {
        try {
            return (T) getHibernateTemplate().get(clazz, id);
        } catch (DataAccessException e) {
            log.error("getEntity()执行失败...", e);
            throw e;
        }
    }

    public Object getEntity(String hql, List<Object> lists) {
        return createQuery(hql, lists.toArray()).uniqueResult();
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}