/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.am.storieswithoutborders.service;

import com.sg.am.storieswithoutborders.dao.RoleDao;
import com.sg.am.storieswithoutborders.dao.UserDao;
import com.sg.am.storieswithoutborders.model.Role;
import com.sg.am.storieswithoutborders.model.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author afsanamiji
 */
@Service
public class AdminServiceLayer {

    @Autowired
    UserDao users;

    @Autowired
    RoleDao roles;

    public Object getAllUsers() {
        return users.getAllUsers();
    }

    public Role getRoleByRole(String role_user) {
        return roles.getRoleByRole(role_user);
    }

    public void createUser(User user) {
        users.createUser(user);
    }

    public void deleteUser(Integer id) {
       users.deleteUser(id);
    }

    public User getUserById(Integer id) {
       return users.getUserById(id);
    }

    public List getAllRoles() {
        return roles.getAllRoles();
    }

    public Role getRoleById(int id) {
        return roles.getRoleById(id);
    }

    public void updateUser(User user) {
        users.updateUser(user);
    }

}
