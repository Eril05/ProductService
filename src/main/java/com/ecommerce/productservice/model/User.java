package com.ecommerce.productservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.List;

@Data
public class User{

    private String username;
    private String password;
    private String email;
    private List<Role>userRoles;
    private boolean isVerified;
}
