package com.rawq.autoweb.utils;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtil {

	private static String resource = "mybatisconfig.xml";
	private static SqlSessionFactory factory;
	
	 /**
      * 获取SqlSessionFactory
      * @return SqlSessionFactory
      */
    static {
         InputStream is = MybatisUtil.class.getClassLoader().getResourceAsStream(resource);
         try {
			factory = new SqlSessionFactoryBuilder().build(is);
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(MybatisUtil.class,"获取SqlSessionFactory失败： "+e);
		}
         Log.info(MybatisUtil.class,"获取到 SqlSessionFactory ："+factory);
     }
     
     /**
      * 获取SqlSession
      * @return SqlSession
      */
     public static SqlSession getSqlSession() {
         return factory.openSession();
     }
     
     /**
      * 获取SqlSession
      * @param isAutoCommit 
      *         true 表示创建的SqlSession对象在执行完SQL之后会自动提交事务
      *         false 表示创建的SqlSession对象在执行完SQL之后不会自动提交事务，这时就需要我们手动调用sqlSession.commit()提交事务
      * @return SqlSession
      */
     public static SqlSession getSqlSession(boolean isAutoCommit) {
         return factory.openSession(isAutoCommit);
     }
}