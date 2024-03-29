package com.whoops.analysis.pojo;

public class Analysis {
    private Integer id;
    private String name;
    private String typeName;
    private Long number;
    private Double totalPrice;

    public Analysis() {
    }

    public Analysis(Integer id, String name, String typeName) {
        this.id = id;
        this.name = name;
        this.typeName = typeName;
    }

    public Analysis(String name, String typeName, Long number, Double totalPrice) {
        this.name = name;
        this.typeName = typeName;
        this.number = number;
        this.totalPrice = totalPrice;
    }

    public Analysis(Integer id, String name, String typeName, Long number, Double totalPrice) {
        this.id = id;
        this.name = name;
        this.typeName = typeName;
        this.number = number;
        this.totalPrice = totalPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
