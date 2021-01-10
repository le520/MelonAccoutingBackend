package com.nwpu.melonbookkeeping.repository;

import com.nwpu.melonbookkeeping.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUserNameAndPassword(String userName, String password);
    User findUserById(int id);
    boolean existsUserByUserNameOrPhoneNumber(String userName,String phoneNumber);
}
