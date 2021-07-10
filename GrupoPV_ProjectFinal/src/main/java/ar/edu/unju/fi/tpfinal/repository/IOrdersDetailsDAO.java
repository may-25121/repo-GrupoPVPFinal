package ar.edu.unju.fi.tpfinal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unju.fi.tpfinal.model.Order;
import ar.edu.unju.fi.tpfinal.model.OrderDetails;
import ar.edu.unju.fi.tpfinal.model.OrderDetailsId;
import ar.edu.unju.fi.tpfinal.model.Products;

public interface IOrdersDetailsDAO  extends CrudRepository<OrderDetails, OrderDetailsId> {

	public List<OrderDetails> findByIdOrderNumber(Order order);
	public List<OrderDetails> findByIdProductCode(Products product);

}
