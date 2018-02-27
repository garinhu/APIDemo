package com.api.json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class JsonUtil {

	public static JsonObject stringToJsonObj(String jsonstr) {

		JsonObject returnData = new JsonParser().parse(jsonstr).getAsJsonObject();

		return returnData;
	}
	
	public static String getJsonDataByKey(String obj,String jsonKey){
		
		JsonObject jsonObj = new JsonParser().parse(obj).getAsJsonObject();
		return jsonObj.get(jsonKey).toString();
	}

	public static String parseJsonObj(JsonObject obj, String type, String subtype) {
		JsonObject jsonObj = obj;
		String str = jsonObj.getAsJsonObject(type).get(subtype).toString();
		
		return str;
	}
	
	
	public static List<String> getVauleFromArrayObj(String jsonStr,String jsonObj,String parentKey,String subKey){
		List<String> userList = new ArrayList<String>();
		JsonObject obj = stringToJsonObj(jsonStr);
		System.out.println(obj.isJsonObject());
		JsonObject subobj = obj.getAsJsonObject(jsonObj);
		JsonArray array = subobj.getAsJsonArray(parentKey);
		System.out.println(array.isJsonArray());
		Iterator<JsonElement> it = array.iterator();
		while(it.hasNext()){
			JsonElement e = (JsonElement) it.next();
			JsonObject subObj = e.getAsJsonObject();
			String value = subObj.get(subKey).toString();
			userList.add(value);
		}
		
		return userList;
	}

	/**
	 * write Login json data
	 */
	
	public static String jsonWriteForLogin(){
		String md5Key = "aes-11-22-33";
		String separator = ":";
		ResultData result = new ResultData();
		result.setResult(1);
		List<UserLoginVo> listUser = new ArrayList<UserLoginVo>();
		UserLoginVo userLogin = new UserLoginVo();
		userLogin.setUserId("123");
		userLogin.setUserName("123");
		userLogin.setUserType("123");
		userLogin.setAdmin("true");
		userLogin.setDesc("111");
		//userSignature=MD5(userId:userName:userType:admin:desc:MD5key)
		String sigStr = userLogin.getUserId()+separator+userLogin.getUserName()+separator+
		userLogin.getUserType()+separator+userLogin.getAdmin()+separator+userLogin.getDesc()+separator+md5Key;
		
		String userSignature = MD5Util.getMd5(true, sigStr);

		userLogin.setUserSignature(userSignature);
		listUser.add(userLogin);
		result.setUserLogin(listUser);
		
		Gson gson = new Gson();
		
		return gson.toJson(result);
	}
	
	public static void creatJson(){
		JsonObject object = new JsonObject();
		object.addProperty("command", "online");
		JsonObject subObj = new JsonObject();
		subObj.addProperty("userToken", "qwertrryyyfccc=");
		subObj.addProperty("clientType", "web");
		subObj.addProperty("deviceName", "iphone7s");
		object.add("body", subObj);
		
		System.out.println(object.toString());
	}
	
	public static String createLoginData(String userId,String userName,String userType,boolean isadmin,String desc,String sign){
		JsonObject object = new JsonObject();
		object.addProperty("userId", userId);
		object.addProperty("userName", userName);
		object.addProperty("userType", userType);
		object.addProperty("admin", isadmin);
		object.addProperty("desc", desc);
		object.addProperty("userSignature", sign);
		return object.toString();
	}
	
	
	public static String createLoginDataWithNoSign(String userId,String userName,String userType,boolean isadmin,String desc){
		JsonObject object = new JsonObject();
		object.addProperty("userId", userId);
		object.addProperty("userName", userName);
		object.addProperty("userType", userType);
		object.addProperty("admin", isadmin);
		object.addProperty("desc", desc);
		//object.addProperty("userSignature", sign);
		return object.toString();
	}
	
	
	public static String createOnlineData(String token){
		
		JsonObject object = new JsonObject();
		object.addProperty("command", "online");
		JsonObject subObj = new JsonObject();
		subObj.addProperty("userToken", token);
		subObj.addProperty("clientType", "web");
		subObj.addProperty("deviceName", "browser");
		object.add("body", subObj);
		
		return object.toString();
	}
	
	public static String createAddGroupData(String userid){
		
		JsonObject object = new JsonObject();
		object.addProperty("command", "addGroup");
		JsonObject subObj = new JsonObject();
		subObj.addProperty("groupId", "groupid-");
		subObj.addProperty("userId", userid);
		object.add("body", subObj);
		
		return object.toString();
	}
	
	public static String createWhitePadData(String tabId,String groupId,String clientId){
		
		JsonObject object = new JsonObject();
		object.addProperty("command", "whitePad");
		JsonObject subObj = new JsonObject();
		subObj.addProperty("tabId", tabId);
		subObj.addProperty("groupId", groupId);
		JsonArray data = new JsonArray();
		JsonObject dataObj = new JsonObject();
		dataObj.addProperty("clientId", clientId);
		dataObj.addProperty("sequence", 1);
		dataObj.addProperty("type", "line");
		JsonObject subdataObj = new JsonObject();
		subdataObj.addProperty("start", "0");
		subdataObj.addProperty("end", "1");
		dataObj.add("content",subdataObj);
		data.add(dataObj);
		
		JsonObject dataObj1 = new JsonObject();
		dataObj1.addProperty("clientId", clientId);
		dataObj1.addProperty("sequence", 1);
		dataObj1.addProperty("type", "line");
		JsonObject subdataObj1 = new JsonObject();
		subdataObj1.addProperty("start", "0");
		subdataObj1.addProperty("end", "1");
		dataObj1.add("content",subdataObj1);
		data.add(dataObj1);

		subObj.add("data", data);
		object.add("body", subObj);
		
		return object.toString();
	}
	
	public static String createKickUserData(String userid,String groupId){
		JsonObject obj = new JsonObject();
		obj.addProperty("command", "kickUser");
		JsonObject subObj = new JsonObject();
		subObj.addProperty("userId", userid);
		subObj.addProperty("groupId", groupId);
		obj.add("body", subObj);
		return obj.toString();
	}
	
	public static String createOnlineUsersData(String groupId){
		JsonObject obj = new JsonObject();
		obj.addProperty("command", "onlineUsers");
		JsonObject subObj = new JsonObject();
		subObj.addProperty("groupId", groupId);
		obj.add("body", subObj);
		return obj.toString();
	}
	
	public static String createSingleChatData(String sendUserId){
		JsonObject obj = new JsonObject();
		obj.addProperty("command", "singleChat");
		JsonObject subObj = new JsonObject();
		subObj.addProperty("sendUserId", sendUserId);
		subObj.addProperty("receivedUserId", "userid-");
		JsonObject subsubObj = new JsonObject();
		subsubObj.addProperty("type", "text");
		subsubObj.addProperty("content", "this is a content");
		subObj.add("message",subsubObj);
		obj.add("body", subObj);
		return obj.toString();
	}
	
	public static String createGroupChatData(String groupId){
		JsonObject obj = new JsonObject();
		obj.addProperty("command", "groupChat");
		JsonObject subObj = new JsonObject();
		subObj.addProperty("groupId", groupId);
		JsonObject subsubObj = new JsonObject();
		subsubObj.addProperty("type", "text");
		subsubObj.addProperty("content", "this is a content");
		subObj.add("message",subsubObj);
		obj.add("body", subObj);
		return obj.toString();
	}
	
	public static String createControlData(String userType,String clientId,String groupId,String tabId){
		JsonObject obj = new JsonObject();
		obj.addProperty("command", "control");
		JsonObject subObj = new JsonObject();
		subObj.addProperty("type", "whitePadAddTab");
		subObj.addProperty("userType", userType);
		
		JsonArray jsonArray = new JsonArray();
		JsonElement element = null;
		JsonParser parser = new JsonParser();
		element=parser.parse(clientId);
		jsonArray.add(element);
		
		subObj.add("clientIds", jsonArray);
		subObj.addProperty("groupId", groupId);
		JsonObject subsubObj = new JsonObject();
		subsubObj.addProperty("tabName", "鐧芥澘鍚嶇О");
		subsubObj.addProperty("groupId", groupId);
		subsubObj.addProperty("tabId", tabId);
		subObj.add("data",subsubObj);
		obj.add("body", subObj);
		return obj.toString();
	}
	
	public static String createGroupUserCountData(String groupId){
		JsonObject obj = new JsonObject();
		obj.addProperty("command", "groupUserCount");
		JsonObject subObj = new JsonObject();
		subObj.addProperty("groupId", groupId);
		obj.add("body", subObj);
		return obj.toString();
	}
	
	public static void main(String[] args) {
		
		//System.out.println(JsonUtil.jsonWriteForLogin());
		
		/*JsonParser parser = new JsonParser();
		try {
			JsonObject object = (JsonObject) parser.parse(new FileReader("test.json"));
			String cmd =object.get("command").getAsString();
			
			System.out.println("cmd="+cmd);
			JsonObject bodyObj = object.get("body").getAsJsonObject();
			String device = bodyObj.get("deviceName").getAsString();
			System.out.println("device="+device);

		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}*/
		//creatJson();
		String str = createLoginData("userid-005","鑳″瓟鏉�","all",true,"desc005","qwerrasdffgf");
		System.out.println(str);
		/*String str = "{\"body\":{\"userList\":[{\"userId\":\"userid-2\",\"userName\":\"username-2\",\"userType\":\"user\"},{\"userId\":\"userid-1\",\"userName\":\"username-1\",\"userType\":\"user\"},{\"userId\":\"userid-5\",\"userName\":\"username-5\",\"userType\":\"user\"}]},\"command\":\"onlineUsers\"}";
		List<String> list = new ArrayList<String>();
		userid-005:鑳″瓟鏉�:all:true:desc005:aes-11-22-33
		list = JsonUtil.getVauleFromArrayObj(str, "body","userList", "userId");
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}*/
		
		
	}

}
