package com.nwpu.melonbookkeeping.repository;

import com.nwpu.melonbookkeeping.entity.Config;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConfigRepository extends JpaRepository<Config, String> {
    List<Config> findAllByKind(int kind);
}
