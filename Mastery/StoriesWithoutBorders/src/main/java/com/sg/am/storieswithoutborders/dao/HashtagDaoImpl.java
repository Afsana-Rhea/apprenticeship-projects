/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.am.storieswithoutborders.dao;

import com.sg.am.storieswithoutborders.model.Category;
import com.sg.am.storieswithoutborders.model.Hashtag;
import com.sg.am.storieswithoutborders.model.Post;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class HashtagDaoImpl implements HashtagDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    @Transactional
    public void addHashtag(Hashtag hashtag) {
    final String ADD_HASHTAG = "INSERT INTO hashtag(hashtagName) VALUES (?)";
        jdbc.update(ADD_HASHTAG, hashtag.getName());
        hashtag.setId(jdbc.queryForObject("select LAST_INSERT_ID()", Integer.class));
    }

    @Override
    public void deleteHashtag(int hashtagId) {
        final String DELETE_POST_HASHTAG= "DELETE FROM postHashtag WHERE hashtagId = ?";
        jdbc.update(DELETE_POST_HASHTAG, hashtagId);
        
        final String DELETE_HASHTAG= "DELETE FROM hashtag WHERE hashtagId = ?";
        jdbc.update(DELETE_HASHTAG, hashtagId);
    }

    @Override
    public void updateHashtag(Hashtag hashtag) {
        final String UPDATE_HASHTAG = "UPDATE hashtag SET hashtagName = ? WHERE hashtagId = ?";
        jdbc.update(UPDATE_HASHTAG, hashtag.getName(), hashtag.getId());
    }

    @Override
    public List<Hashtag> getAllHashtags() {
        final String GET_ALL_HASHTAGS = "SELECT * FROM hashtag";
        List<Hashtag> hashtags = jdbc.query(GET_ALL_HASHTAGS, new HashtagMapper());
        associatePosts(hashtags);
        return hashtags;
    }

    private void associatePosts(List<Hashtag> hashtags) {
        for (Hashtag hashtag : hashtags) {
            hashtag.setPosts(getPostsForHashtag(hashtag));
        }
    }

    @Override
    public Hashtag getHashtagById(int id) {
        try {
            final String GET_HASHTAG_BY_ID = "SELECT * FROM hashtag WHERE hashtagId = ?";
            Hashtag hashtag = jdbc.queryForObject(GET_HASHTAG_BY_ID, new HashtagMapper(), id);
            hashtag.setPosts(getPostsForHashtag(hashtag));
            return hashtag;
        } catch (DataAccessException ex) {
            return null;
        }
    }
    
     @Override
    public Hashtag getHashtagByName (String name) {
        try {
            final String GET_HASHTAG_BY_NAME = "SELECT * FROM hashtag WHERE hashtagName = ?";
            Hashtag hashtag = jdbc.queryForObject(GET_HASHTAG_BY_NAME, new HashtagMapper(), name);
            hashtag.setPosts(getPostsForHashtag(hashtag));
            return hashtag;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    private List<Post> getPostsForHashtag(Hashtag hashtag) {
        final String GET_POSTS_FOR_HASHTAG = "SELECT * FROM post JOIN postHashtag ON postHashtag.postId = post.postId WHERE postHashtag.hashtagId  = ?";
        List<Post> posts = jdbc.query(GET_POSTS_FOR_HASHTAG, new PostMapper(), hashtag.getId());
        return posts;
    }

}
