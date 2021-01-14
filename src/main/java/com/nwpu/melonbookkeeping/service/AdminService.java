package com.nwpu.melonbookkeeping.service;

import com.nwpu.melonbookkeeping.controller.admin.ov.AdminIndexOV;
import com.nwpu.melonbookkeeping.entity.Admin;
import com.nwpu.melonbookkeeping.entity.User;

import java.util.List;

/**
 * @author noorall
 * @date 2021/1/83:21 下午
 * @Description: 管理员操作接口
 */
public interface AdminService {
    boolean login(String userName, String password);

    boolean modifyAdminPassword(String password);

    AdminIndexOV getIndexOv();
}
