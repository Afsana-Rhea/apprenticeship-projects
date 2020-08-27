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
public class PostDaoImpl implements PostDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    @Transactional
    public void addPost(Post post) {
        final String ADD_POST = "INSERT INTO post (postTitle, postDescription, isPublished, isStatic, categoryId) VALUES (?, ?, ?, ?, ?)";
        jdbc.update(ADD_POST, post.getTitle(), post.getDescription(), post.isIsPublished(), post.isIsStatic(), post.getCategory().getId());
        post.setId(jdbc.queryForObject("select LAST_INSERT_ID()", Integer.class));
        insertHashtagsPosts(post);
    }

    private void insertHashtagsPosts(Post post) {
        final String INSERT_POST_HASHTAG = "INSERT INTO postHashtag(postId, hashtagId) VALUES(?,?)";
        for (Hashtag hashtag : post.getHashtags()) {
            jdbc.update(INSERT_POST_HASHTAG, post.getId(), hashtag.getId());
        }
    }

    @Override
    public void deletePost(int postId) {
        final String DELETE_POST_HASHTAG = "DELETE FROM postHashtag WHERE postId = ?";
        jdbc.update(DELETE_POST_HASHTAG, postId);

        final String DELETE_POST = "DELETE FROM post WHERE postId = ?";
        jdbc.update(DELETE_POST, postId);
    }

    @Override
    public void updatePost(Post post) {
        final String UPDATE_POST = "UPDATE post SET postTitle = ?, postDescription = ?, isPublished = ?, isStatic = ?, categoryId = ?  WHERE postId = ?";
        jdbc.update(UPDATE_POST, post.getTitle(), post.getDescription(), post.isIsPublished(), post.isIsStatic(), post.getCategory().getId(), post.getId());

        final String DELETE_POST_HASHTAG = "DELETE FROM postHashtag WHERE postId  = ?";
        jdbc.update(DELETE_POST_HASHTAG, post.getId());

        insertHashtagsPosts(post);
    }

    @Override
    public List<Post> getAllPosts() {
        final String GET_POST = "SELECT * FROM post";
        List<Post> posts = jdbc.query(GET_POST, new PostMapper());
        return associateHashtagsandCategories(posts);

    }

    private List<Post> associateHashtagsandCategories(List<Post> posts) {
        for (Post post : posts) {
            post.setHashtags(getHashtagsForPost(post));
            post.setCategory(getCategoryForPost(post));
        }
        return posts;
    }

    @Override
    public Post getPostByID(int id) {
        try {
            final String GET_POST_BY_ID = "SELECT * FROM post WHERE postId = ?";
            Post post = jdbc.queryForObject(GET_POST_BY_ID, new PostMapper(), id);
            post.setHashtags(getHashtagsForPost(post));
            post.setCategory(getCategoryForPost(post));
            return post;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    private List<Hashtag> getHashtagsForPost(Post post) {
        final String GET_HASHTAGS_FOR_POST = "SELECT hashtag.hashtagId, hashtag.hashtagName FROM hashtag JOIN postHashtag ON postHashtag.hashtagId = hashtag.hashtagId WHERE postHashtag.postId  = ?";
        return jdbc.query(GET_HASHTAGS_FOR_POST, new HashtagMapper(), post.getId());

    }

    private Category getCategoryForPost(Post post) {

        final String GET_CATEGORY_FOR_POST = "SELECT * FROM category JOIN post  ON post.categoryId = category.categoryId WHERE post.postId = ?";

        return jdbc.queryForObject(GET_CATEGORY_FOR_POST, new CategoryMapper(), post.getId());

    }

}
