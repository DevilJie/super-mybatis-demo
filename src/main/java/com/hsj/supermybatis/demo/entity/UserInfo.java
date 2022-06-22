package com.hsj.supermybatis.demo.entity;

import com.cjxch.supermybatis.base.annotation.PrimaryKey;
import com.cjxch.supermybatis.base.annotation.Table;

/**
 * @Author: 菜鸡小彩虹
 * @Date: 2020/10/01/21:32
 * @Email: cjxch@cjxch.com
 */
@Table
public class UserInfo {

    /**
     * 标记此字段为主键
     */
    @PrimaryKey
    private String id;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 邮箱地址
     */
    private String email;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", realName='" + realName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }

    public UserInfo(String realName, Integer age, String email) {
        this.realName = realName;
        this.age = age;
        this.email = email;
    }

    public UserInfo(){}
}

