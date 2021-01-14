package com.nwpu.melonbookkeeping.controller.api.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfoVO implements Serializable {
    @ApiModelProperty(value = "用户名", required = true)
    private String userName;

    @ApiModelProperty(value = "昵称", required = true)
    private String nickName;

    @ApiModelProperty(value = "头像地址", required = true)
    private String avatar;

    @ApiModelProperty(value = "手机号", required = true)
    private String phoneNumber;

    @ApiModelProperty(value = "accessToken", allowEmptyValue = true)
    private String accessToken;
}
