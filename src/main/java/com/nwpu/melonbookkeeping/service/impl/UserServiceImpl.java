package com.nwpu.melonbookkeeping.service.impl;

import com.nwpu.melonbookkeeping.common.Constants;
import com.nwpu.melonbookkeeping.controller.api.param.UserModifyParam;
import com.nwpu.melonbookkeeping.entity.User;
import com.nwpu.melonbookkeeping.repository.UserRepository;
import com.nwpu.melonbookkeeping.service.UserService;
import com.nwpu.melonbookkeeping.util.ImageProcess;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public User getUserById(int id) {
        return userRepository.findUserById(id);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public boolean register(User user) {
        if (!userRepository.existsUserByUserNameOrPhoneNumber(user.getUserName(), user.getPhoneNumber())) {
            try {
                userRepository.saveAndFlush(user);
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
        try {
            userRepository.saveAndFlush(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
