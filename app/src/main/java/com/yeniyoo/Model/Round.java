package com.yeniyoo.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.joda.time.DateTime;

import java.io.Serializable;

/**
 * Created by YJLaptop on 2016-08-13.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Round implements Serializable {

    String question;
    Boolean complete;
    DateTime createDate;
    DateTime modifiedDate;
    DateTime completeDate;
    Boolean isActive;
    User user;
    BackgroundImage backgroundId;

    public DateTime getCreateDate() {
        return createDate;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public BackgroundImage getBackgroundId() {
        return backgroundId;
    }

    public Boolean getComplete() {
        return complete;
    }

    public DateTime getCompleteDate() {
        return completeDate;
    }

    public DateTime getModifiedDate() {
        return modifiedDate;
    }

    public String getQuestion() {
        return question;
    }

    public User getUser() {
        return user;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public void setCreateDate(DateTime createDate) {
        this.createDate = createDate;
    }

    public void setBackgroundId(BackgroundImage backgroundId) {
        this.backgroundId = backgroundId;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    public void setCompleteDate(DateTime completeDate) {
        this.completeDate = completeDate;
    }

    public void setModifiedDate(DateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setUser(User user) {
        this.user = user;
    }
}