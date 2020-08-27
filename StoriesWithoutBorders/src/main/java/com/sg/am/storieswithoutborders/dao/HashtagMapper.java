/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.am.storieswithoutborders.dao;

import com.sg.am.storieswithoutborders.model.Category;
import com.sg.am.storieswithoutborders.model.Hashtag;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author afsanamiji
 */
public class HashtagMapper implements RowMapper<Hashtag>{

    @Override
    public Hashtag mapRow(ResultSet rs, int i) throws SQLException {
        Hashtag hashtag = new Hashtag();
        hashtag.setId(rs.getInt("hashtagId"));
        hashtag.setName(rs.getString("hashtagName"));
        return hashtag;
     }
    
}
