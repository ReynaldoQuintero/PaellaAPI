/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.swagger.repository;

import io.swagger.model.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Component;

/**
 *
 * @author reyna
 */
@Component
public interface OrderRepository extends JpaRepository<Order, Integer>, QueryByExampleExecutor<Order>{
    List<Order> findByUserID(int userID);
    List<Order> findByStatus(Order.StatusEnum status);
    List<Order> findByUserIDAndStatus(int userID, Order.StatusEnum status);
    List<Order> findByIdIn(List<Integer> orderID);
    List<Order> findByUserIDIn(List<Integer> userID);
    List<Order> findByIdInAndStatus(List<Integer> orderID, Order.StatusEnum status);
    List<Order> findByUserIDInAndStatus(List<Integer> userID, Order.StatusEnum status);
    List<Order> findByIdInAndUserIDIn(List<Integer> orderID, List<Integer> userID);
    List<Order> findByIdInAndUserIDInAndStatus(List<Integer> orderID, List<Integer> userID, Order.StatusEnum status);
    
    
}
