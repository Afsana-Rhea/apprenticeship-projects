/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.am.storieswithoutborders.dao;

import com.sg.am.storieswithoutborders.model.Category;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author afsanamiji
 */
public class CategoryMapper implements RowMapper<Category> {

    @Override
    public Category mapRow(ResultSet rs, int i) throws SQLException {
        Category category = new Category();
        category.setId(rs.getInt("categoryId"));
        category.setName(rs.getString("categoryName"));
        return category;
    }

}
