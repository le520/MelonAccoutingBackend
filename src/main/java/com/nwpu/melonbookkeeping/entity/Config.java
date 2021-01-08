package com.nwpu.melonbookkeeping.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "config")
public class Config {
    @Id
    @Column(name = "config_key")
    private String key;

    @Column(name = "config_kind")
    private int kind;

    @Column(name = "config_value")
    private String value;
}
