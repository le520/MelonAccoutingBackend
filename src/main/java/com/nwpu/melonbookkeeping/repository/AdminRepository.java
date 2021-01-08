package com.nwpu.melonbookkeeping.repository;

import com.nwpu.melonbookkeeping.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
}
