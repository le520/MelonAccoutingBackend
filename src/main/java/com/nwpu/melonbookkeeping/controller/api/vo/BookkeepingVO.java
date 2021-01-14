package com.nwpu.melonbookkeeping.controller.api.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author noorall
 * @date 2021/1/92:59 下午
 * @Description: 记账记录VO
 */
@Data
public class BookkeepingVO implements Serializable {
    @ApiModelProperty(value = "远程id")
    private int remoteId;

    @ApiModelProperty(value = "记录创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp time;

    @ApiModelProperty(value = "记账金额")
    private float count;

    @ApiModelProperty(value = "收支类型")
    private int outIntype;

    @ApiModelProperty(value = "具体类型")
    private String detailType;

    @ApiModelProperty(value = "类型图标")
    private int picRes;

    @ApiModelProperty(value = "备注")
    private String note;

    @ApiModelProperty(value = "标签")
    private String remark;

    @ApiModelProperty(value = "定位数据")
    private String location;
}
