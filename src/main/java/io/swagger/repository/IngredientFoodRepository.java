/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.swagger.repository;

import io.swagger.model.IngredientFood;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Component;

/**
 *
 * @author reyna
 */
@Component
public interface IngredientFoodRepository extends JpaRepository<IngredientFood, Integer>, QueryByExampleExecutor<IngredientFood>{
    long deleteByFoodId(Integer foodId);
    long deleteByIngredientId(Integer ingredientId);
    List<IngredientFood> findByFoodIdIn(List<Integer> foodIds);
    List<IngredientFood> findByIngredientIdIn(List<Integer> ingredientIds);
    List<IngredientFood> findByFoodIdInAndIngredientIdIn(List<Integer> foodIds, List<Integer> ingredientIds);
}
