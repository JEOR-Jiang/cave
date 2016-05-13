package cave.utils;

/**
 * Created by Jeor on 2016/5/6.
 * 参数工具类
 */
public class ParamtersUtil {
    /**
     * 检查是否为空
     */
    public static final boolean isNull(Object object){
        if(object==null){
            return true;
        }
        if(object instanceof String){
            String charts=(String)object;
            if(charts==""){
                return true;
            }
        }
        return false;
    }
}
