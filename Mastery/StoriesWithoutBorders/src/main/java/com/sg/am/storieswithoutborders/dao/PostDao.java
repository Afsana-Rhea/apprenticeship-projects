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
public interface PostDao {

    public void addPost(Post post);

    public void deletePost(int postId);

    public void updatePost(Post post);

    public List<Post> getAllPosts();

    public Post getPostByID(int Id);

}
