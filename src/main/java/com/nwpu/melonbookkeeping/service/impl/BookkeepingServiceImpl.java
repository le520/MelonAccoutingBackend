package com.nwpu.melonbookkeeping.service.impl;

import com.nwpu.melonbookkeeping.entity.Bookkeeping;
import com.nwpu.melonbookkeeping.entity.User;
import com.nwpu.melonbookkeeping.repository.BookkeepingRepository;
import com.nwpu.melonbookkeeping.service.BookkeepingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

/**
 * @author noorall
 * @date 2021/1/93:07 下午
 * @Description: 用户记账记录相关接口实现
 */
@Service
@Transactional
public class BookkeepingServiceImpl implements BookkeepingService {
    @Autowired
    BookkeepingRepository bookkeepingRepository;

    /**
     * 通过用户获取所有记录
     * @param user 记录的所有者
     * @return 所有者的所有记录
     */
    @Override
    public List<Bookkeeping> getAllBookkeepingByUser(User user) {
        return bookkeepingRepository.findAllByUser(user);
    }

    /**
     * 添加一个记录
     * @param bookkeeping 记录信息
     * @return 添加记录的id
     */
    @Override
    public int addOneBookkeeping(Bookkeeping bookkeeping) {
        int id = -1;
        try {
            id = bookkeepingRepository.saveAndFlush(bookkeeping).getId();
        } catch (Exception e) {
            return -1;
        }
        return id;
    }

    /**
     * 通过User和ID删除记录
     * @param id 记录的id
     * @param user 记录持有者
     * @return 删除结果
     */
    @Override
    public boolean deleteBookkeepingByIdAndUser(int id, User user) {
        if (bookkeepingRepository.findByIdAndUser(id, user) == null) {
            return false;
        }
        try {
            bookkeepingRepository.deleteBookkeepingByIdAndUser(id, user);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
