package cave.server.impl;

import cave.dao.BaseDao;
import cave.server.BaseServer;
import cave.utils.Page;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * Created by Jeor on 2016/4/18.
 */
@Service("baseServer")
public class BaseServerImpl<T> implements BaseServer<T> {

    @Resource
    private BaseDao baseDao;

    public T findById(Class<T> clazz,Integer id) throws Exception {
        Object entity=this.baseDao.get(clazz,id);
        if(entity!=null){
            return (T)entity;
        }
        return null;
    }
    public void create(T t) throws Exception {
        this.baseDao.save(t);
    }

    public void revise(List<T> ts) throws Exception {
        if(ts!=null&&ts.size()>0){
            for(T t:ts){
                this.baseDao.update(t);
            }
        }
    }

    public void remove(List<T> ts) throws Exception {
        if(ts!=null&&ts.size()>0){
            for(T t:ts){
                this.baseDao.delete(t);
            }
        }

    }
    public Page<T> findByObjectPage(Class<T> tClass,Page<T> page){
        String sumRecordSql="select count(*) from "+tClass.getName();
        String recordSql="from "+tClass.getName();
        this.baseDao.getByObjectPage(page,sumRecordSql,recordSql);
        return page;
    }
}
