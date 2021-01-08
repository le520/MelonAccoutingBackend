package com.nwpu.melonbookkeeping.controller.api.ov;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfoVO implements Serializable {
    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("头像地址")
    private String avatar;

    @ApiModelProperty("手机号")
    private String phoneNumber;

    @ApiModelProperty("accessToken")
    private String accessToken;
}
