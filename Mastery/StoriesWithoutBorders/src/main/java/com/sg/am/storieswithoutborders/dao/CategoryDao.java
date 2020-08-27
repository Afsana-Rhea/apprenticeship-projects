/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.am.storieswithoutborders.dao;

import com.sg.am.storieswithoutborders.model.Category;
import java.util.List;

/**
 *
 * @author afsanamiji
 */
public interface CategoryDao {

    public void addCategory(Category category);

    public void deleteCategory(int id);

    public void updateCategory(Category category);

    public List<Category> getAllCategories();
    
    public Category getCategoryById(int id);

}
