/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.swagger.repository;

import io.swagger.model.Food;
import io.swagger.model.FoodOrder;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Component;

/**
 *
 * @author reyna
 */
@Component
public interface FoodOrderRepository extends JpaRepository<FoodOrder, Integer>, QueryByExampleExecutor<FoodOrder>{
    long deleteByOrderId(Integer orderId);
    List<FoodOrder> findByOrderId(Integer orderId);
    List<FoodOrder> findByFoodIdIn(List<Integer> foodIds);
}
