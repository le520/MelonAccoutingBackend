package com.nwpu.melonbookkeeping.service;

import com.nwpu.melonbookkeeping.entity.User;

public interface UserService {
    User login(String loginName, String passwordMd5);
    User getUserById(int id);
}
