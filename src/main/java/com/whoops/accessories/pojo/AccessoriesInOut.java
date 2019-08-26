package com.whoops.accessories.pojo;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class AccessoriesInOut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 配件
     */
    @OneToOne(cascade = CascadeType.DETACH,fetch = FetchType.LAZY)
    @JoinColumn(name = "accessories_id")
    private Accessories accessories;
    /**
     * 数量
     */
    private Long number;
    /**
     * 单价
     */
    private Double price;
    /**
     * 总价
     */
    private Double totalPrice;
    /**
     * 出入库单号
     */
    private String formNum;
    /**
     * 出入库时间
     */
    @org.hibernate.annotations.CreationTimestamp //由数据库自动创建时间
    private Timestamp createTime;
    /**
     * 经办人
     */
    private Long user_id;
    private String username;

    public AccessoriesInOut() {
    }

    public AccessoriesInOut(Accessories accessories, Long number, Double price, Double totalPrice, String formNum, Timestamp createTime, Long user_id, String username) {
        this.accessories = accessories;
        this.number = number;
        this.price = price;
        this.totalPrice = totalPrice;
        this.formNum = formNum;
        this.createTime = createTime;
        this.user_id = user_id;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Accessories getAccessories() {
        return accessories;
    }

    public void setAccessories(Accessories accessories) {
        this.accessories = accessories;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getFormNum() {
        return formNum;
    }

    public void setFormNum(String formNum) {
        this.formNum = formNum;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
