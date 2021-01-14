package com.nwpu.melonbookkeeping.repository;

import com.nwpu.melonbookkeeping.entity.Bookkeeping;
import com.nwpu.melonbookkeeping.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Book;
import java.util.List;

public interface BookkeepingRepository extends JpaRepository<Bookkeeping, Integer> {
    List<Bookkeeping> findAllByUser(User user);

    void deleteBookkeepingByIdAndUser(int id, User user);

    Bookkeeping findByIdAndUser(int id, User user);

}
