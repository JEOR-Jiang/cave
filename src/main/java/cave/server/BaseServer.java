package cave.server;

import cave.utils.Page;

import java.util.List;

/**
 * Created by Jeor on 2016/4/18.
 */
public interface BaseServer<T> {
    public T findById(Class<T> clazz,Integer id) throws Exception;
    public void create(T t) throws Exception;
    public void saveOrUpdate(T t)throws Exception;
    public void remove(List<T> ts) throws Exception;
    public void revise(List<T> ts)throws Exception;
    /**
     * 实体对象分页
     */
    public Page<T> findByObjectPage(Class<T> tClass ,Page<T> page);
    /**
     * 显示对象属性时填充关联对象
     * @param entity 需要填充的对象
     */
    public void findDetail(Object entity)throws Exception;
    /**
     * 修改对象属性的关联对象
     * @param
     */
    public void reviseRelation(Integer id,Integer[]... relationIds)throws Exception;
}
