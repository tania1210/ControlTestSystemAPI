package com.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "\"subject\"")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userId;

    public Subject() {}

    public Subject(String name, User userId) {
        this.name = name;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setUserId(User user) {
        this.userId = user;
    }

    public User getUserId() {
        return userId;
    }
}
