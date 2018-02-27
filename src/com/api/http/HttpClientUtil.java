package com.api.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;

import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.api.json.JsonUtil;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class HttpClientUtil {
	
	public static String post(Map<String, Object> sParaTemp, String url) throws Exception{
        HttpClient httpClient = new HttpClient();

        PostMethod post = new PostMethod(url);
        post.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");

        List<String> keys = new ArrayList<String>(sParaTemp.keySet());
        NameValuePair[] param = new NameValuePair[keys.size()+1];
        for (int i = 0; i < keys.size(); i++) {
            String name = keys.get(i);
            Object object = sParaTemp.get(name);
            String value = "";
            if (object != null) {
                value = (String) sParaTemp.get(name);
            }
          //添加参数
            param[i] = new NameValuePair(name, value);
            post.setParameter(param[i].getName(),param[i].getValue());
            //System.out.println(param[i].getName());
        }
        HttpMethod method = post;
        httpClient.executeMethod(method);
        String response = method.getResponseBodyAsString();
        post.releaseConnection();
        return response;
    }
	
	public static JsonObject doPost(String url,JsonObject json){
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		JsonObject response = null;
		try {
			StringEntity s = new StringEntity(json.toString(),"utf-8");
			s.setContentEncoding("UTF-8");
			//发送json数据需要设置contentType
			s.setContentType("application/json");
			post.setEntity(s);
			HttpResponse res = client.execute(post);
			if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				HttpEntity entity = res.getEntity();
				String result = EntityUtils.toString(res.getEntity());// 杩斿洖json鏍煎紡锛�
				response = new JsonParser().parse(result).getAsJsonObject();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			client.close();
		}
		return response;
	}
	
	public static JsonObject doGet(String url,String token){
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		get.setHeader("token", token);
		JsonObject response = null;
		try {
			HttpResponse res = client.execute(get);
			if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				HttpEntity entity = res.getEntity();
				String result = EntityUtils.toString(res.getEntity());// 返回json格式：
				response = new JsonParser().parse(result).getAsJsonObject();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			client.close();
		}
		
		return response;
	}
	
	public static String doGetToString(String url,String token){
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		get.setHeader("token", token);
		String response = null;
		try {
			HttpResponse res = client.execute(get);
			if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				HttpEntity entity = res.getEntity();
				String result = EntityUtils.toString(res.getEntity());
				response = result;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			response = e.getMessage();
		} catch (IOException e) {
			e.printStackTrace();
			response = e.getMessage();
		}
		
		return response;
	}
	
	
	public static void main(String[] args) {
		String json = JsonUtil.createLoginData("userid-005", "username-005", "all", true, "desc005", "843c5b12ff50f5c354fbbf4974198803");
		
		String resp = doGetToString("http://10.5.31.92:1991/apply-server?groupId=!@#$%^&*","h/uWrAGg54/T3BZ1BY2HyNWsWdDoLpFMdRzyQJE8gF8=");
		System.out.println(resp);
	}

}
