package com.codingforfun.commonutils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *   Return results uniformly
 */
@Data
public class ResponseResult {

    @ApiModelProperty(value = "Response status, true means success, false means failure")
    private Boolean success;

    @ApiModelProperty(value = "Response Code")
    private Integer code;

    @ApiModelProperty(value = "Response Message")
    private String message;

    @ApiModelProperty(value = "Response Data")
    private Map<String, Object> data = new HashMap<String, Object>();

    //private constructor, only provide static methods
    private ResponseResult() {}

    //success static method
    public static ResponseResult success() {
        ResponseResult r = new ResponseResult();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("Success");
        return r;
    }

    //fail static method
    public static ResponseResult error() {
        ResponseResult r = new ResponseResult();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("Fail");
        return r;
    }

    public ResponseResult success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public ResponseResult message(String message){
        this.setMessage(message);
        return this;
    }

    public ResponseResult code(Integer code){
        this.setCode(code);
        return this;
    }

    public ResponseResult data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public ResponseResult data(Map<String, Object> map){
        this.setData(map);
        return this;
    }

}
