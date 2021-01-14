package com.nwpu.melonbookkeeping.repository;

import com.nwpu.melonbookkeeping.entity.App;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author noorall
 * @date 2021/1/13 6:28 下午
 * @Description: AppRepository
 */
public interface AppRepository extends JpaRepository<App, Integer> {
    App findTopByIsAvailable(int isAvailable);

    List<App> getAppsByIsAvailable(int isAvailable);

    void deleteAppById(int id);

    App findAppById(int id);

    App findFirstByIdIsAfter(int id);
}
