package com.api.test;

import java.util.Map;

import org.testng.Reporter;
import org.testng.annotations.*;

import com.api.xml.TestData;

public class TestXmlDataProvider extends TestData{

	@Test(dataProvider="providerMethod")
	  public void testmethod1(Map<?, ?> param){
	    System.out.println("method1 received:"+param.get("input"));
	    Reporter.log("method1 received:"+param.get("button"));
	  }
	    
	  @Test(dataProvider="providerMethod")
	  public void testmethod2(Map<?, ?> param){
	    System.out.println("method2 received:"+param.get("input"));
	    Reporter.log("method2 received:"+param.get("button"));
	  }
	    
	  @Test(dataProvider="providerMethod")
	  public void testmethod3(Map<?, ?> param){
	    System.out.println("method3 received:"+param.get("input"));
	    Reporter.log("method3 received:"+param.get("button"));
	    Reporter.log("这是测试方法:"+param.get("button"));
	  }
	   
	  @Test
	  public void testmethod4(){
	    System.out.println("method4 received:4");
	  }
	
}
