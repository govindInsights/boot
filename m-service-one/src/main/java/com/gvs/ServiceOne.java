package com.gvs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

@RequestMapping("/service")
@RestController
@RequestScope
public class ServiceOne {

	
	Logger log = LoggerFactory.getLogger(getClass());
	
	private ServiceBean serviceBean;
	
	@Autowired
	public ServiceOne(ServiceBean serviceBean) {
		this.serviceBean = serviceBean;
		log.info("Bean" + serviceBean);
		log.info("Service Insance Created for {1}, ", getClass().getName());
	}
	
	@GetMapping("health")
	public String get() {
		return "I am " + getClass().getSimpleName();
	}
}
