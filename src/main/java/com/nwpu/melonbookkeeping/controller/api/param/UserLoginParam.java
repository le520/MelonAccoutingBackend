package com.nwpu.melonbookkeeping.controller.api.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserLoginParam {
    @ApiModelProperty(value = "登录名", required = true)
    @NotEmpty(message = "The username can't be null")
    private String userName;

    @ApiModelProperty(value = "登录密码", required = true)
    @NotEmpty(message = "The password can't be null")
    private String password;
}
