package com.nwpu.melonbookkeeping.controller.admin.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author noorall
 * @date 2021/1/11 1:56 下午
 * @Description: 用户新增/活跃数据图表
 */
@Data
public class MirrorsDataVO implements Serializable {
    private String y;
    private int a;
    private int b;
}
