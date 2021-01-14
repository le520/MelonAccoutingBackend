package com.nwpu.melonbookkeeping.service;

import com.nwpu.melonbookkeeping.entity.Feedback;

import java.util.List;

/**
 * @author noorall
 * @date 2021/1/11 4:36 下午
 * @Description: 反馈服务接口
 */
public interface FeedbackService {
    boolean addFeedback(Feedback feedback);

    List<Feedback> getAllFeedback();

    boolean setReadById(int id);

    Feedback getFeedbackById(int id);

    int getUNReadCount();
}
