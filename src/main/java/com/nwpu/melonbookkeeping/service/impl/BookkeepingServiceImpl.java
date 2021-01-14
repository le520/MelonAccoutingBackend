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

    @Override
    public List<Bookkeeping> getAllBookkeepingByUser(User user) {
        return bookkeepingRepository.findAllByUser(user);
    }

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
