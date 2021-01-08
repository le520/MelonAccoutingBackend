package com.nwpu.melonbookkeeping.service.impl;

import com.auth0.jwt.JWT;
import com.nwpu.melonbookkeeping.entity.User;
import com.nwpu.melonbookkeeping.repository.UserRepository;
import com.nwpu.melonbookkeeping.service.UserService;
import com.nwpu.melonbookkeeping.util.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
