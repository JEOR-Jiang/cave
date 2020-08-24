package com.jeor.cave.service;

import com.alibaba.druid.support.json.JSONUtils;
import com.jeor.cave.common.Result;
import com.jeor.cave.entity.vo.ToEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.spring.web.json.Json;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @Author: Jiangguoda
 * @Date: 2020/8/24 16:21
 */

@Component
public class SendEmail {

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    //  普通邮件发送
    public Result commonEmail(ToEmail toEmail) {
        //创建简单邮件消息
        SimpleMailMessage message = new SimpleMailMessage();
        //谁发的
        message.setFrom(from);
        //谁要接收
        message.setTo(toEmail.getTos());
        //邮件标题
        message.setSubject(toEmail.getSubject());
        //邮件内容
        message.setText(toEmail.getContent());
        try {
            mailSender.send(message);
            return Result.ok(JSONUtils.toJSONString(toEmail.getTos()));
        } catch (MailException e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    // HTML邮件发送
    public Result htmlEmail(ToEmail toEmail) throws MessagingException {
        //创建一个MINE消息
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper minehelper = new MimeMessageHelper(message, true);
        //谁发
        minehelper.setFrom(from);
        //谁要接收
        minehelper.setTo(toEmail.getTos());
        //邮件主题
        minehelper.setSubject(toEmail.getSubject());
        //邮件内容   true 表示带有附件或html
        minehelper.setText(toEmail.getContent(), true);
        try {
            mailSender.send(message);
            return Result.ok(JSONUtils.toJSONString(toEmail.getTos()) + toEmail.getContent());
        } catch (MailException e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }
    //  将 multpartfile 转为file
    private File MultipartFileToFile(MultipartFile multiFile) {
        // 获取文件名
        String fileName = multiFile.getOriginalFilename();
        // 获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        // 若需要防止生成的临时文件重复,可以在文件名后添加随机码

        try {
            File file = File.createTempFile(fileName, prefix);
            multiFile.transferTo(file);
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 含静态资源邮件发送
    public Result staticEmail(ToEmail toEmail, MultipartFile multipartFile, String resId) {
        //创建一个MINE消息
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            //谁发
            helper.setFrom(from);
            //谁接收
            helper.setTo(toEmail.getTos());
            //邮件主题
            helper.setSubject(toEmail.getSubject());
            //邮件内容   true 表示带有附件或html
            //邮件内容拼接
            String content =
                    "<html><body><img width='250px' src=\'cid:" + resId + "\'>" + toEmail.getContent()
                            + "</body></html>";
            helper.setText(content, true);
            //蒋 multpartfile 转为file
            File multipartFileToFile = MultipartFileToFile(multipartFile);
            FileSystemResource res = new FileSystemResource(multipartFileToFile);

            //添加内联资源，一个id对应一个资源，最终通过id来找到该资源
            helper.addInline(resId, res);
            mailSender.send(message);
            return Result.ok(JSONUtils.toJSONString(toEmail.getTos()) + toEmail.getContent());
        } catch (MessagingException e) {
            return Result.error(e.getMessage());
        }
    }

    // 带附件邮件发送
    public Result enclosureEmail(ToEmail toEmail, MultipartFile multipartFile) {
        //创建一个MINE消息
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            //谁发
            helper.setFrom(from);
            //谁接收
            helper.setTo(toEmail.getTos());
            //邮件主题
            helper.setSubject(toEmail.getSubject());
            //邮件内容   true 表示带有附件或html
            helper.setText(toEmail.getContent(), true);
            File multipartFileToFile = MultipartFileToFile(multipartFile);
            FileSystemResource file = new FileSystemResource(multipartFileToFile);
            String filename = file.getFilename();
            //添加附件
            helper.addAttachment(filename, file);
            mailSender.send(message);
            return Result.ok(JSONUtils.toJSONString(toEmail.getTos())  + toEmail.getContent());
        } catch (MessagingException e) {
            e.printStackTrace();
            return Result.error("附件邮件发送失败" + e.getMessage());
        }
    }
}
