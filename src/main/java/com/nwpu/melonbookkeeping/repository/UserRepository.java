package com.nwpu.melonbookkeeping.repository;

import com.nwpu.melonbookkeeping.controller.admin.ov.MirrorsDataOV;
import com.nwpu.melonbookkeeping.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUserNameAndPassword(String userName, String password);

    User findUserById(int id);

    boolean existsUserByUserNameOrPhoneNumber(String userName, String phoneNumber);

    boolean existsUserByUserName(String userName);

    int countUsersByRegisterTimeBetween(Timestamp startTime, Timestamp endTime);

    int countUsersByLastLoginTimeBetween(Timestamp startTime, Timestamp endTime);
}
