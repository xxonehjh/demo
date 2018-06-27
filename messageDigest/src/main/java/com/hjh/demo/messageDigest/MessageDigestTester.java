package com.hjh.demo.messageDigest;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 单向加密
 * 
 * @author xxonehjh
 */
public class MessageDigestTester {

	public static void main(String args[]) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException {
		System.out.println(md5("123456"));
		System.out.println(sha1("123456"));
		System.out.println(sha256("123456"));

		System.out.println(hmacSHA1Encrypt("123456", "pwd"));
		System.out.println(hmacSHA256Encrypt("123456", "pwd"));

		System.out.println(hmacSHA1Encrypt("123456", "pwd2"));
		System.out.println(hmacSHA256Encrypt("123456", "pwd2"));
	}

	public static String md5(String txt) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(txt.getBytes("utf-8"));
		return toString(md.digest());
	}

	public static String sha1(String txt) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		md.update(txt.getBytes("utf-8"));
		return toString(md.digest());
	}

	public static String sha256(String txt) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(txt.getBytes("utf-8"));
		return toString(md.digest());
	}

	public static String hmacSHA1Encrypt(String encryptText, String encryptKey)
			throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException {
		byte[] data = encryptKey.getBytes("utf-8");
		SecretKey secretKey = new SecretKeySpec(data, "HMACSHA1");
		Mac mac = Mac.getInstance("HMACSHA1");
		mac.init(secretKey);
		byte[] text = encryptText.getBytes("utf-8");
		return toString(mac.doFinal(text));
	}

	public static String hmacSHA256Encrypt(String encryptText, String encryptKey)
			throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException {
		byte[] data = encryptKey.getBytes("utf-8");
		SecretKey secretKey = new SecretKeySpec(data, "HMACSHA256");
		Mac mac = Mac.getInstance("HMACSHA256");
		mac.init(secretKey);
		byte[] text = encryptText.getBytes("utf-8");
		return toString(mac.doFinal(text));
	}

	private static String toString(byte[] b) throws UnsupportedEncodingException {
		return new String(Base64.getEncoder().encode(b), "UTF-8");
	}

}
