package unit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
/**
 * Created by Jeor on 2016/3/7.  修改测试删除分支。
 */
public class TestAction {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;
    @Test
    public void test() throws Exception {
        System.out.println("mvc");
       /* mockMvc.perform((get("/user/login.do"))).andExpect(status().isOk())
                .andDo(print())
                .andExpect(model().attributeHasNoErrors("teacher"));*/
    }
}
