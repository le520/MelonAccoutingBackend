package com.nwpu.melonbookkeeping.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author noorall
 * @date 2021/1/11 4:31 下午
 * @Description: 用户反馈实体
 */
@Entity
@Data
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "content")
    private String content;

    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "is_read")
    private int isRead;
}
