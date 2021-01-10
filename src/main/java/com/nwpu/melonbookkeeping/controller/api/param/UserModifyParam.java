package com.nwpu.melonbookkeeping.controller.api.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class UserModifyParam {

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "用户头像数据",notes = "将图片BASE64加密后传递（不需要加密头部）")
    private String avatarStr;

    @ApiModelProperty(value = "手机号")
    private String phoneNumber;

}
