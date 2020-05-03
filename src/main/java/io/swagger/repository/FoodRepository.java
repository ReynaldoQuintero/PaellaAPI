/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.swagger.repository;

import io.swagger.model.Food;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Component;

/**
 *
 * @author reyna
 */
@Component
public interface FoodRepository extends JpaRepository<Food, Integer>, QueryByExampleExecutor<Food>{
    
    List<Food> findByName(String name);
    List<Food> findByIsDishOfDay(Boolean isDishOfDay);
    List<Food> findByCategoryId(Integer cateogryId);
    @Query("SELECT SUM(fo.quantity) from FoodOrder fo where fo.foodId =:foodId")
    Integer findQuantitySoldByFoodId(@Param("foodId") Integer foodId);
}
