/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.am.storieswithoutborders.service;

import com.sg.am.storieswithoutborders.model.Category;
import com.sg.am.storieswithoutborders.model.Hashtag;
import com.sg.am.storieswithoutborders.model.Post;
import java.util.List;

/**
 *
 * @author afsanamiji
 */
public interface ServiceLayer {

    public void addHashtag(Hashtag hashtag) throws ObjectAlreadyExistsException;

    public List<Hashtag> getAllHashtags();

    public void addCategory(Category category) throws ObjectAlreadyExistsException;

    public List<Category> getAllCategories();

    public List<Post> getAllPosts();

    public void addPost(Post post) throws ObjectAlreadyExistsException;

    public Category getCategoryById(int parseInt);

    public List<Hashtag> setHashtagsForPost(String[] hashtagIds);

    public void deleteHashtag(Integer id);

    public void deleteCategory(Integer id);

    public void deletePost(Integer id);

    public Post getPostById(Integer id);

    public Hashtag getHashtagById(Integer id);

    public void updateCategory(Category category) throws ObjectAlreadyExistsException;

    public void updateHashtag(Hashtag hashtag) throws ObjectAlreadyExistsException;

    public void updatePost(Post post) throws ObjectAlreadyExistsException;

    public List<Post> getStaticPosts();

    public List<Hashtag> getHashtagsForPost(String hashtag);

    public List<Post> getUnpublishedPosts();

    public List<Post> gePublishedPosts();

    public void publishPost(Integer id);
    
}
