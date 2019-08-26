package com.whoops.accessories.pojo;

import javax.persistence.*;

@Entity
public class AccessoriesStock {
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

    public AccessoriesStock() {
    }

    public AccessoriesStock(Accessories accessories, Long number) {
        this.accessories = accessories;
        this.number = number;
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
}
