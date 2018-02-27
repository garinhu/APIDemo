package com.api.test;

import java.util.Random;

import org.testng.ITestNGMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderTest2 {

	@DataProvider
    public Object[][] providerMethod(ITestNGMethod m){
        String[] groups = m.getGroups();
        int size = 2;
        for(String group : groups){
            if(group.equals("function-test")){
                size = 10 ;
                break;
            }
        }
        Object[][] result = new Object[size][];
        Random r = new Random();
        for(int i=0;i<size;i++){
            result[i] = new Object[]{new Integer(r.nextInt())};
        }
        return result;
    }
    
    @Test(dataProvider="providerMethod",groups={"function-test","unit-test"})
    public void testmethod1(Integer n){
        System.out.println("method1 received:"+n);
    }
	
}
