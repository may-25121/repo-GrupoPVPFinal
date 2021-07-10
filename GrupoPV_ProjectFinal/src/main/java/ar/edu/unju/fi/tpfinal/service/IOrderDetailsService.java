package ar.edu.unju.fi.tpfinal.service;

import java.util.List;

import ar.edu.unju.fi.tpfinal.model.OrderDetails;
import ar.edu.unju.fi.tpfinal.model.OrderDetailsId;


public interface IOrderDetailsService {

	public OrderDetails getorderDetail();
	public void saveOrderDetail(OrderDetails orderDetails);
	public OrderDetails getOrderDetailById(OrderDetailsId Id);
	public List<OrderDetails> getAllOrderDetails();
	public void deleteOrderDetailById(OrderDetailsId Id);
	public List<OrderDetails> getOrderDetails(String var);

}
