package com.nwpu.melonbookkeeping.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "user")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "status", columnDefinition = "int default 1")
    private Integer status = 1;

    @Column(name = "register_time")
    private Timestamp registerTime;

    @Column(name = "last_login_time")
    private Timestamp lastLoginTime;

    @Column(name = "access_token")
    private String accessToken;
}
