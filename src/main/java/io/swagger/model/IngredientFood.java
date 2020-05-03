package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.*;

/**
 * IngredientFood
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-07T10:08:47.568Z[GMT]")
@Entity
public class IngredientFood   {
    
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @JsonProperty("id")
  private Integer id;
  
  @JsonProperty("foodId")
  private int foodId;
  
  @JsonProperty("ingredientId")
  private int ingredientId;

  @JsonProperty("quantity")
  private Float quantity = null;

  @JsonProperty("unit")
  private String unit = null;


  public IngredientFood quantity(Float quantity) {
    this.quantity = quantity;
    return this;
  }

  /**
   * The amount of the food that is to be ordered.
   * @return quantity
  **/
  @ApiModelProperty(example = "1.5", required = true, value = "The amount of the ingredient that this food has.")
      @NotNull

    public Float getQuantity() {
    return quantity;
  }

  public void setQuantity(Float quantity) {
    this.quantity = quantity;
  }

  public IngredientFood unit(String unit) {
    this.unit = unit;
    return this;
  }

  /**
   * The unit in which the quantity is measured.
   * @return unit
  **/
  @ApiModelProperty(example = "Grams", required = true, value = "The unit in which the quantity is measured.")
      @NotNull
  
    public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }
  
  public IngredientFood id(Integer id) {
    this.id = id;
    return this;
  }
  
  /**
    * The id of the IngredientFood
    * @return id
   **/
  @ApiModelProperty(example = "1", value = "The id of the IngredientFood")

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
  
  public IngredientFood foodId(Integer foodId) {
    this.foodId = foodId;
    return this;
  }

  /**
    * The id of the Food
    * @return foodId
   **/
  @ApiModelProperty(example = "1", value = "The id of the Food")
    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }
    
    
    public IngredientFood ingredientId(Integer ingredientId) {
        this.ingredientId = ingredientId;
        return this;
    }
    
    /**
    * The id of the Ingredient
    * @return ingredientId
   **/
  @ApiModelProperty(example = "1", required = true, value = "The id of the Ingredient")
      @NotNull

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }
  
  
  
  
  

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IngredientFood ingredientFood = (IngredientFood) o;
    return Objects.equals(this.id, ingredientFood.id) &&
        Objects.equals(this.quantity, ingredientFood.quantity) &&
        Objects.equals(this.unit, ingredientFood.unit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, quantity, unit);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IngredientFood {\n");
    
    sb.append("    foodId: ").append(toIndentedString(this.getFoodId())).append("\n");
    sb.append("    ingredientID: ").append(toIndentedString(this.getIngredientId())).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
    sb.append("    unit: ").append(toIndentedString(unit)).append("\n");
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
