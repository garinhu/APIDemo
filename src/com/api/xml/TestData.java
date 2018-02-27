package com.api.xml;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

public class TestData {

	private List<?> list;
	   
	  public TestData() {  
	    this.getXmlData();    
	  }
	   
	  public void getXmlData(){
	    ParserXml p = new ParserXml();
	    list = p.parserXml(new File("TestData/xmldata.xml").getAbsolutePath());
	  }
	 
	  @SuppressWarnings("unchecked")
	@DataProvider
	  public Object[][] providerMethod(Method method){    
	    List<Map<String, String>> result = new ArrayList<Map<String, String>>();    
	    for (int i = 0; i < list.size(); i++) {
	      Map<?, ?> m = (Map<?, ?>) list.get(i);  
	      if(m.containsKey(method.getName())){              
	        Map<String, String> map =  (Map<String, String>) m.get(method.getName());
			Map<String, String> dm = map;
	        result.add(dm);  
	      }
	    } 
	    Object[][] files = new Object[result.size()][];
	    for(int i=0; i<result.size(); i++){
	      files[i] = new Object[]{result.get(i)};
	    }    
	    return files;
	  }
	
	
}
