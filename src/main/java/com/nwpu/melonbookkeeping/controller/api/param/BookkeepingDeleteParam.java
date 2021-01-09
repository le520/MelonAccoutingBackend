package com.nwpu.melonbookkeeping.controller.api.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author noorall
 * @date 2021/1/93:52 下午
 * @Description: 删除记账记录参数
 */
@Data
public class BookkeepingDeleteParam {
    @ApiModelProperty(value = "记录ID", required = true)
    @Min(1)
    private int id;
}
