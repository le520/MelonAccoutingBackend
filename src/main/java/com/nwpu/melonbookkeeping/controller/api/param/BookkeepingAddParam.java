package com.nwpu.melonbookkeeping.controller.api.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.sql.Date;

/**
 * @author noorall
 * @date 2021/1/93:19 下午
 * @Description: 新增记账记录参数
 */
@Data
public class BookkeepingAddParam {
    @ApiModelProperty(value = "记录url", required = true)
    @NotEmpty(message = "记录URL不能为空")
    private String url;

    @ApiModelProperty(value = "记录创建时间", required = true, notes = "必须是过去的时间")
    @Past
    private Date createTime;
}
