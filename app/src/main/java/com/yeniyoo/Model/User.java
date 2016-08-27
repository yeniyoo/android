package com.yeniyoo.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by Mathpresso2 on 2015-07-22.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable {

    public String fbId;
    public Boolean gender;
    public Integer age;

    public Boolean getGender() {
        return gender;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    public String getFbId() {
        return fbId;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
