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
    @Column(length = 8)
    private String accessoriesName;
    /**
     * 数量
     */
    @Column(length = 8)
    private Long number;
    /**
     * 单价
     */
    @Column(length = 8)
    private Double price;
    /**
     * 总价
     */
    @Column(length = 8)
    private Double totalPrice;
    /**
     * 出入库单号
     */
    @Column(length = 20)
    private String formNum;
    /**
     * 出入库时间
     */
    @Column(length = 20)
    @org.hibernate.annotations.CreationTimestamp //由数据库自动创建时间
    private Timestamp createTime;
    /**
     * 经办人
     */
    @Column(length = 20)
    private Long user_id;
    @Column(length = 20)
    private String username;

    /**
     * 出库/入库,1:出库 2:入库
     */
    @Column(length = 8)
    private Integer type;
    @Column(length = 10)
    private String typeName;

    public AccessoriesInOut() {
    }

    public AccessoriesInOut(Accessories accessories, String accessoriesName, Long number, Double price, Double totalPrice, String formNum, Timestamp createTime, Long user_id, String username, Integer type, String typeName) {
        this.accessories = accessories;
        this.accessoriesName = accessoriesName;
        this.number = number;
        this.price = price;
        this.totalPrice = totalPrice;
        this.formNum = formNum;
        this.createTime = createTime;
        this.user_id = user_id;
        this.username = username;
        this.type = type;
        this.typeName = typeName;
    }

    public String getAccessoriesName() {
        return accessoriesName;
    }

    public void setAccessoriesName(String accessoriesName) {
        this.accessoriesName = accessoriesName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
        if(type == 1){
            typeName = "出库";
        }else if(type == 2){
            typeName = "入库";
        }
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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
        this.accessoriesName = accessories.getName();
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
