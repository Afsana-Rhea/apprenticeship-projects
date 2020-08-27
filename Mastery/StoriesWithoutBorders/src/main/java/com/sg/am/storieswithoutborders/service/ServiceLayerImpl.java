/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.am.storieswithoutborders.service;

import com.sg.am.storieswithoutborders.dao.CategoryDao;
import com.sg.am.storieswithoutborders.dao.HashtagDao;
import com.sg.am.storieswithoutborders.dao.PostDao;
import com.sg.am.storieswithoutborders.model.Category;
import com.sg.am.storieswithoutborders.model.Hashtag;
import com.sg.am.storieswithoutborders.model.Post;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceLayerImpl implements ServiceLayer {

    @Autowired
    HashtagDao hashtagDao;

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    PostDao postDao;

    @Override
    public void addHashtag(Hashtag hashtag) throws ObjectAlreadyExistsException {
        for (Hashtag h : hashtagDao.getAllHashtags()) {
            if (hashtag.getName().equalsIgnoreCase(h.getName())) {
                throw new ObjectAlreadyExistsException();
            }
        }
        hashtagDao.addHashtag(hashtag);
    }

    @Override
    public List<Hashtag> getAllHashtags() {
        return hashtagDao.getAllHashtags();
    }

    @Override
    public void addCategory(Category category) throws ObjectAlreadyExistsException {
        for (Category c : categoryDao.getAllCategories()) {
            if (category.getName().equalsIgnoreCase(c.getName())) {
                throw new ObjectAlreadyExistsException();
            }
        }
        categoryDao.addCategory(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryDao.getAllCategories();
    }

    @Override
    public List<Post> getAllPosts() {
        return postDao.getAllPosts();
    }

    @Override
    public void addPost(Post post) throws ObjectAlreadyExistsException {
        
        for (Post p : postDao.getAllPosts()) {
            if (post.getTitle().equalsIgnoreCase(p.getTitle())) {
                throw new ObjectAlreadyExistsException();
            }
        }
        postDao.addPost(post);
    }

    @Override
    public Category getCategoryById(int parseInt) {
        Category category = categoryDao.getCategoryById(parseInt);
        if (category == null) {
            return null;
        }
        return category;
    }

    @Override
    public List<Hashtag> setHashtagsForPost(String[] hashtagIds) {
        List<Hashtag> hashtags = new ArrayList<>();
        for (String hashtagId : hashtagIds) {
            hashtags.add(hashtagDao.getHashtagById(Integer.parseInt(hashtagId)));
        }
        return hashtags;
    }

    @Override
    public void deleteHashtag(Integer id) {
        hashtagDao.deleteHashtag(id);
    }

    @Override
    public void deleteCategory(Integer id) {
        categoryDao.deleteCategory(id);
    }

    @Override
    public void deletePost(Integer id) {
        postDao.deletePost(id);
    }

    @Override
    public Post getPostById(Integer id) {
        Post post = postDao.getPostByID(id);
        if (post == null) {
            return null;
        }
        return post;
    }

    @Override
    public Hashtag getHashtagById(Integer id) {
        Hashtag hashtag = hashtagDao.getHashtagById(id);
        if (hashtag == null) {
            return null;
        }
        return hashtag;
    }

    @Override
    public void updateCategory(Category category) throws ObjectAlreadyExistsException {
        String catName = categoryDao.getCategoryById(category.getId()).getName();
        for (Category c : categoryDao.getAllCategories()) {
            if (category.getName().equalsIgnoreCase(c.getName()) && !category.getName().equals(catName)) {
                throw new ObjectAlreadyExistsException();
            }
        }
        categoryDao.updateCategory(category);
    }

    @Override
    public void updateHashtag(Hashtag hashtag) throws ObjectAlreadyExistsException {
        String hashtagName = hashtagDao.getHashtagById(hashtag.getId()).getName();
        for (Hashtag h : hashtagDao.getAllHashtags()) {
            if (hashtag.getName().equalsIgnoreCase(h.getName()) && !hashtag.getName().equalsIgnoreCase(hashtagName)) {
                throw new ObjectAlreadyExistsException();
            }
        }
        hashtagDao.updateHashtag(hashtag);
    }

    @Override
    public void updatePost(Post post) throws ObjectAlreadyExistsException {
        String postName = postDao.getPostByID(post.getId()).getTitle();
        for (Post p : postDao.getAllPosts()) {
            if (post.getTitle().equalsIgnoreCase(p.getTitle()) && !post.getTitle().equals(postName)) {
                throw new ObjectAlreadyExistsException();
            }
        }
        postDao.updatePost(post);
    }

    @Override
    public List<Post> getStaticPosts() {
        List<Post> posts = postDao.getAllPosts();
        List<Post> staticPosts = new ArrayList<Post>();
        for (Post aPost : posts) {
            if (aPost.isIsStatic()) {
                staticPosts.add(aPost);
            }
        }
        return staticPosts;
    }

    @Override //creates hashtag from hashtag string
    public List<Hashtag> getHashtagsForPost(String hashtag) {
        String str[] = hashtag.split(" ");
        List<String> hashtags = Arrays.asList(str);
        List<Hashtag> hashtagObjects = new ArrayList();
        List<Hashtag> allHashtags = hashtagDao.getAllHashtags();
        
        for (String s : hashtags) {
            if (!s.isEmpty()) {
                Hashtag newHashtag = new Hashtag();
                        newHashtag.setName(s);
                        if(hashtagDao.getHashtagByName(newHashtag.getName()) == null){
                            hashtagDao.addHashtag(newHashtag);
                            hashtagObjects.add(newHashtag);
                        }else{
                            hashtagObjects.add(hashtagDao.getHashtagByName(newHashtag.getName()));
                        }
                        
                    }
                }
        
        

        return hashtagObjects;
    }

    @Override
    public List<Post> getUnpublishedPosts() {
        List<Post> posts = postDao.getAllPosts();
        List<Post> unpublishedPosts = new ArrayList<Post>();
        for (Post aPost : posts) {
            if (!aPost.isIsPublished() && !aPost.isIsStatic()) {
                unpublishedPosts.add(aPost);
            }
        }
        return unpublishedPosts;
    }

    @Override
    public List<Post> gePublishedPosts() {
    List<Post> posts = postDao.getAllPosts();
        List<Post> publishedPosts = new ArrayList<Post>();
        for (Post aPost : posts) {
            if (aPost.isIsPublished()) {
                publishedPosts.add(aPost);
            }
        }
        return publishedPosts;
    }

    @Override
    public void publishPost(Integer id) {
      Post post = postDao.getPostByID(id);
      post.setIsPublished(true);
      postDao.updatePost(post);
    }

}
