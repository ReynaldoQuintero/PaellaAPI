/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

/**
 *
 * @author reyna
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-07T10:08:47.568Z[GMT]")
public class FoodAndIngredients {
    @JsonProperty("food")
    private Food food = null;

    @JsonProperty("ingredientFoods")
    private List<IngredientFood> ingredientFoods = new ArrayList<IngredientFood>();

    
    public FoodAndIngredients food(Food food) {
        this.food = food;
        return this;
    }
     /**
    * The name of the food.
    * @return name
    **/
   @ApiModelProperty(required = true, value = "The Food object.")
       @NotNull
    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
    
    public FoodAndIngredients ingredientFoods(List<IngredientFood> ingredientFoods) {
        this.ingredientFoods = ingredientFoods;
        return this;
    }

    public FoodAndIngredients addIngredientFoodsItem(IngredientFood ingredientFoodsItem) {
        this.ingredientFoods.add(ingredientFoodsItem);
        return this;
    }
    @ApiModelProperty(required = true, value = "An array of the ingredient foods for this food.")
          @NotNull
        @Valid
    @Size(min=1) public List<IngredientFood> getIngredientFoods() {
        return ingredientFoods;
    }

    public void setIngredientFoods(List<IngredientFood> ingredientFoods) {
        this.ingredientFoods = ingredientFoods;
    }
    
    @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FoodAndIngredients foodAndIngredients = (FoodAndIngredients) o;
    return Objects.equals(this.food, foodAndIngredients.food) &&
        Objects.equals(this.ingredientFoods, foodAndIngredients.ingredientFoods);
  }

  @Override
  public int hashCode() {
    return Objects.hash(food, ingredientFoods);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IngredientFoods {\n");
    
    sb.append("    food: ").append(toIndentedString(food)).append("\n");
    sb.append("    ingredientFoods: ").append(toIndentedString(ingredientFoods)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
