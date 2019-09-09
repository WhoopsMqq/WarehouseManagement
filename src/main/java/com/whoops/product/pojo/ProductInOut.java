package com.whoops.product.pojo;


import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class  ProductInOut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 成品
     */
    @OneToOne(cascade = CascadeType.DETACH,fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(length = 20)
    private String productName;
    /**
     * 数量
     */
    @Column(length = 8)
    private Long number;
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

    public ProductInOut() {
    }

    public ProductInOut(Product product, Long number, String formNum, Timestamp createTime, Long user_id, String username, Integer type,String typeName) {
        this.product = product;
        this.number = number;
        this.formNum = formNum;
        this.createTime = createTime;
        this.user_id = user_id;
        this.username = username;
        this.type = type;
        this.typeName = typeName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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
        this.productName = product.getName()+"/"+product.getSize()+"/"+product.getColor();
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
}
