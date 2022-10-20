package com.IronHack.MidtermProject.Midterm.Project.entity.users;

import javax.persistence.*;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String role;

    @ManyToOne
    private User user;

    //--------------------------- CONSTRUCTORS: -------------------------

    public Role() {
    }

    public Role(String role, User user) {
        this.role = role;
        this.user = user;
    }

    //--------------------------- GETTERS & SETTERS: -------------------------


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
