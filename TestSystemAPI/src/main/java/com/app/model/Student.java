package com.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "\"student\"")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private byte temp;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private Group groupId;

    public Student() {}

    public Student(byte temp, User userId, Group groupId) {
        this.temp = temp;
        this.userId = userId;
        this.groupId = groupId;
    }

    public Long getId() {
        return id;
    }

    public void setTemp(byte temp) {
        this.temp = temp;
    }

    public byte getTemp(){
        return temp;
    }

    public User getUserId() {
        return userId;
    }

    public Group getGroupId() {
        return groupId;
    }

}
