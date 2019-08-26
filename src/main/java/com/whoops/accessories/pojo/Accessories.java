package com.whoops.accessories.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 配件
 */
@Entity
public class Accessories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 配件种类
     */
    private Integer type;

    /**
     * 配件名称
     */
    private String name;

    public Accessories() {
    }

    public Accessories(Long id, Integer type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
