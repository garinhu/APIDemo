package com.api.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequest {
	
	public String ADD_URL = "localhost";
	
	public HttpRequest(String url) {
		
		this.ADD_URL = url;
		
	}
	public  String post(Object obj){
		
		String responseData = null;
		try {
			//创建连接
	        URL url = new URL(ADD_URL);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setDoOutput(true);
	        connection.setDoInput(true);
	        connection.setRequestMethod("POST");
	        connection.setUseCaches(false);
	        connection.setInstanceFollowRedirects(true);
	        connection.setRequestProperty("Content-Type", "application/json");

	        connection.connect();

	      //POST请求
	        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
//	        JSONObject obj = new JSONObject();
//	        obj.element("app_name", "asdf");
//	        obj.element("app_ip", "10.21.243.234");
//	        obj.element("app_port", 8080);
//	        obj.element("app_type", "001");
//	        obj.element("app_area", "asd");

	        out.writeBytes(obj.toString());
	        out.flush();
	        out.close();

	      //读取响应
	        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	        String lines;
	        StringBuffer sb = new StringBuffer("");
	        while ((lines = reader.readLine()) != null) {
	            lines = new String(lines.getBytes(), "utf-8");
	            sb.append(lines);
	        }
	        System.out.println(sb);
	        responseData = sb.toString();
	        reader.close();
	     // 断开连接
	        connection.disconnect();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
		return responseData;
	}
	
	
}
