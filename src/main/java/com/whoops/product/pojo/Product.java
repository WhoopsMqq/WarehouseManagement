package com.whoops.product.pojo;

import javax.persistence.*;

/**
 * 成品
 */
@Entity
public class Product {

    @Id
    @Column(length = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 名称
     */
    @Column(length = 20,nullable = false)
    private String name;

    /**
     * 颜色
     */
    @Column(length = 20,nullable = false)
    private String color;

    /**
     * 门幅
     */
    @Column(length = 20,nullable = false)
    private String size;

    public Product() {
    }

    public Product(String name, String color, String size) {
        this.name = name;
        this.color = color;
        this.size = size;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

}
