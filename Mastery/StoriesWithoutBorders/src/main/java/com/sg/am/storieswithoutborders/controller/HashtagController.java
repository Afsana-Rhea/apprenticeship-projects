/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.am.storieswithoutborders.controller;

import com.sg.am.storieswithoutborders.dao.CategoryDao;
import com.sg.am.storieswithoutborders.dao.HashtagDao;
import com.sg.am.storieswithoutborders.dao.PostDao;
import com.sg.am.storieswithoutborders.model.Category;
import com.sg.am.storieswithoutborders.model.Hashtag;
import com.sg.am.storieswithoutborders.model.Post;
import com.sg.am.storieswithoutborders.service.ObjectAlreadyExistsException;
import com.sg.am.storieswithoutborders.service.ServiceLayer;
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
public class HashtagController {

    @Autowired
    private ServiceLayer service;

    @GetMapping("hashtags")
    public String displayHashtags(Model model) {
        List<Hashtag> hashtags = service.getAllHashtags();
        Hashtag hashtag = new Hashtag();
        List<Post> staticPosts = service.getStaticPosts();

        model.addAttribute("staticPosts", staticPosts);
        model.addAttribute("hashtags", hashtags);
        model.addAttribute("hashtag", hashtag);
        return "hashtags";
    }

    @PostMapping("addHashtag")
    public String addHashtag(@Valid Hashtag hashtag, BindingResult result, HttpServletRequest request, Model model) throws ObjectAlreadyExistsException {
        if (result.hasFieldErrors()) {
            List<Hashtag> hashtags = service.getAllHashtags();
            model.addAttribute("hashtags", hashtags);
            return "hashtags";
        }
        try {
            service.addHashtag(hashtag);
        } catch (ObjectAlreadyExistsException e) {
            FieldError error = new FieldError("hashtag", "name", "Name already exists.");
            result.addError(error);
        }
         if (result.hasFieldErrors()) {
            List<Hashtag> hashtags = service.getAllHashtags();
            model.addAttribute("hashtags", hashtags);
            return "hashtags";
        }
        
        return "redirect:hashtags";
    }

    @GetMapping("deleteHashtag")
    public String deleteHashtag(Integer id) {
        service.deleteHashtag(id);
        return "redirect:/hashtags";
    }

    @GetMapping("hashtagDetail")
    public String hashtagDetail(Integer id, Model model) {
        Hashtag hashtag = service.getHashtagById(id);
        List<Post> staticPosts = service.getStaticPosts();

        model.addAttribute("staticPosts", staticPosts);
        model.addAttribute("hashtag", hashtag);
        model.addAttribute("posts", hashtag.getPosts());
        return "hashtagDetail";
    }

    @GetMapping("editHashtag")
    public String editHashtag(Integer id, Model model) {
        List<Post> staticPosts = service.getStaticPosts();

        model.addAttribute("staticPosts", staticPosts);
        model.addAttribute("hashtag", service.getHashtagById(id));
        return "editHashtag";
    }

    @PostMapping("editHashtag")
    public String performEditHashtag(@Valid Hashtag hashtag, BindingResult result, HttpServletRequest request, Model model) throws ObjectAlreadyExistsException {
        if (result.hasErrors()) {
            List<Post> staticPosts = service.getStaticPosts();

            model.addAttribute("staticPosts", staticPosts);
            model.addAttribute("hashtag", hashtag);
            return "editHashtag";
        }
          try {
            service.updateHashtag(hashtag);
        } catch (ObjectAlreadyExistsException e) {
            FieldError error = new FieldError("hashtag", "name", "Name already exists.");
            result.addError(error);
        }
          
          if (result.hasErrors()) {
            List<Post> staticPosts = service.getStaticPosts();

            model.addAttribute("staticPosts", staticPosts);
            model.addAttribute("hashtag", hashtag);
            return "editHashtag";
        }
        
        return "redirect:/hashtags";
    }

}
