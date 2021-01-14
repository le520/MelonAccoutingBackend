package com.nwpu.melonbookkeeping.controller.admin.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author noorall
 * @date 2021/1/11 3:38 下午
 * @Description: 主页数据OV
 */
@Data
public class AdminIndexVO implements Serializable {
    //所有用户数量
    private int totalUser;
    //所有记录数量
    private int totalBookkeeping;
    //API调用次数
    private int totalApiCalls;
    //活跃用户数量
    private int todayActivateUser;
}
