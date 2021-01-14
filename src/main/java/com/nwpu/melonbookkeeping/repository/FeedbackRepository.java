package com.nwpu.melonbookkeeping.repository;

import com.nwpu.melonbookkeeping.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author noorall
 * @date 2021/1/11 4:35 下午
 * @Description: 用户反馈
 */
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
    boolean deleteById(int id);

    Feedback getFeedbackById(int id);

    int countFeedbacksByIsRead(int isRead);
}
