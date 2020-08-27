/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.am.storieswithoutborders.controller;

import com.sg.am.storieswithoutborders.model.Post;
import com.sg.am.storieswithoutborders.service.ServiceLayer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author afsanamiji
 */
@Controller
public class LoginController {

    @Autowired
    private ServiceLayer service;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        List<Post> staticPosts = service.getStaticPosts();
        model.addAttribute("staticPosts", staticPosts);
        return "login";
    }

}
