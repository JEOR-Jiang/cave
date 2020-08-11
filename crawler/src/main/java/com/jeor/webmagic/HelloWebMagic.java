package com.jeor.webmagic;

import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import java.io.FileWriter;
import java.util.List;

/**
 * @Author jeor
 * @Date 2020/8/3 21:37
 * @Version 1.0
 *
 *  最原始提取方式，提取一页子内容写到文件中。
 */
public class HelloWebMagic implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);


    private static String host = "";    // 网址
    private static String url = "/viewforum.php?f=6";   // 列表url

    private static String fileName = "./url";   // 保存结果文件

    @Override
    public void process(Page page) {
        // 1.处理列表页面
        List<String> nodes = page.getHtml().$(".topictitle").all();
        nodes.forEach(item -> {
            // 1.1 提取详细界面地址
            int start = item.indexOf(".");
            int end = item.indexOf("\"", start+1);
            String requestDetail = host + item.substring(start+1, end);
            requestDetail = String.join("",requestDetail.split("amp;"));
            page.addTargetRequest(requestDetail); // 3.增加明细页面

            // 1.2  提取列表地址

        });
        //  2.处理详细页面
        String node = page.getHtml().$(".content").get();
        if(!StringUtils.isBlank(node)){
            int start = node.lastIndexOf("(");
            int end = node.lastIndexOf(")");
            String like = node.substring(start+1, end);
            if(Integer.parseInt(like) > 10) {
                _appendContent(like+""+page.getRequest().getUrl());
            }
        }
    }
    void _appendContent(String message){
        try {
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(message);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new HelloWebMagic()).addPipeline(new ConsolePipeline()).addUrl(host+url).thread(5).run();
    }
}
