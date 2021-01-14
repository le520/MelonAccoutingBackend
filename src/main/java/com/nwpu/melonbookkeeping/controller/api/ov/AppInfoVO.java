package com.nwpu.melonbookkeeping.controller.api.ov;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author noorall
 * @date 2021/1/13 6:26 下午
 * @Description: APP信息VO
 */
@Data
public class AppInfoVO implements Serializable {
    @ApiModelProperty("最新版本号")
    private int version;
    @ApiModelProperty("更新信息")
    private String info;
}
