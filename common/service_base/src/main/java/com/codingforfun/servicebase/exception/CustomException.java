package com.codingforfun.servicebase.exception;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomException extends RuntimeException{

    @ApiModelProperty("Exception Code")
    private Integer code;

    @ApiModelProperty("Exception Message")
    private String exceptionMessage;


}
