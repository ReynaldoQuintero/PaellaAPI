/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.swagger.repository;

import io.swagger.model.Ingredient;
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
public interface IngredientRepository extends JpaRepository<Ingredient, Integer>, QueryByExampleExecutor<Ingredient>{
    List<Ingredient> findByName(String name);
    List<Ingredient> findByDescription(String description);
    List<Ingredient> findByNameAndDescription(String name, String description);
}
