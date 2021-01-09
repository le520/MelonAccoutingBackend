package com.nwpu.melonbookkeeping.service.impl;

import com.nwpu.melonbookkeeping.entity.Admin;
import com.nwpu.melonbookkeeping.entity.User;
import com.nwpu.melonbookkeeping.repository.AdminRepository;
import com.nwpu.melonbookkeeping.repository.UserRepository;
import com.nwpu.melonbookkeeping.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public boolean login(String userName, String password) {
        return adminRepository.existsAdminByUserNameAndPassword(userName, password);
    }


}
