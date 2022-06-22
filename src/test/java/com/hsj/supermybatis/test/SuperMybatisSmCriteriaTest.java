package com.hsj.supermybatis.test;

import com.alibaba.fastjson2.JSON;
import com.cjxch.supermybatis.core.tools.query.CriteriaConnector;
import com.cjxch.supermybatis.core.tools.query.SmCriteria;
import com.cjxch.supermybatis.core.tools.query.SmCriterion;
import com.cjxch.supermybatis.core.tools.query.SmCriterionArrays;
import com.hsj.supermybatis.demo.SuperMybatisDemoApplication;
import com.hsj.supermybatis.demo.dao.IUserInfoDao;
import com.hsj.supermybatis.demo.entity.UserInfo;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.List;


public class SuperMybatisSmCriteriaTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 这里为了方便测试，我们直接拿到Dao的Bean
     */
    private IUserInfoDao userInfoDao;

    /**
     * Junit运行之前，先将Spring容器进行加载
     * 同时将UserInfoService进行注入
     */
    @Before
    public void init(){
        ConfigurableApplicationContext run = SpringApplication.run(SuperMybatisDemoApplication.class, new String[]{});
        userInfoDao = run.getBean(IUserInfoDao.class);

    }

    String[] names = new String[]{"刘德华", "古天乐", "周润发", "周星驰", "张家辉", "基努·里维斯", "汤姆·克鲁斯"};

    /**
     * 首先我们先初始化一些数据
     */
    @Test
    public void initData(){
        List<UserInfo> ulist = new ArrayList<>();
        for(int i = 0 ; i < 10; i++){
            ulist.add(new UserInfo(names[(int)(Math.random()*names.length)] + i, (int)(Math.random()*100), "314170122@qq.com"));
        }
        userInfoDao.batchInsert(ulist);
    }

    @Test
    public void selectTestEq(){
        SmCriteria smCriteria = new SmCriteria.SmCriteriaBuild()
                .add(SmCriterion.eq("realName", "小菜鸡0"))
                .build();

        /**
         * 这条查询语句查询出所有realName = "小菜鸡0"的数据
         */

        List<UserInfo> userInfoList = userInfoDao.getList(smCriteria);
        logger.info("查询结果：{}", JSON.toJSONString(userInfoList));
    }



    @Test
    public void selectTestLike(){
        SmCriteria smCriteria = new SmCriteria.SmCriteriaBuild()
                .add(SmCriterion.like("realName", "刘德华%"))
                .build();
        List<UserInfo> userInfoList = userInfoDao.getList(smCriteria);
        logger.info("查询结果：{}", JSON.toJSONString(userInfoList));
    }


    @Test
    public void selectTestGe(){
        SmCriteria smCriteria = new SmCriteria.SmCriteriaBuild()
                .add(SmCriterion.ge("age", 80))
                .build();
        List<UserInfo> userInfoList = userInfoDao.getList(smCriteria);
        logger.info("查询结果：{}", JSON.toJSONString(userInfoList));
    }

    @Test
    public void selectTestLe(){
        SmCriteria smCriteria = new SmCriteria.SmCriteriaBuild()
                .add(SmCriterion.le("age", 18))
                .build();
        List<UserInfo> userInfoList = userInfoDao.getList(smCriteria);
        logger.info("查询结果：{}", JSON.toJSONString(userInfoList));
    }

    @Test
    public void selectTest01(){
        SmCriteria smCriteria = new SmCriteria.SmCriteriaBuild()
                .add(SmCriterion.like("realName", "小菜鸡%"))
                .add(SmCriterion.ge("age", 12))
                .add(SmCriterion.le("age", 18))
                .build();
        List<UserInfo> userInfoList = userInfoDao.getList(smCriteria);
        logger.info("查询结果：{}", JSON.toJSONString(userInfoList));
    }


    @Test
    public void selectTest02(){
        SmCriteria smCriteria = new SmCriteria.SmCriteriaBuild()
                .add(SmCriterion.like("realName", "刘德华%"))
                .add(SmCriterion.gt("age", 18))
                .add(SmCriterion.lt("age", 12, CriteriaConnector.OR))
                .build();
        List<UserInfo> userInfoList = userInfoDao.getList(smCriteria);
        logger.info("查询结果：{}", JSON.toJSONString(userInfoList));
    }


    @Test
    public void selectTest03(){

        /**
         * 定义一个年龄段的查询条件集合
         */
        SmCriterionArrays ageRange = new SmCriterionArrays.SmCriterionArraysBuild()
                .gt("age", 18)
                .lt("age", 12, CriteriaConnector.OR)
                /**设置拼接这一段查询组的方式为 OR */
                .build(CriteriaConnector.AND);

        SmCriteria smCriteria = new SmCriteria.SmCriteriaBuild()
                .add(SmCriterion.like("realName", "刘德华%"))
                .add(ageRange)
                .build();
        List<UserInfo> userInfoList = userInfoDao.getList(smCriteria);
        logger.info("查询结果：{}", JSON.toJSONString(userInfoList));
    }
}
