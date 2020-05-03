/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.swagger.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author reyna
 * DID NOT USE
 */
@Embeddable
public class IngredientFoodKey implements Serializable {
 
    public IngredientFoodKey(Integer foodId, Integer ingredientId) {
        this.foodId = foodId;
        this.ingredientId = ingredientId;
    }
    
    public IngredientFoodKey(){}
    
    @Column(name = "foodId")
    Integer foodId;
 
    @Column(name = "ingredientId")
    Integer ingredientId;

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public Integer getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Integer ingredientId) {
        this.ingredientId = ingredientId;
    }
}
