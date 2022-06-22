package com.hsj.supermybatis.demo.service.impl;

import com.hsj.supermybatis.demo.dao.IUserInfoDao;
import com.hsj.supermybatis.demo.entity.UserInfo;
import com.hsj.supermybatis.demo.service.IUserInfoService;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class UserInfoServiceImpl implements IUserInfoService {

    private final IUserInfoDao userInfoDao;

    public UserInfoServiceImpl(IUserInfoDao userInfoDao){
        this.userInfoDao = userInfoDao;
    }

    @Override
    public UserInfo insert(UserInfo userInfo) {
        Serializable id = userInfoDao.insert(userInfo);
        userInfo.setId(id.toString());
        return userInfo;
    }

    @Override
    public Long remove(String id) {
        return userInfoDao.delete(id);
    }

    @Override
    public Long update(UserInfo userInfo) {
        return userInfoDao.update(userInfo);
    }

    @Override
    public UserInfo getById(String id) {
        return userInfoDao.get(id);
    }

    @Override
    public List<UserInfo> loadAll() {
        return userInfoDao.allList();
    }
}
