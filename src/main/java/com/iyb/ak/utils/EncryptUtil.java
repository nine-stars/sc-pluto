package com.iyb.ak.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;

public class EncryptUtil {

	static Cipher cipher;
	static final String KEY_ALGORITHM = "AES";
	/* 
	 *  
	 */
	static final String CIPHER_ALGORITHM_CBC = "AES/CBC/PKCS5Padding";
	
	/** 
     * 定义加密方式 
     * MAC算法可选以下多种算法 
     * <pre> 
     * HmacMD5 
     * HmacSHA1 
     * HmacSHA256 
     * HmacSHA384 
     * HmacSHA512 
     * </pre> 
     */  
    private final static String KEY_MAC = "HmacSHA256";  

	/**
	 * 字符串加密
	 * 
	 * @param content
	 *            需要加密的内容
	 * @param password
	 *            加密密码
	 * @return
	 */
	public static String encrypt(String content, String password) {
		if (content == null)
			return content;
		try {
			KeyGenerator kgen = KeyGenerator.getInstance(KEY_ALGORITHM); // KeyGenerator提供（对称）密钥生成器的功能。使用getInstance
																			// 类方法构造密钥生成器。
			//kgen.init(128, new SecureRandom(password.getBytes()));// 使用用户提供的随机源初始化此密钥生成器，使其具有确定的密钥大小。
			
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );
			secureRandom.setSeed(password.getBytes());
			kgen.init(128, secureRandom);

			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, KEY_ALGORITHM);// 使用SecretKeySpec类来根据一个字节数组构造一个
																				// SecretKey,，而无须通过一个（基于
																				// provider
																				// 的）SecretKeyFactory.
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);// 创建密码器
																		// //为创建
																		// Cipher
																		// 对象，应用程序调用
																		// Cipher
																		// 的
																		// getInstance
																		// 方法并将所请求转换
																		// 的名称传递给它。还可以指定提供者的名称（可选）。
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(getIV()));// 初始化
			byte[] result = cipher.doFinal(byteContent); // 按单部分操作加密或解密数据，或者结束一个多部分操作。数据将被加密或解密（具体取决于此
															// Cipher 的初始化方式）。
			return base64Encode(result); // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 字符串解密
	 * 
	 * @param content
	 *            待解密内容
	 * @param password
	 *            解密密钥
	 * @return
	 */
	public static String decrypt(String content, String password) {
		if (content == null || password == null)
			return content;
		try {
			KeyGenerator kgen = KeyGenerator.getInstance(KEY_ALGORITHM);
			//kgen.init(128, new SecureRandom(password.getBytes()));
			
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );
			secureRandom.setSeed(password.getBytes());
			kgen.init(128, secureRandom);
			
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, KEY_ALGORITHM);
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(getIV()));// 初始化
			byte[] encrypt = base64Decode(content);
			byte[] result = cipher.doFinal(encrypt);
			return new String(result, "utf-8"); // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static byte[] getIV() {
		String iv = "kel0-Pw#F.aH8+2z"; // must be 16 byte
		return iv.getBytes();
	}

	/**
	 * Base64 编码
	 * 
	 * @param content
	 * @return
	 */
	public static String base64Encode(byte[] content) {
		byte[] result = Base64.encodeBase64(content);
		return new String(result);
	}

	/**
	 * Base64解码
	 * 
	 * @param content
	 * @return
	 */
	public static byte[] base64Decode(String content) {
		return Base64.decodeBase64(content);
	}

	/**
	 * byte数组转字符串
	 * @param content
	 * @return
	 */
	public static String hexString(byte[] content) {
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < content.length; i++) {
			int val = (int) content[i] & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

	public static String SHA256(String content) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(content.getBytes("utf-8"));
		byte[] result = md.digest();
		return hexString(result);
	}
	
	/** 
     * 初始化HMAC密钥 
     * @return 
     */  
    public static String createHMACKey() {  
        SecretKey key;  
        String str = "";  
        try {  
            KeyGenerator generator = KeyGenerator.getInstance(KEY_MAC);  
            key = generator.generateKey();  
            str = base64Encode(key.getEncoded());  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return str;  
    }  
	
	/** 
     * HMAC加密 
     * @param data 需要加密的字节数组 
     * @param key 密钥, Base64编码
     * @return 字节数组 
     */  
    public static byte[] encryptHMAC(byte[] data, String key) {  
        SecretKey secretKey;  
        byte[] bytes = null;  
        try {  
            secretKey = new SecretKeySpec(base64Decode(key), KEY_MAC);  
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());  
            mac.init(secretKey);  
            bytes = mac.doFinal(data);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return bytes;  
    }  
  
    /** 
     * HMAC加密 
     * @param data 需要加密的字符串 
     * @param key 密钥 
     * @return 字符串 
     */  
    public static String encryptHMAC(String data, String key) {  
        if (data == null) {  
            return null;  
        }  
        byte[] bytes = encryptHMAC(data.getBytes(), key);  
        return hexString(bytes);  
    }  
}
