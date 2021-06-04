package com.mohit.ws.soap.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws22.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.mohit.ws.soap.CustomerOrdersWsImpl;

@Configuration
public class WebServiceConfig {
	
	@Autowired
	private Bus bus;
	
	public Endpoint endpoint() {
		Endpoint endpoint = new EndpointImpl(bus, new CustomerOrdersWsImpl());
		endpoint.publish("/customerorderservice");
		return endpoint;
		
	}

}
