package com.nwpu.melonbookkeeping.repository;

import com.nwpu.melonbookkeeping.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUserNameAndPassword(String userName, String password);

    User findUserById(int id);

    boolean existsUserByUserNameOrPhoneNumber(String userName, String phoneNumber);

    boolean existsUserByUserName(String userName);

    int countUsersByRegisterTimeBetween(Timestamp startTime, Timestamp endTime);

    int countUsersByLastLoginTimeBetween(Timestamp startTime, Timestamp endTime);
}
