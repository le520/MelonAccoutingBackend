package com.nwpu.melonbookkeeping.controller.api.ov;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author noorall
 * @date 2021/1/11 10:58 上午
 * @Description: 添加记账结果VO
 */
@Data
public class BookkeepingAddVO implements Serializable {
    @ApiModelProperty("远程ID")
    private int remoteId;
}
