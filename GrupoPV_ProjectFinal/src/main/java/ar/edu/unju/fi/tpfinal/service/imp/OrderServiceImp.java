package ar.edu.unju.fi.tpfinal.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tpfinal.model.Order;
import ar.edu.unju.fi.tpfinal.repository.IOrderDAO;
import ar.edu.unju.fi.tpfinal.service.IOrderService;

@Service("orderServiceMysql")
public class OrderServiceImp implements IOrderService {
	
	@Autowired
	IOrderDAO orderDAO;
	
	@Autowired
	private Order order;

	@Override
	public Order getOrder() {
		return this.order;
	}

	@Override
	public void saveOrder(Order order) {
		orderDAO.save(order);
	}

	@Override
	public Order getOrderById(int id) {
		Order order = orderDAO.findById(String.valueOf(id)).get();
		return order;
	}

	@Override
	public List<Order> getAllOrders() {
		List<Order> orders = (List<Order>) orderDAO.findAll();
		return orders;
	}

	@Override
	public void deleteOrderById(int id) {
		orderDAO.deleteById(String.valueOf(id));
	}

	@Override
	public List<Order> getOrders(String var) {
		List<Order> orders = new ArrayList<>();
		if(!var.isEmpty() && !orderDAO.findById(var).isEmpty()) {
			orders.add(orderDAO.findById(var).get());
		}else if(!var.isEmpty() && !orderDAO.findByStatus(var).isEmpty()) {
			orders = orderDAO.findByStatus(var);
		}else {
			orders = (List<Order>) orderDAO.findAll();
		}
		return orders;
	}

	@Override
	public boolean getCheckOrderById(String number) {
		boolean var = false;
		if(!number.isEmpty() && !orderDAO.findById(number).isEmpty()) {
			var = true;
		}
		return var;
	}

}
