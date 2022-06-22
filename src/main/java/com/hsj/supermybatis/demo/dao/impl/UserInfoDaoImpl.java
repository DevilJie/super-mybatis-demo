package com.hsj.supermybatis.demo.dao.impl;

import com.cjxch.supermybatis.core.dao.impl.BaseDaoImpl;
import com.hsj.supermybatis.demo.dao.IUserInfoDao;
import com.hsj.supermybatis.demo.entity.UserInfo;
import org.springframework.stereotype.Repository;


@Repository
public class UserInfoDaoImpl extends BaseDaoImpl<UserInfo> implements IUserInfoDao {
}
