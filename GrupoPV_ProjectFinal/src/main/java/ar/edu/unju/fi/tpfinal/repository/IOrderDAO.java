package ar.edu.unju.fi.tpfinal.repository;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unju.fi.tpfinal.model.Order;


public interface IOrderDAO  extends CrudRepository<Order, Integer> {
 

}
