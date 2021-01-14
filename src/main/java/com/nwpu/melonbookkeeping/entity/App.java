package com.nwpu.melonbookkeeping.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

/**
 * @author noorall
 * @date 2021/1/13 6:18 下午
 * @Description: app实体
 */
@Entity
@Data
@Table(name = "app")
public class App {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "app_id")
    private Integer id;

    @Column(name = "is_available")
    private Integer isAvailable = 1;

    @Column(name = "version")
    private Integer version;

    @Column(name = "info")
    private String info;

    @Column(name = "update_time")
    private Date updateTime;
}
