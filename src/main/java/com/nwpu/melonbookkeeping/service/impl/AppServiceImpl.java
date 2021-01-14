package com.nwpu.melonbookkeeping.service.impl;

import com.nwpu.melonbookkeeping.entity.App;
import com.nwpu.melonbookkeeping.repository.AppRepository;
import com.nwpu.melonbookkeeping.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author noorall
 * @date 2021/1/13 6:36 下午
 * @Description: app服务实现
 */
@Service
@Transactional
public class AppServiceImpl implements AppService {
    @Autowired
    AppRepository appRepository;

    @Override
    public App getNewestApp() {
        List<App> appList = appRepository.findAll();
        if (appList.size() == 0) {
            return null;
        } else {
            return appList.get(appList.size() - 1);
        }
    }

    @Override
    public int getNewestVersion() {
        List<App> appList = appRepository.findAll();
        if (appList.size() == 0) {
            return 1001;
        } else {
            return appList.get(appList.size() - 1).getVersion() + 1;
        }
    }

    @Override
    public boolean saveApp(App app) {
        app.setUpdateTime(new Date(System.currentTimeMillis()));
        try {
            appRepository.saveAndFlush(app);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<App> getAllApp() {
        return appRepository.findAll();
    }

    @Override
    public boolean deleteAppById(int id) {
        try {
            appRepository.deleteAppById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean recallAppById(int id) {
        App app = appRepository.findAppById(id);
        if (app == null) {
            return false;
        } else {
            app.setIsAvailable(0);
            appRepository.saveAndFlush(app);
            return true;
        }
    }
}
