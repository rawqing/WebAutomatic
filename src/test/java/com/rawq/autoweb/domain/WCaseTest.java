package com.rawq.autoweb.domain;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.rawq.autoweb.service.IWCase;
import com.rawq.autoweb.service.IWElement;
import com.rawq.autoweb.service.imp.WCaseOper;
import com.rawq.autoweb.utils.MybatisUtil;

public class WCaseTest {
	private static SqlSessionFactory sqlSessionFactory;
	private String resource = "config.xml";
	SqlSession session;
	
  //@BeforeTest
  public void beforeTest() {
//	  InputStream is = WCaseTest.class.getClassLoader().getResourceAsStream(resource);
//	  System.out.println(is);
//	  sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
	  
	//  sqlSessionFactory = MybatisUtil.getSqlSessionFactory();
  }


//  @Test
  public void toStringtest() {
      //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
     
      
      //构建sqlSession的工厂
     // SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
      //使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
      //Reader reader = Resources.getResourceAsReader(resource); 
      //构建sqlSession的工厂
      //SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
      //创建能执行映射文件中sql的sqlSession
      session = sqlSessionFactory.openSession();
      /**
       * 映射sql的标识字符串，
       * com.rawq.casemanager.mapping.hTestCase是hTestCase.xml文件中mapper标签的namespace属性的值，
       * getTestCaseByID是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
       */
      String statement = "com.rawq.autoweb.mapper.wCase.getWCaseByID";//映射sql的标识字符串
      //执行查询返回一个唯一HTestCase对象的sql
      WCase testcase = session.selectOne(statement, 2);	//(statement, 2) 这里的2 是caseId = #{caseId} 需要传入的参数，即caseId = 2 的意思
      System.out.println(testcase.toString());
  }
  
 // @Test
  public void testgetWCaseById(){
	 /* session = sqlSessionFactory.openSession();
	  System.out.println(session);
	  IWCase iht = session.getMapper(IWCase.class);
	  WCase testcase = iht.getWCaseById(1);	
	  System.out.println("**1** "+testcase);
	  System.out.println(testcase.getWSteeps().get(0).toString());
	  System.out.println(testcase.getWSteeps().get(1).toString());*/
	  
	  IWCase icase = new WCaseOper();
	  System.out.println(icase.getWCaseById(1));
	  
	  IWCase icase1 = new WCaseOper();
	  System.out.println(icase1.getWCaseByName("打开首页"));
  }
  
  
 // @Test
  public void testgetWCaseByName(){
	  session = sqlSessionFactory.openSession();
	  System.out.println(session);
	  IWCase iht = session.getMapper(IWCase.class);
	  List<WCase> testcase = iht.getWCaseByName("登录系统");	
	  System.out.println(testcase);
  }
  
// @Test
  public void testaddWCase(){
	  session = MybatisUtil.getSqlSession();
	  System.out.println(session);
	  IWCase iht = session.getMapper(IWCase.class);
	  
	  WCase testcase = new WCase();
	  testcase.setCaseName("输入用户名");
	  testcase.setPriority(1);
	  testcase.setVersion(1);
	  testcase.setCaseNumber("TE001");
	  
	  
	  WCase testcase1 = new WCase();
	  testcase1.setCaseName("输入用户名1");
	  testcase1.setPriority(2);
	  testcase1.setVersion(2);
	  testcase.setCaseNumber("TE001");
	  
	  System.out.println("before add testcast id :"+ testcase.getId());
	  int res = iht.addWCase(testcase);	
	  System.out.println("after add testcast id"+ testcase.getId());
	  System.out.println("before add testcast1 id"+ testcase.getId());
	//  int res1 = iht.addWCase(testcase1);	
	  System.out.println("after add testcast id"+ testcase.getId());

	  session.commit();
	  System.out.println(res);
	//  System.out.println(res1);
	  System.out.println("after add testcast id"+ testcase.getId());
  }
  
 // @Test
  public void testgetWElement(){
	  session = sqlSessionFactory.openSession();
	  System.out.println(session);
	  IWElement iht = session.getMapper(IWElement.class);
	  WElement telem = iht.getWElementById(1);	
	  System.out.println("**1** "+telem);
	  
	  int id = iht.getWElementIdByName("首页","用户名输入框");
	  System.out.println(id);
	 
  }
  //@AfterClass
  public void closeSession(){
	  session.close();
	  Assert.assertEquals(1, 2);
  }
}
