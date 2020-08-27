/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.am.storieswithoutborders.model;

import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author afsanamiji
 */
public class Post {
    
    int id;
    
    @NotBlank(message = "Title must not be empty.")
    @Size(max = 45, message = "Title must be less than 45 characters.")
    String title;
    
    @NotBlank(message = "Post must not be empty.")
    @Size(max = 5000, message = "Post must be less than 5000 characters.")
    String description;
    boolean isPublished;
    boolean isStatic;
    
//    @NotBlank(message = "Name must not be empty.")
//    @Size(max = 128, message = "Name must be less than 128 characters.")
    String hashtag;
    
    List<Hashtag> hashtags;
    Category category;

    public int getId() {
        return id;
    }

    public void setId(int postId) {
        this.id = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIsPublished() {
        return isPublished;
    }

    public void setIsPublished(boolean isPublished) {
        this.isPublished = isPublished;
    }

    public boolean isIsStatic() {
        return isStatic;
    }

    public void setIsStatic(boolean isStatic) {
        this.isStatic = isStatic;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public List<Hashtag> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<Hashtag> hashtags) {
        this.hashtags = hashtags;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id;
        hash = 89 * hash + Objects.hashCode(this.title);
        hash = 89 * hash + Objects.hashCode(this.description);
        hash = 89 * hash + (this.isPublished ? 1 : 0);
        hash = 89 * hash + (this.isStatic ? 1 : 0);
        hash = 89 * hash + Objects.hashCode(this.hashtag);
        hash = 89 * hash + Objects.hashCode(this.hashtags);
        hash = 89 * hash + Objects.hashCode(this.category);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Post other = (Post) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.isPublished != other.isPublished) {
            return false;
        }
        if (this.isStatic != other.isStatic) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.hashtag, other.hashtag)) {
            return false;
        }
        if (!Objects.equals(this.hashtags, other.hashtags)) {
            return false;
        }
        if (!Objects.equals(this.category, other.category)) {
            return false;
        }
        return true;
    }

    
}
