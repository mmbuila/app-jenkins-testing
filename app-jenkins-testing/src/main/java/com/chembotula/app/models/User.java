package com.chembotula.app.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private Long id;
    private String username;
    private String password;
    private boolean isActive;
    private List<Role> roles = new ArrayList<>();
    public void addRole(Role role) {
      roles.add(role);
    }
}
