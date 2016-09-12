package cave.controller;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by Jeor on 2016/7/26.
 */
public class BaseAction {
    private static Logger logger=Logger.getLogger(BaseAction.class.getName());

    public static void responseMessage(HttpServletResponse var0, Object var1)throws Exception{
        var0.setCharacterEncoding("utf-8");
        var0.setContentType("text/html");
        PrintWriter var2 = null;
        try {
            var2 = var0.getWriter();
            var2.print(var1.toString());
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            if(var2 != null) {
                var2.flush();
                var2.close();
            }

        }

    }
}
