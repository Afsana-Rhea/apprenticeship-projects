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
public class PostController {

    @Autowired
    private ServiceLayer service;

    @GetMapping("posts")
    public String displayPosts(Model model) {
        List<Category> categories = service.getAllCategories();
        List<Hashtag> hashtags = service.getAllHashtags();
        Post post = new Post();
        List<Post> staticPosts = service.getStaticPosts();
        List<Post> publishedPosts = service.gePublishedPosts();

        model.addAttribute("staticPosts", staticPosts);
        model.addAttribute("publishedPosts", publishedPosts);
        model.addAttribute("categories", categories);

        model.addAttribute("post", post);
        return "posts";
    }

    @PostMapping("addPost")
    public String addPost(@Valid Post post, BindingResult result, HttpServletRequest request, Model model) throws ObjectAlreadyExistsException {
        String categoryId = request.getParameter("categoryId");
        
//         User user = adminService.getUserByUsername(request.getParameter("userName"));
//        post.setUser(user);

 //       setDate for post
//        LocalDateTime date = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy h:mm a");
//        String text = date.format(formatter);
//        post.setPostDate(text);
        
        if (categoryId == null) {
            FieldError error = new FieldError("post", "category", "must include a category");
            result.addError(error);
            post.setCategory(new Category());
        } else {
            post.setCategory(service.getCategoryById(Integer.parseInt(categoryId)));
        }
        post.setHashtags(service.getHashtagsForPost(post.getHashtag()));

        if (result.hasErrors()) {
            List<Post> publishedPosts = service.gePublishedPosts();
            List<Category> categories = service.getAllCategories();
            List<Hashtag> hashtags = service.getAllHashtags();
            List<Post> staticPosts = service.getStaticPosts();

            model.addAttribute("staticPosts", staticPosts);
            model.addAttribute("publishedPosts", publishedPosts);
            model.addAttribute("categories", categories);

            return "posts";
        }

        try {
            service.addPost(post);
        } catch (ObjectAlreadyExistsException e) {
            FieldError error = new FieldError("post", "title", "Title already exists.");
            result.addError(error);
        }

        if (result.hasErrors()) {
            List<Post> posts = service.getAllPosts();
            List<Category> categories = service.getAllCategories();
            List<Hashtag> hashtags = service.getAllHashtags();
            List<Post> staticPosts = service.getStaticPosts();

            model.addAttribute("staticPosts", staticPosts);
            model.addAttribute("posts", posts);
            model.addAttribute("categories", categories);

            return "posts";
        }

        return "redirect:posts";
    }

    @GetMapping("deletePost")
    public String deletePost(Integer id) {
        service.deletePost(id);
        return "redirect:/posts";
    }

    @GetMapping("postDetail")
    public String postDetail(Integer id, Model model) {
        Post post = service.getPostById(id);
        List<Post> staticPosts = service.getStaticPosts();

        model.addAttribute("staticPosts", staticPosts);
        model.addAttribute("post", post);
        model.addAttribute("category", post.getCategory());
        model.addAttribute("hashtags", post.getHashtags());
        return "postDetail";
    }

    @GetMapping("editPost")
    public String editPost(Integer id, Model model) {
        Post post = service.getPostById(id);
        List<Category> categories = service.getAllCategories();
        List<Hashtag> hashtags = service.getAllHashtags();
        List<Post> staticPosts = service.getStaticPosts();

        model.addAttribute("staticPosts", staticPosts);
        model.addAttribute("post", post);
        model.addAttribute("categories", categories);
        model.addAttribute("hashtags", hashtags);
        return "editPost";
    }

    @PostMapping("editPost")
    public String performEditPost(@Valid Post post, BindingResult result, HttpServletRequest request, Model model) throws ObjectAlreadyExistsException {
        String categoryId = request.getParameter("categoryId");
        if (categoryId == null) {
            FieldError error = new FieldError("post", "category", "must include a category");
            result.addError(error);
            post.setCategory(new Category());
        } else {
            post.setCategory(service.getCategoryById(Integer.parseInt(categoryId)));
        }
        post.setHashtags(service.getHashtagsForPost(post.getHashtag()));

        if (result.hasErrors()) {
            List<Category> categories = service.getAllCategories();
            List<Hashtag> hashtags = service.getAllHashtags();
            List<Post> staticPosts = service.getStaticPosts();
            List<Post> publishedPosts = service.gePublishedPosts();

            model.addAttribute("publishedPosts", publishedPosts);
            model.addAttribute("staticPosts", staticPosts);
            model.addAttribute("post", post);
            model.addAttribute("categories", categories);

            return "editPost";
        }

        try {
            service.updatePost(post);
        } catch (ObjectAlreadyExistsException e) {
            FieldError error = new FieldError("post", "title", "Title already exists.");
            result.addError(error);
        }
        if (result.hasErrors()) {
            List<Category> categories = service.getAllCategories();
            List<Hashtag> hashtags = service.getAllHashtags();
            List<Post> staticPosts = service.getStaticPosts();
            List<Post> publishedPosts = service.gePublishedPosts();

            model.addAttribute("publishedPosts", publishedPosts);
            model.addAttribute("staticPosts", staticPosts);
            model.addAttribute("post", post);
            model.addAttribute("categories", categories);

            return "editPost";
        }

        return "redirect:/posts";
    }
}
