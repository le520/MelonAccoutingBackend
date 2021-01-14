package com.nwpu.melonbookkeeping.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * 记账记录实体类
 */
@Entity
@Data
@Table(name = "bookkeeping")
public class Bookkeeping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookkeeping_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "time")
    private Timestamp time;

    @Column(name = "count")
    private float count;

    @Column(name = "out_intype")
    private int outIntype;

    @Column(name = "detail_type")
    private String detailType;

    @Column(name = "pic_res")
    private int picRes;

    @Column(name = "note")
    private String note;

    @Column(name = "remark")
    private String remark;

    @Column(name = "location")
    private String location;
}
