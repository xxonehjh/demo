package com.hjh.demo.messageDigest;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import java.util.zip.CRC32;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 单向加密
 * 
 * @author xxonehjh
 */
public class MessageDigestTester {

	/**
	   crc32:158520161
       md5:e10adc3949ba59abbe56e057f20f883e
      sha1:7c4a8d09ca3762af61e59520943dc26494f8941b
    sha256:8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92
  hmacSHA1:5b5f3b669029c832f441444bb75e956f5887015d
hmacSHA256:5c5a5361d3506a4f18d4c47a73da241668d41e3233626e8aafa3fdb2e4a50cea
     crc32(1万次UUID):103ms
       md5(1万次UUID):72ms
      sha1(1万次UUID):51ms
    sha256(1万次UUID):45ms
  hmacSHA1(1万次UUID):97ms
hmacSHA256(1万次UUID):76ms
	 */
	public static void main(String args[]) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException {
		System.out.println("     crc32:" + crc32("123456"));
		System.out.println("       md5:" + md5("123456"));
		System.out.println("      sha1:" + sha1("123456"));
		System.out.println("    sha256:" + sha256("123456"));
		System.out.println("  hmacSHA1:" + hmacSHA1("123456", "password"));
		System.out.println("hmacSHA256:" + hmacSHA256("123456", "password"));

		System.out.println("     crc32(1万次UUID):" + cost((txt) -> crc32(txt)) + "ms");
		System.out.println("       md5(1万次UUID):" + cost((txt) -> md5(txt)) + "ms");
		System.out.println("      sha1(1万次UUID):" + cost((txt) -> sha1(txt)) + "ms");
		System.out.println("    sha256(1万次UUID):" + cost((txt) -> sha256(txt)) + "ms");
		System.out.println("  hmacSHA1(1万次UUID):" + cost((txt) -> hmacSHA1(txt, "password")) + "ms");
		System.out.println("hmacSHA256(1万次UUID):" + cost((txt) -> hmacSHA256(txt, "password")) + "ms");
	}

	public interface Runner {
		public void run(String txt) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException;
	}
	
	
	public static long cost(Runner run) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException {
		long start = System.currentTimeMillis();
		int count = 10000;
		for (int i = 0; i < count; i++) {
			run.run("TEST-TEST" + UUID.randomUUID().toString());
		}
		return System.currentTimeMillis() - start;
	}

	public static String crc32(String txt) throws UnsupportedEncodingException {
		CRC32 crc32 = new CRC32();
		crc32.update(txt.getBytes("utf-8"));
		return crc32.getValue() + "";
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

	public static String hmacSHA1(String encryptText, String encryptKey)
			throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException {
		SecretKey secretKey = new SecretKeySpec(encryptKey.getBytes("utf-8"), "HMACSHA1");
		Mac mac = Mac.getInstance("HMACSHA1");
		mac.init(secretKey);
		return toString(mac.doFinal(encryptText.getBytes("utf-8")));
	}

	public static String hmacSHA256(String encryptText, String encryptKey)
			throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException {
		SecretKey secretKey = new SecretKeySpec(encryptKey.getBytes("utf-8"), "HMACSHA256");
		Mac mac = Mac.getInstance("HMACSHA256");
		mac.init(secretKey);
		return toString(mac.doFinal(encryptText.getBytes("utf-8")));
	}

	private static String toString(byte[] b) throws UnsupportedEncodingException {
		// 1个byte是8bit，1个16进制数可以表示4bit，故1byte可以用2个16进制数表示
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < b.length; i++) {
			int v = Byte.toUnsignedInt(b[i]); // 转换为无符号整型
			if (v < 16) {
				builder.append("0");
			}
			builder.append(Integer.toHexString(v));
		}
		return builder.toString();
	}

}
