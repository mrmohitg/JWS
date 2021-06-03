package com.mohit.ws.soap;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mohit.ws.trainings.CreateOrdersRequest;
import com.mohit.ws.trainings.CreateOrdersResponse;
import com.mohit.ws.trainings.CustomerOrdersPortType;
import com.mohit.ws.trainings.GetOrdersRequest;
import com.mohit.ws.trainings.GetOrdersResponse;
import com.mohit.ws.trainings.Order;
import com.mohit.ws.trainings.Product;

public class CustomerOrdersWsImpl implements CustomerOrdersPortType{
	
	Map<BigInteger,List<Order>> customerOrders = new HashMap<>();
	int currentId;
	
	public CustomerOrdersWsImpl() {
		super();
		init();
	}

	public void init()
	{
		List<Order> orders = new ArrayList<>();
		Order order = new Order();
		order.setId(BigInteger.valueOf(1));
		
		Product product = new Product();
		product.setId("1");
		product.setDescription("IPhone");
		product.setQuantity(BigInteger.valueOf(2));
		
		order.getProduct().add(product);
		orders.add(order);
		
		customerOrders.put(BigInteger.valueOf(++currentId), orders);
	}

	@Override
	public GetOrdersResponse getOrders(GetOrdersRequest request) {
		
		BigInteger cusomerId = request.getCustomerId();
		List<Order> orders = customerOrders.get(cusomerId);
		
		GetOrdersResponse response = new GetOrdersResponse();
		response.getOrder().addAll(orders);
		return response;
	}

	@Override
	public CreateOrdersResponse createOrders(CreateOrdersRequest request) {
		BigInteger customerId = request.getCustomerId();
		Order order = request.getOrder();
		
		List<Order> orders = customerOrders.get(customerId);
		orders.add(order);
		
		CreateOrdersResponse response = new CreateOrdersResponse();
		response.setResult(true);
		return response;
	}

}
