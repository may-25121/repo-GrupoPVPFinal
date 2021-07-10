package ar.edu.unju.fi.tpfinal.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tpfinal.model.Order;
import ar.edu.unju.fi.tpfinal.model.OrderDetails;
import ar.edu.unju.fi.tpfinal.model.OrderDetailsId;
import ar.edu.unju.fi.tpfinal.model.Products;
import ar.edu.unju.fi.tpfinal.repository.IOrderDAO;
import ar.edu.unju.fi.tpfinal.repository.IOrdersDetailsDAO;
import ar.edu.unju.fi.tpfinal.repository.IProductsDAO;
import ar.edu.unju.fi.tpfinal.service.IOrderDetailsService;

@Service("orderDetailServiceMysql")
public class OrderDetailsServiceImp implements IOrderDetailsService {
	
	@Autowired
	IOrdersDetailsDAO orderDetailDAO;
	
	@Autowired
	IOrderDAO orderDAO;
	
	@Autowired
	IProductsDAO productDAO;
	
	@Autowired
	private OrderDetails orderDetail;
	
	@Autowired
	private Order order;
	
	@Autowired
	private Products product;

	@Override
	public OrderDetails getorderDetail() {
		return this.orderDetail;
	}

	@Override
	public void saveOrderDetail(OrderDetails orderDetails) {
		orderDetailDAO.save(orderDetails);
	}

	@Override
	public OrderDetails getOrderDetailById(OrderDetailsId Id) {
		OrderDetails orderDetail = orderDetailDAO.findById(Id).get();
		return orderDetail;
	}

	@Override
	public List<OrderDetails> getAllOrderDetails() {
		List<OrderDetails> orderDetails = (List<OrderDetails>) orderDetailDAO.findAll();
		return orderDetails;
	}

	@Override
	public void deleteOrderDetailById(OrderDetailsId Id) {
		orderDetailDAO.deleteById(Id);
	}

	//Falta corregir el metodo de busqueda 
	
	@Override
	public List<OrderDetails> getOrderDetails(String var) {
		List<OrderDetails> orderDetails = new ArrayList<>();
		Order order = new Order();
		order = orderDAO.findById(var).get(); 
		Products product = new Products();
		product = productDAO.findById(var).get();
		if(!var.isEmpty() && !orderDetailDAO.findByIdOrderNumber(order).isEmpty()) {
			orderDetails = orderDetailDAO.findByIdOrderNumber(order);
		}else if(!var.isEmpty() && !orderDetailDAO.findByIdProductCode(product).isEmpty()) {
			orderDetails = orderDetailDAO.findByIdProductCode(product);
		}else {
			orderDetails = (List<OrderDetails>) orderDetailDAO.findAll();
		}
		return orderDetails;
	}

}
