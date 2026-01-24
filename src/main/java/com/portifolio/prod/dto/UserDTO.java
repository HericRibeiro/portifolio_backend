package com.portifolio.prod.dto;

import com.portifolio.prod.enums.Role;

public class UserDTO {
    
    private Long id;
    private String name;
    private String mail;
    private Role role;

    public UserDTO() {}

    public UserDTO(Long id, String name, String mail, Role role) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.role = role;
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
    
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
