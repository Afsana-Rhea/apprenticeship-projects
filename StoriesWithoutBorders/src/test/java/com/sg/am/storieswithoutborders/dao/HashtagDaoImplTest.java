/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.am.storieswithoutborders.dao;

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
public class HashtagDaoImplTest {

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    HashtagDao hashtagDao;

    @Autowired
    PostDao postDao;

    public HashtagDaoImplTest() {
    }

    /**
     * Test of addHashtag and getbyID method, of class HashtagDaoImpl.
     */
    @Test
    public void testAddGetHashtag() {
        Hashtag hashtag = new Hashtag();
        hashtag.setId(1);
        hashtag.setName("cool");
        List<Post> posts = new ArrayList<Post>();
        hashtag.setPosts(posts);

        hashtagDao.addHashtag(hashtag);
        Hashtag acthashtag = hashtagDao.getHashtagById(hashtag.getId());

        assertTrue(hashtag.equals(acthashtag));
    }

    /**
     * Test of deleteHashtag method, of class HashtagDaoImpl.
     */
    @Test
    public void testDeleteHashtag() {
        Hashtag hashtag = new Hashtag();
        hashtag.setId(1);
        hashtag.setName("cool");
        List<Post> posts = new ArrayList<Post>();
        hashtag.setPosts(posts);

        hashtagDao.addHashtag(hashtag);
        hashtagDao.deleteHashtag(hashtag.getId());

        assertNull(hashtagDao.getHashtagById(hashtag.getId()));
    }

    /**
     * Test of updateHashtag method, of class HashtagDaoImpl.
     */
    @Test
    public void testUpdateHashtag() {
        Hashtag hashtag = new Hashtag();
        hashtag.setId(1);
        hashtag.setName("cool");
        List<Post> posts = new ArrayList<Post>();
        hashtag.setPosts(posts);

        hashtagDao.addHashtag(hashtag);
        hashtag.setName("notcool");
        hashtagDao.updateHashtag(hashtag);

        assertTrue(hashtag.equals(hashtagDao.getHashtagById(hashtag.getId())));
    }

    /**
     * Test of getAllHashtags method, of class HashtagDaoImpl.
     */
    @Test
    public void testGetAllHashtags() {
        Hashtag hashtag = new Hashtag();
        hashtag.setId(1);
        hashtag.setName("cool");
        List<Post> posts = new ArrayList<Post>();
        hashtag.setPosts(posts);
        hashtagDao.addHashtag(hashtag);

        Hashtag hashtag2 = new Hashtag();
        hashtag2.setId(2);
        hashtag2.setName("notcool");
        hashtag2.setPosts(posts);
        hashtagDao.addHashtag(hashtag2);
        
        assertEquals(2, hashtagDao.getAllHashtags().size());
        assertTrue(hashtagDao.getAllHashtags().contains(hashtag));
        assertTrue(hashtagDao.getAllHashtags().contains(hashtag2));
    }
}
