/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.am.storieswithoutborders.controller;

import com.sg.am.storieswithoutborders.model.Category;
import com.sg.am.storieswithoutborders.model.Hashtag;
import com.sg.am.storieswithoutborders.model.Post;
import com.sg.am.storieswithoutborders.service.ObjectAlreadyExistsException;
import com.sg.am.storieswithoutborders.service.ServiceLayer;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author afsanamiji
 */
@Controller
public class CategoryController {

    @Autowired
    private ServiceLayer service;

    @GetMapping("categories")
    public String displayCategories(Model model) {
        List<Category> categories = service.getAllCategories();
        Category category = new Category();
        List<Post> staticPosts = service.getStaticPosts();

        model.addAttribute("staticPosts", staticPosts);
        model.addAttribute("categories", categories);
        model.addAttribute("category", category);
        return "categories";
    }

    @PostMapping("addCategory")
    public String addCategory(@Valid Category category, BindingResult result, HttpServletRequest request, Model model) throws ObjectAlreadyExistsException {
        
        if (result.hasErrors()) {
            List<Category> categories = service.getAllCategories();
            model.addAttribute("categories", categories);

            return "categories";
        }
        try {
            service.addCategory(category);
        } catch (ObjectAlreadyExistsException e) {
            FieldError error = new FieldError("category", "name", "Name already exists.");
            result.addError(error);
        }
          if (result.hasErrors()) {
            List<Category> categories = service.getAllCategories();
            model.addAttribute("categories", categories);

            return "categories";
        }

        return "redirect:categories";
    }

    @GetMapping("deleteCategory")
    public String deleteCategory(Integer id) {
        service.deleteCategory(id);
        return "redirect:/categories";
    }

    @GetMapping("categoryDetail")
    public String categoryDetail(Integer id, Model model) {
        Category category = service.getCategoryById(id);
        List<Post> staticPosts = service.getStaticPosts();

        model.addAttribute("staticPosts", staticPosts);
        model.addAttribute("category", category);
        model.addAttribute("posts", category.getPosts());
        return "categoryDetail";
    }

    @GetMapping("editCategory")
    public String editCategory(Integer id, Model model) {
        List<Post> staticPosts = service.getStaticPosts();

        model.addAttribute("staticPosts", staticPosts);
        model.addAttribute("category", service.getCategoryById(id));
        return "editCategory";
    }

    @PostMapping("editCategory")
    public String performEditCategory(@Valid Category category, BindingResult result, HttpServletRequest request, Model model) throws ObjectAlreadyExistsException {
       

        if (result.hasErrors()) {
            List<Post> staticPosts = service.getStaticPosts();

            model.addAttribute("staticPosts", staticPosts);
            model.addAttribute("category", category);
            return "editCategory";
        }
        
         try {
            service.updateCategory(category);
        } catch (ObjectAlreadyExistsException e) {
            FieldError error = new FieldError("category", "name", "Name already exists.");
            result.addError(error);
        }
         
          if (result.hasErrors()) {
            List<Post> staticPosts = service.getStaticPosts();

            model.addAttribute("staticPosts", staticPosts);
            model.addAttribute("category", category);
            return "editCategory";
        }

        return "redirect:/categories";
    }

}
