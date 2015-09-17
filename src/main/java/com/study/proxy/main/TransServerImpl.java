package com.study.proxy.main;

import javax.jws.WebService;

import com.study.proxy.entity.CenterInfo;
import com.study.proxy.entity.VacRequest;
import com.study.proxy.entity.VacResponse;
import com.study.proxy.util.ConfUtil;

@WebService()
public class TransServerImpl implements TransServer {

	//物理中心信息
	public static CenterInfo centerinfo = null;
	//线程组
	
	//队列组
	public static void main(String[] args) {
		
	}
	
	public void init(){
		//1.----初始化物理中心配置信息------------------------------------
		//根据配置文件里的一个开关，判断是从mdb查询路由信息，还是从配置文件里
        boolean isGetProxyInfoFromMdb = Boolean.valueOf(ConfUtil.getPropertyValue("get_proxyinfo_from_mdb"));
        if(isGetProxyInfoFromMdb){
        	centerinfo = ConfUtil.getBillingCenterInfo();
        }else{
        	//从mdb读取一次就好，提供一个刷新的功能
        	
        	
        	
        }
        
        //2.----初始化消息队列------------------------------------------
        
        
        
        
        
        //3.----初始化线程组------------------------------------
        
        
        
        
        
        
        

	}
	public VacResponse proxy(VacRequest vacRequest) {
		System.out.println("proxy");
		return null;
	}

}