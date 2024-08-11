package com.binodcoder.merokaamapi.entity;

import jakarta.persistence.*;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "users_type")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_type_id")
    private int userTypeId;

    @ToString.Exclude
    @Enumerated(EnumType.STRING)
    @Column(length = 20, name = "user_type_name")
    private AppRole roleName;

//    @OneToMany(targetEntity = Users.class, mappedBy = "userTypeId", cascade = CascadeType.ALL)
//    private List<Users> users;

    public Role() {
    }

    public Role(int userTypeId, AppRole roleName, List<Users> users) {
        this.userTypeId = userTypeId;
        this.roleName = roleName;
       // this.users = users;
    }

    public int getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(int userTypeId) {
        this.userTypeId = userTypeId;
    }

    public AppRole getRoleName() {
        return roleName;
    }

    public void setRoleName(AppRole roleName) {
        this.roleName = roleName;
    }

//    public List<Users> getUsers() {
//        return users;
//    }

//    public void setUsers(List<Users> users) {
//        this.users = users;
//    }

    @Override
    public String toString() {
        return "UsersType{" +
                "userTypeId=" + userTypeId +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
