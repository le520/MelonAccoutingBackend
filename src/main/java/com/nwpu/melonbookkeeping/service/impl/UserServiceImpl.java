package com.nwpu.melonbookkeeping.service.impl;

import com.nwpu.melonbookkeeping.common.Constants;
import com.nwpu.melonbookkeeping.controller.admin.vo.MirrorsDataVO;
import com.nwpu.melonbookkeeping.controller.api.param.UserModifyParam;
import com.nwpu.melonbookkeeping.entity.User;
import com.nwpu.melonbookkeeping.repository.UserRepository;
import com.nwpu.melonbookkeeping.service.UserService;
import com.nwpu.melonbookkeeping.util.ImageProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 用户登录
     * @TODO 密码使用md5加密
     * @param loginName 用户名
     * @param passwordMd5 密码
     * @return 登录结果
     */
    @Override
    public User login(String loginName, String passwordMd5) {
        return userRepository.findUserByUserNameAndPassword(loginName, passwordMd5);
    }

    /**
     * 更新最后登录时间
     * @param user 更新user的最后登录时间
     */
    @Override
    public void updateLastLoginTime(User user) {
        try {
            user.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
        } catch (Exception e) {

        }
    }

    /**
     * 设置用户的状态
     * @param user 设置用户的状态
     * @return 设置的结果
     */
    @Override
    public boolean setUserStatus(User user) {
        try {
            userRepository.saveAndFlush(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 通过ID获取一个用户
     * @param id 用户的id
     * @return 用户信息
     */
    @Override
    public User getUserById(int id) {
        return userRepository.findUserById(id);
    }

    /**
     * 获取所有用户
     * @return 所有用户
     */
    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    /**
     * 用户注册
     * @param user 注册用户的信息
     * @return 注册的结果
     */
    @Override
    public boolean register(User user) {
        if (!userRepository.existsUserByUserName(user.getUserName())) {
            try {
                user.setRegisterTime(new Timestamp(System.currentTimeMillis()));
                userRepository.saveAndFlush(user);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    /**
     * 修改用户
     * @param user 用户信息
     * @param userModifyParam 修改的参数
     * @return 修改的结果
     */
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

    /**
     * 获取用户的视图信息
     * @return 用户的视图信息
     */
    @Override
    public List<MirrorsDataVO> getUserMirrorsData() {
        List<MirrorsDataVO> mirrorsDataVOS = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < 10; i++) {
            MirrorsDataVO mirrorsDataVO = new MirrorsDataVO();
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
            mirrorsDataVO.setA(userRepository.countUsersByRegisterTimeBetween(beginOfDate, endOfDate));
            mirrorsDataVO.setB(userRepository.countUsersByLastLoginTimeBetween(beginOfDate, endOfDate));
            mirrorsDataVO.setY(sdf.format(beginOfDate));
            mirrorsDataVOS.add(mirrorsDataVO);
        }
        return mirrorsDataVOS;
    }


}
