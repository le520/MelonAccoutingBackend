package com.nwpu.melonbookkeeping.controller.api.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author noorall
 * @date 2021/1/11 4:42 下午
 * @Description: 反馈参数
 */
@Data
public class FeedbackParam {
    @ApiModelProperty("内容")
    @NotBlank
    private String content;
}
