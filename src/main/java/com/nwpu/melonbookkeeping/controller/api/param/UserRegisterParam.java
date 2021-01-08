package com.nwpu.melonbookkeeping.controller.api.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserRegisterParam {
    @ApiModelProperty("用户名")
    @NotEmpty(message = "用户名不能为空")
    private String userName;

    @ApiModelProperty("密码")
    @NotEmpty(message = "密码不能为空")
    private String password;

    @ApiModelProperty("昵称")
    @NotEmpty(message = "昵称不能为空")
    private String nickName;

    @ApiModelProperty("手机号")
    @NotEmpty(message = "手机号不能为空")
    private String phoneNumber;
}
