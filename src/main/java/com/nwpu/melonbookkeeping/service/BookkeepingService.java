package com.nwpu.melonbookkeeping.service;

import com.nwpu.melonbookkeeping.entity.Bookkeeping;
import com.nwpu.melonbookkeeping.entity.User;

import java.util.List;

/**
 * @author noorall
 * @date 2021/1/93:04 下午
 * @Description: 记账记录相关接口
 */
public interface BookkeepingService {
    List<Bookkeeping> getAllBookkeepingByUser(User user);

    int addOneBookkeeping(Bookkeeping bookkeeping);

    boolean deleteBookkeepingByIdAndUser(int id, User user);
}
