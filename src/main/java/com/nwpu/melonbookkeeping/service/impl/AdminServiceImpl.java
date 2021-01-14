package com.nwpu.melonbookkeeping.service.impl;

import com.nwpu.melonbookkeeping.controller.admin.vo.AdminIndexVO;
import com.nwpu.melonbookkeeping.entity.Admin;
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

    /**
     * 管理员登录
     * @param userName 用户名
     * @param password 密码
     * @return 登录结果
     */
    @Override
    public boolean login(String userName, String password) {
        return adminRepository.existsAdminByUserNameAndPassword(userName, password);
    }

    /**
     * 修改管理员密码
     * @param password 新密码
     * @return 修改结果
     */
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

    /**
     * 获取管理员首页VO数据
     * @return 返回VO数据
     */
    @Override
    public AdminIndexVO getIndexVO() {
        AdminIndexVO adminIndexVO = new AdminIndexVO();
        adminIndexVO.setTotalUser((int) userRepository.count());
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH),
                0, 0, 0);
        Timestamp beginOfDate = new Timestamp(calendar1.getTime().getTime());
        // 获取23点59分59秒Date
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(calendar2.get(Calendar.YEAR), calendar2.get(Calendar.MONTH), calendar2.get(Calendar.DAY_OF_MONTH),
                23, 59, 59);
        Timestamp endOfDate = new Timestamp(calendar2.getTime().getTime());
        adminIndexVO.setTodayActivateUser(userRepository.countUsersByLastLoginTimeBetween(beginOfDate, endOfDate));
        adminIndexVO.setTotalBookkeeping((int) bookkeepingRepository.count());
        adminIndexVO.setTotalApiCalls(Integer.parseInt(configRepository.findConfigByKey("apiCallsCount").getValue()));
        return adminIndexVO;
    }


}
