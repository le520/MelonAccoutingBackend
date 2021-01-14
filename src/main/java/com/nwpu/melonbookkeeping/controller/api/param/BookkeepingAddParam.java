package com.nwpu.melonbookkeeping.controller.api.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author noorall
 * @date 2021/1/93:19 下午
 * @Description: 新增记账记录参数
 */
@Data
public class BookkeepingAddParam {
    @ApiModelProperty(value = "记录创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp time;

    @ApiModelProperty(value = "记账金额")
    private float count;

    @ApiModelProperty(value = "收支类型")
    @Min(1)
    @Max(2)
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
