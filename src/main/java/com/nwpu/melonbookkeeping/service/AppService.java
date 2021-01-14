package com.nwpu.melonbookkeeping.service;

import com.nwpu.melonbookkeeping.entity.App;

import java.util.List;

/**
 * @author noorall
 * @date 2021/1/13 6:35 下午
 * @Description: app服务接口
 */
public interface AppService {
    App getNewestApp();
    int getNewestVersion();
    boolean saveApp(App app);
    List<App> getAllApp();
    boolean deleteAppById(int id);
    boolean recallAppById(int id);
}
