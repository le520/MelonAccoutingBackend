package com.nwpu.melonbookkeeping.controller.api.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author noorall
 * @date 2021/1/93:52 下午
 * @Description: 删除记账记录参数
 */
@Data
public class BookkeepingDeleteParam {
    @ApiModelProperty(value = "记录ID", required = true)
    @NotEmpty(message = "记录ID不能为空")
    private int id;
}
