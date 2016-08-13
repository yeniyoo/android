package com.yeniyoo.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.joda.time.DateTime;

import java.io.Serializable;

/**
 * Created by YJLaptop on 2016-08-13.
 */


@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment implements Serializable {
    Integer id;
    String nickname;
    String content;
    Integer like;
    Integer isLiked;
    DateTime createDate;

    public DateTime getCreateDate() {
        return createDate;
    }

    public Integer getId() {
        return id;
    }

    public Integer getIsLiked() {
        return isLiked;
    }

    public Integer getLike() {
        return like;
    }

    public String getContent() {
        return content;
    }

    public String getNickname() {
        return nickname;
    }

    public void setCreateDate(DateTime createDate) {
        this.createDate = createDate;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIsLiked(Integer isLiked) {
        this.isLiked = isLiked;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
