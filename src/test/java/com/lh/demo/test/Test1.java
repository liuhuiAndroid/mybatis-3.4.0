package com.lh.demo.test;

import com.lh.demo.pojo.User;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.log4j.Log4jImpl;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;

/**
 * Created by lh on 2018/4/25.
 */
public class Test1 {

    @Test
    public void testMybatis() throws Exception {
        //加载核心配置文件
        String resource = "SqlMapConfig.xml";
        InputStream in = Resources.getResourceAsStream(resource);
        //创建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        //创建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行Sql语句
        User user = sqlSession.selectOne("test.findUserById", 10);
        System.out.println(user);
    }

    @Test
    public void testLog() throws Exception {
       Log log = new Log4jImpl(Test1.this.getClass().getSimpleName());
        log.error("testLog");
    }
}
