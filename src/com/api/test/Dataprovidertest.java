package com.api.test;

import org.testng.annotations.Test;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

public class Dataprovidertest {
	
  @DataProvider
  public Object[][] providerMethod(Method method) {
   
	  Object[][] result = null;
	  if(method.getName().equals("testMethod1")){
		  result = new Object[][]{new Object[]{1}};
	  }else if(method.getName().equals("testMethod2")){
		  result = new Object[][]{new Object[]{2}};
	  }else{
		  result = new Object[][]{new Object[]{3}};
	  }
	  return result;
  }
  
  @Test(dataProvider="providerMethod")
  public void testMethod1(int param){
      System.out.println("method1 received:"+param);
  }
   
  @Test(dataProvider="providerMethod")
  public void testMethod2(int param){
      System.out.println("method2 received:"+param);
  }
   
  @Test(dataProvider="providerMethod")
  public void testMethod3(int param){
      System.out.println("method3 received:"+param);
  }
  
  
  
  
  
}
