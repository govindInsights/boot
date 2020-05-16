package com.gvs;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;


@Component
@Qualifier("myBean")
public class ServiceBean {

	Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private EurekaClient eurkeaClient;
	
	
	
	@Autowired
	private RestTemplateBuilder builder;
	
	public ServiceBean() {
		log.info("Service Insance Created for {1}, ", getClass().getName());
	}
	
	public String process() {
		eurkeaClient.getAllKnownRegions().stream().forEach(value -> log.info(" Region Value " + value));
		
		eurkeaClient.getApplications().getRegisteredApplications().stream().forEach(application -> 
			application.getInstances().stream().forEach(instance -> log.info(" app " + instance.getAppName() + " - " + instance.getPort()))
				);
		
		List<Application> applications = eurkeaClient.getApplications().getRegisteredApplications().stream().parallel()
				.filter(application -> application.getName().equals("SERVICE-TWO")).collect(Collectors.toList());
		
		RestTemplate restTemplate = builder.build();
		
		applications.get(0).getInstances().stream().parallel().forEach(instanceInfo -> 
				log.info(
						restTemplate.getForObject("http://"+ instanceInfo.getHostName()+ ":" + instanceInfo.getPort() + "/service/health" , String.class)
						)
			);
		
		return "default";
	}

	@PostConstruct
	private void postConstruct() {
		System.out.println("Inside Post Construct ");
	}
	
}
