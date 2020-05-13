package com.gvs;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
@Qualifier("myBean")
public class ServiceBean {

	Logger log = LoggerFactory.getLogger(getClass());
	
	public ServiceBean() {
		log.info("Service Insance Created for {1}, ", getClass().getName());
	}

	@PostConstruct
	private void postConstruct() {
		System.out.println("Inside Post Construct ");
	}
	
}
