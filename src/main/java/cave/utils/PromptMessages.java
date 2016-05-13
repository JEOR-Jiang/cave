package cave.utils;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by Jeor on 2016/5/6.
 * 错误信息管理
 */
public final class PromptMessages {
    private static Map<String,String> PROMPt_MESSAGES=new HashMap<String, String>();

    public void init(){
        //用户
        PROMPt_MESSAGES.put("user.failedLogin", PropertySetting.getProperties().get("messages.user.failedLogin"));
        PROMPt_MESSAGES.put("user.findByParamInvailOperation",PropertySetting.getProperties().get("messages.user.findByParamInvailOperation"));
        PROMPt_MESSAGES.put("user.registerEmptyAcount", PropertySetting.getProperties().get("messages.user.registerEmptyAcount"));
        PROMPt_MESSAGES.put("user.registerEmptyPassword", PropertySetting.getProperties().get("messages.user.registerEmptyPassword"));
        PROMPt_MESSAGES.put("user.registerExistQQ", PropertySetting.getProperties().get("messages.user.registerExistQQ"));
        PROMPt_MESSAGES.put("user.registerExistEmail", PropertySetting.getProperties().get("messages.user.registerExistEmail"));
        PROMPt_MESSAGES.put("user.registerExistPhone", PropertySetting.getProperties().get("messages.user.registerExistPhone"));
        PROMPt_MESSAGES.put("user.nullId", PropertySetting.getProperties().get("messages.user.nullId"));

    }

    /**
     * 生成错误信息
     * @param errorCode 错误信息码
     * @param params    错误信息填充参数
     * @return
     */
    public static final String getMsg(String errorCode,Object... params ){
        String msg=PROMPt_MESSAGES.get(errorCode);
        return MessageFormat.format(msg,params);
        /*String message = "oh, {0} is ''a'' pig";
        Object[] array = new Object[]{"ZhangSan"};
        String value = MessageFormat.format(message, array);
        System.out.println(value);
        * */
    }

}
