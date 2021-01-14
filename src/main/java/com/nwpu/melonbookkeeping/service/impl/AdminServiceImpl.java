package com.nwpu.melonbookkeeping.service.impl;

import com.nwpu.melonbookkeeping.controller.admin.ov.AdminIndexOV;
import com.nwpu.melonbookkeeping.entity.Admin;
import com.nwpu.melonbookkeeping.entity.Bookkeeping;
import com.nwpu.melonbookkeeping.entity.User;
import com.nwpu.melonbookkeeping.repository.AdminRepository;
import com.nwpu.melonbookkeeping.repository.BookkeepingRepository;
import com.nwpu.melonbookkeeping.repository.ConfigRepository;
import com.nwpu.melonbookkeeping.repository.UserRepository;
import com.nwpu.melonbookkeeping.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

/**
 * @author noorall
 * @date 2021/1/83:21 下午
 * @Description: 管理员相关操作实现
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookkeepingRepository bookkeepingRepository;
    @Autowired
    ConfigRepository configRepository;

    @Override
    public boolean login(String userName, String password) {
        return adminRepository.existsAdminByUserNameAndPassword(userName, password);
    }

    @Override
    public boolean modifyAdminPassword(String password) {
        try {
            Admin admin = adminRepository.findAdminById(1);
            admin.setPassword(password);
            adminRepository.saveAndFlush(admin);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public AdminIndexOV getIndexOv() {
        AdminIndexOV adminIndexOV = new AdminIndexOV();
        adminIndexOV.setTotalUser((int) userRepository.count());
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH),
                0, 0, 0);
        Timestamp beginOfDate = new Timestamp(calendar1.getTime().getTime());
        // 获取23点59分59秒Date
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(calendar2.get(Calendar.YEAR), calendar2.get(Calendar.MONTH), calendar2.get(Calendar.DAY_OF_MONTH),
                23, 59, 59);
        Timestamp endOfDate = new Timestamp(calendar2.getTime().getTime());
        adminIndexOV.setTodayActivateUser(userRepository.countUsersByLastLoginTimeBetween(beginOfDate, endOfDate));
        adminIndexOV.setTotalBookkeeping((int) bookkeepingRepository.count());
        adminIndexOV.setTotalApiCalls(Integer.parseInt(configRepository.findConfigByKey("apiCallsCount").getValue()));
        return adminIndexOV;
    }


}
