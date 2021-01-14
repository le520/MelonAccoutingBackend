package com.nwpu.melonbookkeeping.controller.admin.param;

import lombok.Data;

/**
 * @author noorall
 * @date 2021/1/10 6:53 下午
 * @Description: 系统配置参数
 */
@Data
public class SystemConfigParam {
    //网站标题
    private String webTitle;
    //网站描述
    private String webDescription;
    //网站邮箱
    private String email;
}
