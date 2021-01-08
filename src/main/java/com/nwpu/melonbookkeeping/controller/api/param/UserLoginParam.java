package com.nwpu.melonbookkeeping.controller.api.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserLoginParam {
    @ApiModelProperty("登录名")
    @NotEmpty(message = "The username can't be null")
    private String userName;
    @ApiModelProperty("登录密码")
    @NotEmpty(message = "The password can't be null")
    private String password;
}
