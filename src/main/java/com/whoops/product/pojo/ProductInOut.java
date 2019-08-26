package com.whoops.product.pojo;


import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class ProductInOut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 成品
     */
    @OneToOne(cascade = CascadeType.DETACH,fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
    /**
     * 数量
     */
    private Long number;
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

    public ProductInOut() {
    }

    public ProductInOut(Product product, Long number, String formNum, Timestamp createTime, Long user_id, String username) {
        this.product = product;
        this.number = number;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
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
