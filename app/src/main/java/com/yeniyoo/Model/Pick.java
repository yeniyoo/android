package com.yeniyoo.Model;

import android.support.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.joda.time.DateTime;

import java.io.Serializable;

/**
 * Created by YJLaptop on 2016-08-13.
 */


@JsonIgnoreProperties(ignoreUnknown = true)
public class Pick implements Serializable {
    @Nullable
    Boolean yesNo;

    DateTime CreateDate;
    DateTime modifiedDate;
    Boolean isActive;
    User user;
    Round round;
}
