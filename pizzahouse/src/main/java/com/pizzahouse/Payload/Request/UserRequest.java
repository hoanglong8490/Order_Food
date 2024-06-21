package com.pizzahouse.Payload.Request;

import com.pizzahouse.Entity.Role;

import java.util.Date;

public class UserRequest {
    private String email;
    private String password;
    private String roleName;
    private int roleId;


    public UserRequest(String email, String password, String roleName, int roleId) {
        this.email = email;
        this.password = password;
        this.roleName = roleName;
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
