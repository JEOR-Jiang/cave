package com.jeor.cave.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: Jiangguoda
 * @Date: 2020/8/24 16:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToEmail implements Serializable {

    /**
     * 邮件接收方，可多人
     */
    private String[] tos;
    /**
     * 邮件主题
     */
    private String subject;
    /**
     * 邮件内容
     */
    private String content;
}