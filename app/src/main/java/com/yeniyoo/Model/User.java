package com.yeniyoo.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by Mathpresso2 on 2015-07-22.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable {

    public Integer age;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
