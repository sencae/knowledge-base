package com.diploma.knowledgebase.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "User", schema = "public")
public class User {
    private long id;
    private String username;
    private String password;
    private Long reg_id;
    @JsonIgnore
    private UserGroups userGroupsByRegId;

    @Id
    @SequenceGenerator(name = "seq", sequenceName = "id_increment", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    @Basic
    @Column(name = "username", nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "reg_id", nullable = false)
    public Long getReg_id() {
        return reg_id;
    }

    public void setReg_id(Long reg_id) {
        this.reg_id = reg_id;
    }

    @ManyToOne
    @JoinColumn(name = "reg_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public UserGroups getUserGroupsByRegId() {
        return userGroupsByRegId;
    }

    public void setUserGroupsByRegId(UserGroups userGroupsByRegId) {
        this.userGroupsByRegId = userGroupsByRegId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
