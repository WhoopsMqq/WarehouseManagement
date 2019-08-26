package com.whoops.material.pojo;

import com.whoops.accessories.pojo.Accessories;

import javax.persistence.*;

@Entity
public class MaterialStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 原材料
     */
    @OneToOne(cascade = CascadeType.DETACH,fetch = FetchType.LAZY)
    @JoinColumn(name = "material_id")
    private Material material;
    /**
     * 数量
     */
    private Long number;

    public MaterialStock() {
    }

    public MaterialStock(Material material, Long number) {
        this.material = material;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }
}
