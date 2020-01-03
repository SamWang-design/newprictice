package com.wxy.newprictice.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "u_permission")
public class Permmission {
    @Id
    private long id;

    @Column(name = "url", unique = true, nullable = false, length = 64)
    private String url;

    @Column(name = "name", nullable = false, length = 64)
    private String name;
}
