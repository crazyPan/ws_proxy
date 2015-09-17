package com.study.proxy.util;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import com.study.proxy.entity.CenterInfo;
import com.study.proxy.entity.ServerAddress;
import com.study.proxy.entity.ServerInfo;


public class ConfUtil {
    private static final String FILE_PATH = "/trans.properties";
    public  static Properties pros =  new Properties();
    private static CenterInfo centerInfo;
    private static int servernum = 0;
    private static int timeout = 0;
    public static CenterInfo getCenterInfo() {
		return centerInfo;
	}

	public static void setCenterInfo(CenterInfo centerInfo) {
		ConfUtil.centerInfo = centerInfo;
	}

	private static boolean isGetProxyInfoFromMdb;
    private static String proxyKey;

	public static void main(String[] args) {
		try {
			Properties pro = loadProperties(FILE_PATH);
			System.out.println(pro.get("ip_1"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getPropertyValue(String key){
		Properties pro = null;
		try {
			pro = loadProperties(FILE_PATH);
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		return pro.getProperty(key);
		
	}
	
	
    public static Properties loadProperties(String cpPath) throws Exception
    {
        InputStream is = ConfUtil.class.getResourceAsStream(cpPath);
        if (is == null)
        {
            return null;
        }
        pros.load(is);
        is.close();
        return pros;
    }
    
    /** 
     * 将byte数组转化成String,为了支持中文，转化时用GBK编码方式 
     */  
    public String ByteArraytoString(byte[] valArr,int maxLen) {  
        String result=null;  
        int index = 0;  
        while(index < valArr.length && index < maxLen) {  
            if(valArr[index] == 0) {  
                break;  
            }  
            index++;  
        }  
        byte[] temp = new byte[index];  
        System.arraycopy(valArr, 0, temp, 0, index);  
        try {  
            result= new String(temp,"GBK");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        return result;  
    }  
    
    public static CenterInfo getBillingCenterInfo() {
    	if(null == centerInfo){
    	   	centerInfo = new CenterInfo();
        	String centerInfostr = ConfUtil.getPropertyValue("billing_center_info");
        	System.out.println(centerInfostr);

        	String info = centerInfostr.substring(centerInfostr.indexOf('{')+1, centerInfostr.indexOf('}'));
        	String[] centers = info.split(",");
        	int centerId = 1;
        	for(String temp:centers){
        		String serverStr = temp.substring(temp.indexOf('[')+1, temp.indexOf(']'));
        		String[] serverAddress = serverStr.split("\\|");
        		ServerInfo serverInfo = new ServerInfo();
        		for(String addressStr:serverAddress){
        			ServerAddress address = new ServerAddress();
            		address.setIp(addressStr.substring(0, addressStr.indexOf(":")));
            		address.setPort(Integer.valueOf(addressStr.substring(addressStr.indexOf(":")+1)));
            		serverInfo.getServerInfo().add(address);
            		servernum++;
        		}
        	
        		centerInfo.getCenterMap().put(String.valueOf(centerId), serverInfo);
        		centerId++;
        	}
    	}
		return centerInfo;  
    }

	public static void initConfig() {
		isGetProxyInfoFromMdb = Boolean.valueOf(ConfUtil.getPropertyValue("get_proxyinfo_from_mdb"));
		proxyKey = ConfUtil.getPropertyValue("proxy_key");
		if(isGetProxyInfoFromMdb){
			centerInfo = ConfUtil.getBillingCenterInfo();
		}else{
			
		}
	}

	
	public static void setGetProxyInfoFromMdb(boolean isGetProxyInfoFromMdb) {
		ConfUtil.isGetProxyInfoFromMdb = isGetProxyInfoFromMdb;
	}

	public static boolean isGetProxyInfoFromMdb() {
		return isGetProxyInfoFromMdb;
	}

	public static void setProxyKey(String proxyKey) {
		ConfUtil.proxyKey = proxyKey;
	}

	public static String getProxyKey() {
		return proxyKey;
	}

	public static int getServernum() {
		return servernum;
	}

	public static void setTimeout(int timeout) {
		ConfUtil.timeout = timeout;
	}

	public static int getTimeout() {
		return timeout;
	}
}
