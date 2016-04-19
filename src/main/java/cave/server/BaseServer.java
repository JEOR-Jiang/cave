package cave.server;

import cave.utils.Page;

import java.util.List;

/**
 * Created by Jeor on 2016/4/18.
 */
public interface BaseServer<T> {
    public T findById(Class<T> clazz,Integer id) throws Exception;
    public void create(T t) throws Exception;
    public void remove(List<T> ts) throws Exception;
    public void revise(List<T> ts)throws Exception;
    public Page<T> findByObjectPage(Class<T> tClass ,Page<T> page);
}
