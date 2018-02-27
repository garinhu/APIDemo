package com.api.json;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

	/**@param
	 * 
	 * @param type  鏄惁鏄�32浣峬d5鍔犲瘑浣嶆暟
	 * @param plainText  MD5琚姞瀵嗙殑瀛楃涓�
	 * @return
	 */
	public static String getMd5(Boolean type,String plainText) { 
		
        try {  
            MessageDigest md = MessageDigest.getInstance("MD5");  
            md.update(plainText.getBytes("utf-8"));  
            byte b[] = md.digest();  
            int i;  
            StringBuffer buf = new StringBuffer("");  
            for (int offset = 0; offset < b.length; offset++) {  
                i = b[offset];  
                if (i < 0)  
                    i += 256;  
                if (i < 16)  
                    buf.append("0");  
                buf.append(Integer.toHexString(i));  
            }
            if(type){
            	//32浣嶅姞瀵�  
            	return buf.toString();  
            }else{
            	// 16浣嶇殑鍔犲瘑  
            	return buf.toString().substring(8, 24);  
            }
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
            return null;  
        } catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null; 
		}  
  
    }  
	
	public static void main(String[] args) {
		String str="userid-001:鑳″瓟绀�:user:false:desc-001";
		System.out.println("32bit="+MD5Util.getMd5(true,str));
		System.out.println("16bit="+MD5Util.getMd5(false,str));
		
	}

}
