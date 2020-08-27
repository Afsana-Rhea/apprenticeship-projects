/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.am.storieswithoutborders.dao;

import com.sg.am.storieswithoutborders.model.Post;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


/**
 *
 * @author afsanamiji
 */
@Repository 
public class PostMapper implements RowMapper<Post>{

    @Override
    public Post mapRow(ResultSet rs, int i) throws SQLException {
        Post post = new Post();
        post.setId(rs.getInt("postId"));
        post.setTitle(rs.getString("postTitle"));
        post.setDescription(rs.getString("postDescription"));
        post.setIsPublished(rs.getBoolean("isPublished"));
        post.setIsStatic(rs.getBoolean("isStatic"));
        return post;

    }

}
