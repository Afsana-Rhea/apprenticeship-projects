/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.am.storieswithoutborders.controller;

import com.sg.am.storieswithoutborders.dao.RoleDao;
import com.sg.am.storieswithoutborders.dao.UserDao;
import com.sg.am.storieswithoutborders.model.Category;
import com.sg.am.storieswithoutborders.model.Hashtag;
import com.sg.am.storieswithoutborders.model.Post;
import com.sg.am.storieswithoutborders.model.Role;
import com.sg.am.storieswithoutborders.model.User;
import com.sg.am.storieswithoutborders.service.AdminServiceLayer;
import com.sg.am.storieswithoutborders.service.ServiceLayer;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author afsanamiji
 */
@Controller
public class AdministratorController {

    @Autowired
    private ServiceLayer service;
    
    
    @Autowired
    private AdminServiceLayer adminService;
    

    @Autowired
    UserDao users;

    @Autowired
    RoleDao roles;

    @Autowired
    PasswordEncoder encoder;

    Set<ConstraintViolation<User>> violations = new HashSet<>();
   

    @GetMapping("administrator")
    public String displayPosts(Model model) {
        Post post = new Post();
        List<Post> staticPosts = service.getStaticPosts();
        List<Post> unpublishedPosts = service.getUnpublishedPosts();
        List<Post> publishedPosts = service.gePublishedPosts();
        model.addAttribute("users", adminService.getAllUsers());
        model.addAttribute("unpublishedPosts", unpublishedPosts);
        model.addAttribute("staticPosts", staticPosts);
        model.addAttribute("errors", violations);

        return "administrator";
    }

    @PostMapping("/addUser")
    public String addUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(user);
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(adminService.getRoleByRole("ROLE_USER"));
        user.setRoles(userRoles);

        user.setPassword(encoder.encode(password));
        user.setEnabled(true);
        if (violations.isEmpty()) {
            adminService.createUser(user);
        }

        return "redirect:/administrator";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(Integer id) {
        adminService.deleteUser(id);
        return "redirect:/administrator";
    }

    @GetMapping("/editUser")
    public String editUserDisplay(Model model, Integer id, Integer error) {
        User user = adminService.getUserById(id);
        List roleList = adminService.getAllRoles();

        model.addAttribute("user", user);
        model.addAttribute("roles", roleList);

        if (error != null) {
            if (error == 1) {
                model.addAttribute("error", "Passwords did not match, password was not updated.");
            }
        }

        return "editUser";
    }

    @PostMapping(value = "/editUser")
    public String editUserAction(String[] roleIdList, Boolean enabled, Integer id) {
        User user = adminService.getUserById(id);
        if (enabled != null) {
            user.setEnabled(enabled);
        } else {
            user.setEnabled(false);
        }

        Set<Role> roleList = new HashSet<>();
        for (String roleId : roleIdList) {
            Role role = adminService.getRoleById(Integer.parseInt(roleId));
            roleList.add(role);
        }
        user.setRoles(roleList);
        adminService.updateUser(user);

        return "redirect:/administrator";
    }

    @PostMapping("editPassword")
    public String editPassword(Integer id, String password, String confirmPassword) {
        User user = adminService.getUserById(id);
        
        if(password.equals(null)){
            return "redirect:/editUser?id=" + id + "&error=1";
        }

        if (password.equals(confirmPassword)) {
            user.setPassword(encoder.encode(password));
            adminService.updateUser(user);
            return "redirect:/administrator";
        } else {
            return "redirect:/editUser?id=" + id + "&error=1";
        }
    }

    @GetMapping("publish")
    public String publishPost(Integer id) {
        service.publishPost(id);
        return "redirect:/administrator";
    }

}
