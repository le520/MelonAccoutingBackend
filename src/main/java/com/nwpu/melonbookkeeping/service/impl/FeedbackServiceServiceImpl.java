package com.nwpu.melonbookkeeping.service.impl;

import com.nwpu.melonbookkeeping.entity.Feedback;
import com.nwpu.melonbookkeeping.repository.FeedbackRepository;
import com.nwpu.melonbookkeeping.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author noorall
 * @date 2021/1/11 4:37 下午
 * @Description: 用户反馈服务实现
 */
@Service
@Transactional
public class FeedbackServiceServiceImpl implements FeedbackService {
    @Autowired
    FeedbackRepository feedbackRepository;

    @Override
    public boolean addFeedback(Feedback feedback) {
        try {
            feedback.setCreateTime(new Timestamp(System.currentTimeMillis()));
            feedbackRepository.saveAndFlush(feedback);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll(Sort.by("isRead"));
    }

    @Override
    public boolean setReadById(int id) {
        Feedback feedback = feedbackRepository.getFeedbackById(id);
        if (feedback == null) {
            return false;
        } else {
            try {
                feedback.setIsRead(1);
                feedbackRepository.saveAndFlush(feedback);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }

    @Override
    public Feedback getFeedbackById(int id) {
        return feedbackRepository.getFeedbackById(id);
    }

    @Override
    public int getUNReadCount() {
        return feedbackRepository.countFeedbacksByIsRead(0);
    }

}
