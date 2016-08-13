package com.yeniyoo.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.joda.time.DateTime;

import java.io.Serializable;

/**
 * Created by YJLaptop on 2016-08-13.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class BackgroundImage implements Serializable {

    String image;
    DateTime createDate;
    Boolean isActive;

    public Boolean getIsActive() {
        return isActive;
    }

    public DateTime getCreateDate() {
        return createDate;
    }

    public String getImage() {
        return image;
    }

    public void setCreateDate(DateTime createDate) {
        this.createDate = createDate;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}
