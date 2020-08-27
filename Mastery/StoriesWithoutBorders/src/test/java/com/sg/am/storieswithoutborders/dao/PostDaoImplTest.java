/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.am.storieswithoutborders.dao;

import com.sg.am.storieswithoutborders.model.Category;
import com.sg.am.storieswithoutborders.model.Hashtag;
import com.sg.am.storieswithoutborders.model.Post;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author afsanamiji
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PostDaoImplTest {

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    HashtagDao hashtagDao;

    @Autowired
    PostDao postDao;

    public PostDaoImplTest() {
    }

    /**
     * Test of addPost and getbyID method, of class PostDaoImpl.
     */
    @Test
    public void testAddGetPost() {
        Post post = new Post();
        post.setId(1);
        post.setTitle("Walden");
        post.setDescription("A Solitary Life");
        post.setIsPublished(false);
        post.setIsStatic(false);

        Category category = new Category();

        category.setName("fiction");

        categoryDao.addCategory(category);
        post.setCategory(category);

        Hashtag hashtag = new Hashtag();
        List<Hashtag> hashtags = new ArrayList<Hashtag>();

        hashtag.setName("cool");

        hashtagDao.addHashtag(hashtag);
        hashtags.add(hashtag);
        post.setHashtags(hashtags);

        postDao.addPost(post);
        int id = post.getId();
        Post actPost = postDao.getPostByID(id);

        assertEquals(post.getTitle(), actPost.getTitle());
        assertTrue(post.equals(actPost));

    }

    /**
     * Test of deletePost method, of class PostDaoImpl.
     */
    @Test
    public void testDeletePost() {
        Post post = new Post();
        post.setId(1);
        post.setTitle("Walden");
        post.setDescription("A Solitary Life");
        post.setIsPublished(false);
        post.setIsStatic(false);

        Category category = new Category();

        category.setName("fiction");

        categoryDao.addCategory(category);
        post.setCategory(category);

        Hashtag hashtag = new Hashtag();
        List<Hashtag> hashtags = new ArrayList<Hashtag>();

        hashtag.setName("cool");

        hashtagDao.addHashtag(hashtag);
        hashtags.add(hashtag);
        post.setHashtags(hashtags);

        postDao.addPost(post);
        postDao.deletePost(post.getId());

        assertNull(postDao.getPostByID(post.getId()));
    }

    /**
     * Test of updatePost method, of class PostDaoImpl.
     */
    @Test
    public void testUpdatePost() {
        Post post = new Post();
        post.setId(1);
        post.setTitle("Walden");
        post.setDescription("A Solitary Life");
        post.setIsPublished(false);
        post.setIsStatic(false);

        Category category = new Category();

        category.setName("fiction");

        categoryDao.addCategory(category);
        post.setCategory(category);

        Hashtag hashtag = new Hashtag();
        List<Hashtag> hashtags = new ArrayList<Hashtag>();

        hashtag.setName("cool");

        hashtagDao.addHashtag(hashtag);
        hashtags.add(hashtag);
        post.setHashtags(hashtags);

        postDao.addPost(post);
        post.setTitle("Dog");
        postDao.updatePost(post);

        assertTrue(postDao.getPostByID(post.getId()).equals(post));
    }

    /**
     * Test of getAllPosts method, of class PostDaoImpl.
     */
    @Test
    public void testGetAllPosts() {
        Post post = new Post();
        post.setId(1);
        post.setTitle("Walden");
        post.setDescription("A Solitary Life");
        post.setIsPublished(false);
        post.setIsStatic(false);

        Category category = new Category();

        category.setName("fiction");

        categoryDao.addCategory(category);
        post.setCategory(category);

        Hashtag hashtag = new Hashtag();
        List<Hashtag> hashtags = new ArrayList<Hashtag>();

        hashtag.setName("cool");

        hashtagDao.addHashtag(hashtag);
        hashtags.add(hashtag);
        post.setHashtags(hashtags);

        postDao.addPost(post);
        
        Post post2 = new Post();
        post2.setId(2);
        post2.setTitle("Walden");
        post2.setDescription("A Solitary Life");
        post2.setIsPublished(false);
        post2.setIsStatic(false);

        post2.setCategory(category);
        post2.setHashtags(hashtags);

        postDao.addPost(post2);

        assertEquals(2, postDao.getAllPosts().size());
        assertTrue(postDao.getAllPosts().contains(post));
         assertTrue(postDao.getAllPosts().contains(post2));
    }
}
