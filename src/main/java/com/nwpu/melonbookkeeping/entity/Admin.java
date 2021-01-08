package com.nwpu.melonbookkeeping.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "admin")
public class Admin {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "admin_id")
    private int id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;
}
