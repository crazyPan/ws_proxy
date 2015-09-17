package com.study.proxy.util;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class CommonUtil {

	/**
	 * 将String转化为byte,为了支持中文，转化时用GBK编码方式
	 */
	public static byte[] StringToByteArray(String str) {
		byte[] temp = null;
		try {
			temp = str.getBytes("GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return temp;
	}
	
	public static byte[] IntToBytes(int i) {
		ByteArrayOutputStream boutput = new ByteArrayOutputStream();
		DataOutputStream doutput = new DataOutputStream(boutput);
		try {
			doutput.writeInt(i);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] buf1 = boutput.toByteArray();
		return buf1;
	}
	
}
