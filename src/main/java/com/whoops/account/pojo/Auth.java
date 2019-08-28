package com.whoops.account.pojo;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Auth implements GrantedAuthority {

    @Id
    private Long id;

    @Column(length = 20,nullable = false)
    private String name;

    public Auth(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Auth() {
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

    @Override
    public String getAuthority() {
        return name;
    }
}
