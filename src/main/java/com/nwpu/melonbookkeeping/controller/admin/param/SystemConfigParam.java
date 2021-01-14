package com.nwpu.melonbookkeeping.controller.admin.param;

import lombok.Data;

/**
 * @author noorall
 * @date 2021/1/10 6:53 下午
 * @Description: 系统配置参数
 */
@Data
public class SystemConfigParam {
    private String webTitle;
    private String webDescription;
    private String email;
}
