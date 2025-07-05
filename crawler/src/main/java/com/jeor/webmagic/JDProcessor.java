package com.jeor.webmagic;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author jeor
 * @Date 2025/05/05 17:44
 * @Version 1.0
 *
 * 京东
 */
public class JDProcessor implements PageProcessor {

    public static final String URL_LIST = "http://blog\\.sina\\.com\\.cn/s/articlelist_1487828712_0_\\d+\\.html";

    public static final String URL_POST = "http://blog\\.sina\\.com\\.cn/s/blog_\\w+\\.html";

    private Site site = Site
            .me()
            .setDomain("blog.sina.com.cn")
            .addHeader("name", "Jeor")
            .setRetryTimes(3)
            .setSleepTime(3000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31")
            ;

    @Override
    public Site getSite() {
        return site;
    }
    @Override
    public void process(Page page) {
        //列表页
        if (page.getUrl().regex(URL_LIST).match()) {
            // 从页面发现后续的url地址来抓取
            page.addTargetRequests(page.getHtml().xpath("//div[@class=\"articleList\"]").links().regex(URL_POST).all());
            page.addTargetRequests(page.getHtml().links().regex(URL_LIST).all());
            //文章页
        } else {

            page.putField("title", page.getHtml().xpath("//div[@class='articalTitle']/h2").all());
            page.putField("images", page.getHtml().css("img", "src").all());
//            page.putField("content", page.getHtml().xpath("//div[@id='articlebody']//div[@class='articalContent']"));
//            page.putField("date", page.getHtml().xpath("//div[@id='articlebody']//span[@class='time SG_txtc']").regex("\\((.*)\\)"));
        }
    }


    public static void main(String[] args) {
//        String params = "{\"url\":\"www.jd.com\"}";
//        String params = "{\"url\":\"https://www.51cto.com/article/772956.html\"}";
        String params = "{\"url\":\"https://blog.sina.com.cn/s/blog_40f078a90102zbz4.html\"}";
        Map<String, String> mapParams = JSONUtil.toBean(params, Map.class);
        System.out.println(mapParams);
        System.out.println("Begin");

        Spider.create(new JDProcessor())
                .addUrl(mapParams.get("url"))
//                .addPipeline(new JsonFilePipeline("D:\\temp\\crawler\\json\\"))
                .run();

        System.out.println("The end");
    }
}