package com.study.proxy.main;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.study.proxy.entity.VacRequest;
import com.study.proxy.entity.VacResponse;

@org.apache.cxf.interceptor.InInterceptors(interceptors={"com.ailk.proxy.interceptor.ProxyInterceptor"})
@WebService
public interface TransServer {
    @WebMethod 
	public VacResponse proxy(VacRequest vacRequest);
}