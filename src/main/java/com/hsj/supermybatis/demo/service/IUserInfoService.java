package com.hsj.supermybatis.demo.service;

import com.hsj.supermybatis.demo.entity.UserInfo;

import java.util.List;

public interface IUserInfoService {

    /**
     * 新增用户信息
     * @param userInfo
     * @return
     */
    public UserInfo insert(UserInfo userInfo);

    /**
     * 根据主键id删除用户信息
     * @return
     */
    public Long remove(String id);

    /**
     * 更新用户信息
     * @param userInfo
     * @return
     */
    public Long update(UserInfo userInfo);


    /**
     * 根据用户id获取用户
     * @param id
     * @return
     */
    public UserInfo getById(String id);

    /**
     * 获取所有用户列表
     * @return
     */
    public List<UserInfo> loadAll();
}
