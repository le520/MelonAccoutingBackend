package com.nwpu.melonbookkeeping.repository;

import com.nwpu.melonbookkeeping.entity.Bookkeeping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookkeepingRepository extends JpaRepository<Bookkeeping,Integer> {

}
