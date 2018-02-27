package com.api.json;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.security.*;

import org.apache.commons.codec.binary.Base64;
//import com.codyy.coco.exception.TokenValidException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringUtils;
import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class SecurityUtils {

	
	//private static Logger logger = Logger.getLogger(SecurityUtils.class);

    // md5
    public static String MD5(String source,boolean type) throws UnsupportedEncodingException {
        byte src[] = source.getBytes("utf-8");
        byte target[] = MD5(src);

        int i;
        StringBuffer buf = new StringBuffer("");
        for (int offset = 0; offset < target.length; offset++) {
            i = target[offset];
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
    }

    public static byte[] MD5(byte source[]) {
        try {
            MessageDigest mdInstance = MessageDigest.getInstance("MD5");
            mdInstance.update(source);
            return mdInstance.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // AES
    public static SecretKey AESKeyGenerator(String aesKey) {
        try {
            KeyGenerator _generator = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(aesKey.getBytes());
            _generator.init(128, secureRandom);
            return _generator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            //logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }

    }

    public static String encryptAES(Key key, byte[] data) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getEncoded(), "AES"));
            byte[] cipherByte = cipher.doFinal(data);
            return base64Encode(cipherByte);
        } catch (GeneralSecurityException e) {
            //logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public static String decryptAES(Key key, String data) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getEncoded(), "AES"));
            byte[] bytes = cipher.doFinal(base64Decode(data));
            return new String(bytes, "utf-8");
        } catch (GeneralSecurityException e) {
            //logger.error(e.getMessage(), e);
            //throw new TokenValidException("瑙ｅ瘑澶辫触");
        	return "瑙ｅ瘑澶辫触";
        } catch (Exception e) {
            //logger.error(e.getMessage(), e);
            //throw new TokenValidException("瑙ｅ瘑澶辫触");
        	return "瑙ｅ瘑澶辫触";

        }
    }

    /**
     * base 64 encode
     *
     * @param bytes 寰呯紪鐮佺殑byte[]
     * @return 缂栫爜鍚庣殑base 64 code
     */
    public static String base64Encode(byte[] bytes) {
        return new BASE64Encoder().encode(bytes);
    }

    /**
     * base 64 decode
     *
     * @param base64Code 寰呰В鐮佺殑base 64 code
     * @return 瑙ｇ爜鍚庣殑byte[]
     * @throws Exception
     */
    public static byte[] base64Decode(String base64Code) throws Exception {
        return StringUtils.isEmpty(base64Code) ? null : new BASE64Decoder().decodeBuffer(base64Code);
        
    }
	
    
    public static String encodeBase64File(String path) throws Exception{
    	File file = new File(path);
    	FileInputStream in = new FileInputStream(file);
    	byte[] buffer = new byte[(int) file.length()];
    	in.read(buffer);
    	in.close();
    	String data = Base64.encodeBase64String(buffer);
    	
    	return data;
    }
	
	public static void main(String[] args) {
		
	/*	String str="userid-005:鑳″瓟鏉�:all:true:desc005:aes-11-22-33";
		try {
			System.out.println("32bit="+SecurityUtils.MD5(str,true));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		String data = "";
		try {
			data = encodeBase64File("D:\\test\\videodata\\H264_AAC.mp4");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(data);
	}

}
