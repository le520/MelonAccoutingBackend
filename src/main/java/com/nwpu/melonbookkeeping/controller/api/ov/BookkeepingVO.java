package com.nwpu.melonbookkeeping.controller.api.ov;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Date;

/**
 * @author noorall
 * @date 2021/1/92:59 下午
 * @Description: 记账记录VO
 */
@Data
public class BookkeepingVO {
    @ApiModelProperty(value = "记录ID")
    private int id;

    @ApiModelProperty(value = "记录URL")
    private String url;

    @ApiModelProperty("记录创建时间")
    private Date createTime;
}
