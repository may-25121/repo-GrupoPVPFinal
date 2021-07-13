package ar.edu.unju.fi.tpfinal.service;

import java.util.Date;
import java.util.List;

import ar.edu.unju.fi.tpfinal.model.Order;



public interface IOrderService {
	
	public Order getOrder();
	public void saveOrder(Order order);
	public Order getOrderById(int id);
	public List<Order> getAllOrders();
	public void deleteOrderById(int id);
	public List<Order> getOrders(String var);
	public boolean getCheckOrderById(String number);
	

}
