/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.am.storieswithoutborders.dao;

import com.sg.am.storieswithoutborders.model.Category;
import com.sg.am.storieswithoutborders.model.Post;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CategoryDaoImpl implements CategoryDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    @Transactional
    public void addCategory(Category category) {
        final String ADD_CATEGORY = "INSERT INTO category(categoryName) VALUES (?)";
        jdbc.update(ADD_CATEGORY, category.getName());
        category.setId(jdbc.queryForObject("select LAST_INSERT_ID()", Integer.class));
    }

    @Override
    public void deleteCategory(int id) {
        final String DELETE_POST_HASHTAG = "DELETE postHashtag.* FROM postHashtag JOIN post ON postHashtag.postId = post.postId WHERE post.categoryId = ?";
        jdbc.update(DELETE_POST_HASHTAG, id);
        
        final String DELETE_CATEGORY_POST = "DELETE FROM post WHERE categoryId = ?";
        jdbc.update(DELETE_CATEGORY_POST, id);

        final String DELETE_CATEGORY = "DELETE FROM category WHERE categoryId = ?";
        jdbc.update(DELETE_CATEGORY, id);
    }

    @Override
    public void updateCategory(Category category) {
        final String UPDATE_CATEGORY = "UPDATE category SET categoryName = ? WHERE categoryId = ?";
        jdbc.update(UPDATE_CATEGORY, category.getName(), category.getId());
    } 

    @Override
    public List<Category> getAllCategories() {
        final String GET_ALL_CATEGORIES = "SELECT * FROM category";
        List<Category> categories = jdbc.query(GET_ALL_CATEGORIES, new CategoryMapper());
        for (Category category : categories) {
            category.setPosts(getPostsForCategory(category));
        }
        return categories;
    }

    @Override
    public Category getCategoryById(int id) {
        try {
            final String GET_CATEGORY_BY_ID = "SELECT * FROM category WHERE categoryId = ?";
            Category category = jdbc.queryForObject(GET_CATEGORY_BY_ID, new CategoryMapper(), id);
            category.setPosts(getPostsForCategory(category));
            return category;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    private List<Post> getPostsForCategory(Category category) {
        final String GET_POSTS_FOR_CATEGORY = "SELECT * FROM post WHERE categoryId = ?";
        List<Post> posts = jdbc.query(GET_POSTS_FOR_CATEGORY, new PostMapper(), category.getId());
        return posts;
    }
}
