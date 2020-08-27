/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.am.storieswithoutborders.dao;

import com.sg.am.storieswithoutborders.model.Category;
import com.sg.am.storieswithoutborders.model.Post;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
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
public class CategoryDaoImplTest {

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    HashtagDao hashtagDao;

    @Autowired
    PostDao postDao;

    public CategoryDaoImplTest() {

    }

    /**
     * Test of addCategory and getbyId method, of class CategoryDaoImpl.
     */
    @Test
    public void testGetAddCategory() {
        Category category = new Category();
        category.setId(1);
        category.setName("fiction");
        List<Post> posts = new ArrayList<Post>();
        category.setPosts(posts);

        categoryDao.addCategory(category);
        Category actCategory = categoryDao.getCategoryById(category.getId());

        assertEquals(category.getName(), actCategory.getName());
        assertEquals(category.getId(), actCategory.getId());
        assertEquals(category.getPosts(), actCategory.getPosts());
        assertTrue(category.equals(actCategory));

    }

    /**
     * Test of deleteCategory method, of class CategoryDaoImpl.
     */
    @Test
    public void testDeleteCategory() {
        Category category = new Category();
        category.setId(1);
        category.setName("fiction");
        List<Post> posts = new ArrayList<Post>();
        category.setPosts(posts);

        categoryDao.addCategory(category);
        categoryDao.deleteCategory(category.getId());

        assertNull(categoryDao.getCategoryById(category.getId()));
    }

    /**
     * Test of updateCategory method, of class CategoryDaoImpl.
     */
    @Test
    public void testUpdateCategory() {
        Category category = new Category();
        category.setId(1);
        category.setName("fiction");
        List<Post> posts = new ArrayList<Post>();
        category.setPosts(posts);

        categoryDao.addCategory(category);
        category.setName("nonfiction");
        categoryDao.updateCategory(category);
        Category actCategory = categoryDao.getCategoryById(category.getId());

        assertEquals(category.getName(), actCategory.getName());
        assertEquals(category.getId(), actCategory.getId());
        assertEquals(category.getPosts(), actCategory.getPosts());
        assertTrue(category.equals(actCategory));

    }

    /**
     * Test of getAllCategories method, of class CategoryDaoImpl.
     */
    @Test
    public void testGetAllCategories() {
        Category category = new Category();
        category.setId(1);
        category.setName("fiction");
        List<Post> posts = new ArrayList<Post>();
        category.setPosts(posts);
        categoryDao.addCategory(category);
        
        Category category2 = new Category();
        category2.setId(1);
        category2.setName("fiction");
        category2.setPosts(posts);
        categoryDao.addCategory(category2);
        
        assertEquals(2, categoryDao.getAllCategories().size());
        assertTrue(categoryDao.getAllCategories().contains(category));
        assertTrue(categoryDao.getAllCategories().contains(category2));
    }
}
