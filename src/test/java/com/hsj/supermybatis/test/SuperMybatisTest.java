package com.hsj.supermybatis.test;

import com.alibaba.fastjson2.JSON;
import com.hsj.supermybatis.demo.SuperMybatisDemoApplication;
import com.hsj.supermybatis.demo.entity.UserInfo;
import com.hsj.supermybatis.demo.service.IUserInfoService;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;


public class SuperMybatisTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private IUserInfoService userInfoService;

    /**
     * Junit运行之前，先将Spring容器进行加载
     * 同时将UserInfoService进行注入
     */
    @Before
    public void init(){
        ConfigurableApplicationContext run = SpringApplication.run(SuperMybatisDemoApplication.class, new String[]{});
        userInfoService = run.getBean(IUserInfoService.class);

    }

    @Test
    public void startTest(){
        UserInfo userInfo = new UserInfo("小菜鸡", 18, "314170122@qq.com");
        userInfo = userInfoService.insert(userInfo);
        logger.info("数据插入成功：{}", JSON.toJSONString(userInfo));
        getById(userInfo.getId());
    }


    public void getById(String id){
        UserInfo userInfo = userInfoService.getById(id);
        logger.info("根据主键id查询结果：{}", JSON.toJSONString(userInfo));
        userInfo.setRealName("小菜鸡二号");
        update(userInfo);
    }

    public void update(UserInfo userInfo){
        Long ret = userInfoService.update(userInfo);
        logger.info("数据更新结果，更新记录：{}", ret);
        allList();
        delete(userInfo.getId());
    }

    public void allList(){
        logger.info("所有用户列表：{}", JSON.toJSONString(userInfoService.loadAll()));
    }

    public void delete(String id){
        logger.info("数据删除结果，删除记录：{}", userInfoService.remove(id));
        allList();
    }
}
