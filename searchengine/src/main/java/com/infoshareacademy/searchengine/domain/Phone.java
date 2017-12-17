package com.infoshareacademy.searchengine.domain;

import javax.persistence.*;

@Entity
public class Phone {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String number;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}