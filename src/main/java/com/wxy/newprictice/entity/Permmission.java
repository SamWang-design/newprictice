package com.wxy.newprictice.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "u_permission")
public class Permmission {
    @Id
    private Integer id;

    @Column(name = "url", unique = true, nullable = false, length = 64)
    private String url;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Column(name = "rid", nullable = false, length = 64)
    private Integer rid;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
