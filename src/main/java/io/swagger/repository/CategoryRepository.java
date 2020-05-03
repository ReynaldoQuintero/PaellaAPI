/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.swagger.repository;

import io.swagger.model.Category;
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
public interface CategoryRepository extends JpaRepository<Category, Integer>, QueryByExampleExecutor<Category>{
    List<Category> findByName(String name);
    List<Category> findByDescription(String description);
    List<Category> findByNameAndDescription(String name, String description);
}
