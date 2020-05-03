package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.repository.FoodRepository;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.Transient;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * FoodOrder
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-07T10:08:47.568Z[GMT]")
@Entity
public class FoodOrder   {
  
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @JsonProperty("id")
  private Integer id = null;
  
  @JsonProperty("foodId")
  private Integer foodId = null; 
  
  @JsonProperty("orderId")
  private Integer orderId = null; 

  @JsonProperty("quantity")
  private Integer quantity = null;

  @JsonProperty("comments")
  private String comments = null;

  @JsonProperty("subTotal")
  private BigDecimal subTotal = null;

  public FoodOrder foodId(Integer foodId) {
    this.foodId = foodId;
    return this;
  }

  /**
   * The identifier of the food bought
   * @return foodId
  **/
  @ApiModelProperty(example = "1", value = "The identifier of the food bought")
  
    public Integer getFoodId() {
    return this.foodId;
  }

  public void setFoodId(Integer foodId) {
     this.foodId = foodId;
  }

  /**
   * The identification of the order to which the detail belongs to. If it is empty, it belongs to the cart.
   * @return orderId
  **/
  @ApiModelProperty(example = "1", value = "The identification of the order to which the detail belongs to. If it is empty, it belongs to the cart.")
  
    public Integer getOrderId() {
    return this.orderId;
  }

  public void setOrderId(Integer orderId) {
    this.orderId = orderId;
  }

  public FoodOrder quantity(Integer quantity) {
    this.quantity = quantity;
    return this;
  }

  /**
   * The amount of the food that is to be ordered
   * @return quantity
  **/
  @ApiModelProperty(example = "1", required = true, value = "The amount of the food that is to be ordered")
      @NotNull

    public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public FoodOrder comments(String comments) {
    this.comments = comments;
    return this;
  }

  /**
   * Some comments of the order, to maybe change the way it's cooked or the ingredients that are used.
   * @return comments
  **/
  @ApiModelProperty(example = "I don't want pickles", value = "Some comments of the order, to maybe change the way it's cooked or the ingredients that are used.")
  
    public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  public FoodOrder subTotal(BigDecimal subTotal) {
    this.subTotal = subTotal;
    return this;
  }

  /**
   * The total to be payed only from this specific detail.
   * @return subTotal
  **/
  @ApiModelProperty(example = "5.67", value = "The total to be payed only from this specific detail.")
  
    @Valid
    public BigDecimal getSubTotal() {
    return subTotal;
  }

  public void setSubTotal(BigDecimal subTotal) {
    this.subTotal = subTotal;
  }
  
  
  public FoodOrder id(Integer id) {
    this.id = id;
    return this;
  }
  /**
   * The total to be payed only from this specific detail.
   * @return subTotal
  **/
  @ApiModelProperty(example = "1", value = "The ID of the food order")

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
  
  
  


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FoodOrder foodOrder = (FoodOrder) o;
    return Objects.equals(this.id, foodOrder.id) &&
        Objects.equals(this.quantity, foodOrder.quantity) &&
        Objects.equals(this.comments, foodOrder.comments) &&
        Objects.equals(this.subTotal, foodOrder.subTotal);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, quantity, comments, subTotal);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FoodOrder {\n");
    
    sb.append("    id: ").append(toIndentedString(this.getId())).append("\n");
    sb.append("    foodId: ").append(toIndentedString(this.getFoodId())).append("\n");
    sb.append("    orderId: ").append(toIndentedString(this.getOrderId())).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
    sb.append("    comments: ").append(toIndentedString(comments)).append("\n");
    sb.append("    subTotal: ").append(toIndentedString(subTotal)).append("\n");
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
