package com.jeor.cave.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

/**
 * @Author: Jiangguoda
 * @Date: 2020/8/21 9:16
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "通用返回结构", description = "通用返回结构")
public class Result<T> {
    public static final int errorCode = 503;
    public static final int successCode = 200;
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "状态代码", required = true, notes = "状态代码,200成功，503失败", dataType = "int")
    private int code;
    @ApiModelProperty(value = "返回数据信息", notes = "data为对应数据实体", dataType = "String")
    private T data;
    @ApiModelProperty(value = "描述信息", notes = "描述信息", dataType = "String")
    private String msg;

    public Result() {
        this.code = 0;
    }



    public static <T> Result<T> error(int code, String msg) {
        return new Result<T>().setCode(code).setMsg(msg);
    }

    public static <T> Result<T> error(String msg){
        return new Result<T>().setCode(errorCode).setMsg(msg);
    }

    public static <T> Result<T> ok(T data) {
        return new Result<T>().setCode(200).setData(data);
    }

    public static <T> Result<T> msg(String msg){
        return new Result<T>().setCode(200).setMsg(msg);
    }

    public static <T> Result<T> ok() {
        return new Result<T>().setCode(200).setMsg("操作成功!");
    }

    public static <T> Result<T> error(HttpStatus status){
        return new Result<T>().setCode(status.value()).setMsg(status.getReasonPhrase());
    }

    public boolean isSucceed(){
        return this.code == successCode;
    }
}
