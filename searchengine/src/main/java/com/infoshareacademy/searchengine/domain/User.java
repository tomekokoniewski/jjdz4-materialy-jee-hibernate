package com.infoshareacademy.searchengine.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "selectAll",query = "from User "),
        @NamedQuery(name = "selectUserById",query = "from User where id=:id")
})

//        @NamedQuery(name = "update",query = "update User set name=:name,surname=:surname,login=:login,age=:age, gender=:gender where =:modelToUpdate")})

public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(nullable=false, unique=true)
    private int id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String login;
    @Column
    private int age;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @OneToMany
    private List<Phone> phones = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }
}