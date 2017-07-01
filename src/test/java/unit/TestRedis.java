package unit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by Jeor on 2016/3/7.  修改测试删除分支。
 */
public class TestRedis {
    @Autowired
    private JedisPool jedisPool;

    @Test
    public void test() throws Exception {
        System.out.println("jedisPool"+jedisPool);
        Jedis jedis = null;
        String key="ams:exam:100018:info";
        //jedis = jedisPool.getResource();
        jedis=this.InitJedis();
        String obj=jedis.get(key);
        System.out.print(obj);

    }

    /** 连接redis制定的db*/
    private Jedis InitJedis(){

        Jedis jedis = new Jedis("ct.yunxiao.com",16363);
        jedis.auth("123456");
        jedis.select(0);
        return jedis;
    }
}
