package com.portifolio.prod.dto;

import com.portifolio.prod.enums.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreatedUserDTO {
    
    @NotBlank
    private String name;
    
    @Email
    @NotBlank
    private String mail;

    @NotBlank
    @Size(min = 8)
    private String password;

    @NotNull
    private Role role;

    public CreatedUserDTO() {}

    public CreatedUserDTO(String name, String mail, String password, Role role) {
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.role = role;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
