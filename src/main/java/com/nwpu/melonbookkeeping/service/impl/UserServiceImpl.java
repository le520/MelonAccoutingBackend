package com.nwpu.melonbookkeeping.service.impl;

import com.nwpu.melonbookkeeping.common.Constants;
import com.nwpu.melonbookkeeping.controller.admin.ov.MirrorsDataOV;
import com.nwpu.melonbookkeeping.controller.api.param.UserModifyParam;
import com.nwpu.melonbookkeeping.entity.User;
import com.nwpu.melonbookkeeping.repository.UserRepository;
import com.nwpu.melonbookkeeping.service.UserService;
import com.nwpu.melonbookkeeping.util.ImageProcess;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User login(String loginName, String passwordMd5) {
        return userRepository.findUserByUserNameAndPassword(loginName, passwordMd5);
    }

    @Override
    public void updateLastLoginTime(User user) {
        try {
            user.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
        } catch (Exception e) {

        }
    }

    @Override
    public boolean setUserStatus(User user) {
        try {
            userRepository.saveAndFlush(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findUserById(id);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public boolean register(User user) {
        if (!userRepository.existsUserByUserName(user.getUserName())) {
            try {
                user.setRegisterTime(new Timestamp(System.currentTimeMillis()));
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean modify(User user, UserModifyParam userModifyParam) {
        if (userModifyParam.getAvatarStr() != null && !userModifyParam.getAvatarStr().isBlank()) {
            if (!ImageProcess.generateImage(userModifyParam.getAvatarStr(), String.valueOf(user.getId()))) {
                return false;
            } else if (user.getAvatar() == null || user.getAvatar().isBlank()) {
                user.setAvatar(Constants.AVATAR_URL + user.getId() + ".png");
            }
        }
        if (userModifyParam.getNickName() != null && !userModifyParam.getNickName().isEmpty()) {
            user.setNickName(userModifyParam.getNickName());
        }
        if (userModifyParam.getPhoneNumber() != null && !userModifyParam.getPhoneNumber().isEmpty()) {
            user.setPhoneNumber(userModifyParam.getPhoneNumber());
        }
        return true;
    }

    @Override
    public List<MirrorsDataOV> getUserMirrorsData() {
        List<MirrorsDataOV> mirrorsDataOVS = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < 10; i++) {
            MirrorsDataOV mirrorsDataOV = new MirrorsDataOV();
            // 获取00点00分00秒Date
            Calendar calendar1 = Calendar.getInstance();
            calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH) - i,
                    0, 0, 0);
            Timestamp beginOfDate = new Timestamp(calendar1.getTime().getTime());
            // 获取23点59分59秒Date
            Calendar calendar2 = Calendar.getInstance();
            calendar2.set(calendar2.get(Calendar.YEAR), calendar2.get(Calendar.MONTH), calendar2.get(Calendar.DAY_OF_MONTH) - i,
                    23, 59, 59);
            Timestamp endOfDate = new Timestamp(calendar2.getTime().getTime());
            mirrorsDataOV.setA(userRepository.countUsersByRegisterTimeBetween(beginOfDate, endOfDate));
            mirrorsDataOV.setB(userRepository.countUsersByLastLoginTimeBetween(beginOfDate, endOfDate));
            mirrorsDataOV.setY(sdf.format(beginOfDate));
            mirrorsDataOVS.add(mirrorsDataOV);
        }
        return mirrorsDataOVS;
    }


}
