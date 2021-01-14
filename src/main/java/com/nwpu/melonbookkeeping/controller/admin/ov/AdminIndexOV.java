package com.nwpu.melonbookkeeping.controller.admin.ov;

import lombok.Data;

import java.io.Serializable;

/**
 * @author noorall
 * @date 2021/1/11 3:38 下午
 * @Description: 主页数据OV
 */
@Data
public class AdminIndexOV implements Serializable {
    private int totalUser;
    private int totalBookkeeping;
    private int totalApiCalls;
    private int todayActivateUser;
}
