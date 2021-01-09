package com.nwpu.melonbookkeeping.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Table(name = "bookkeeping")
public class Bookkeeping {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bookkeeping_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "url")
    private String url;

    @Column(name = "create_time")
    private Date createTime;
}
