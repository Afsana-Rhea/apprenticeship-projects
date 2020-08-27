/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.am.storieswithoutborders.dao;

import com.sg.am.storieswithoutborders.model.Hashtag;
import com.sg.am.storieswithoutborders.model.Post;
import java.util.List;

/**
 *
 * @author afsanamiji
 */
public interface HashtagDao {

    public void addHashtag(Hashtag hashtag);

    public void deleteHashtag(int hashtagId);

    public void updateHashtag(Hashtag hashTag);

    public List<Hashtag> getAllHashtags();

    public Hashtag getHashtagById(int id);
    
    public Hashtag getHashtagByName (String name);

}
