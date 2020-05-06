package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Food
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-07T10:08:47.568Z[GMT]")
@Entity
public class Food   {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @JsonProperty("foodId")
  private Integer foodId = null;
  
  @JsonProperty("categoryId")
  private Integer categoryId = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("price")
  private BigDecimal price = null;

  @JsonProperty("imageURL")
  private String imageURL = null;
  
  @JsonProperty("active")
  private Boolean active = null;
  
  @JsonProperty("isDishOfDay")
  private Boolean isDishOfDay = null;

  public Food foodId(Integer foodId) {
    this.foodId = foodId;
    return this;
  }

  /**
   * The identifier of the food
   * @return foodId
  **/
  @ApiModelProperty(example = "1", value = "The identifier of the food")
  
    public Integer getFoodId() {
    return foodId;
  }

  public void setFoodId(Integer foodId) {
    this.foodId = foodId;
  }
  
  public Food categoryId(Integer categoryId) {
    this.categoryId = categoryId;
    return this;
  }

  /**
   * The identifier of the category
   * @return categoryId
  **/
  @ApiModelProperty(example = "1", required = true, value = "The identifier of the category")
    @NotNull
  
    public Integer getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Integer categoryId) {
    this.categoryId = categoryId;
  }

  public Food name(String name) {
    this.name = name;
    return this;
  }

  /**
   * The name of the food.
   * @return name
  **/
  @ApiModelProperty(example = "Burger", required = true, value = "The name of the food.")
      @NotNull

    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Food description(String description) {
    this.description = description;
    return this;
  }

  /**
   * The description of the food.
   * @return description
  **/
  @ApiModelProperty(example = "Delicious meat between 2 buns", required = true, value = "The description of the food.")
      @NotNull

    public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Food price(BigDecimal price) {
    this.price = price;
    return this;
  }

  /**
   * The price of the food.
   * @return price
  **/
  @ApiModelProperty(example = "5.67", required = true, value = "The price of the food.")
      @NotNull

    @Valid
    public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Food imageURL(String imageURL) {
    this.imageURL = imageURL;
    return this;
  }

  /**
   * The URL of the picture of the food.
   * @return imageURL
  **/
  @ApiModelProperty(example = "https://www.la-viande.fr/sites/default/files/styles/slider_recettes/public/recettes/images/burger-de-hampe-de-boeuf-a-laustralienne.jpg", value = "The URL of the picture of the food.")
  
    public String getImageURL() {
    return imageURL;
  }

  public void setImageURL(String imageURL) {
    this.imageURL = imageURL;
  }
  
  public Food active(Boolean active) {
    this.active = active;
    return this;
  }

  /**
   * Wether the food is active to be seen by the customers
   * @return active
  **/
  @ApiModelProperty(example = "True", required = true, value = "Wether the food is active to be seen by the customers")
  
    public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }
  
  public Food isDishOfDay(Boolean isDishOfDay) {
    this.isDishOfDay = isDishOfDay;
    return this;
  }

  /**
   * The URL of the picture of the food.
   * @return isDishOfDay
  **/
  @ApiModelProperty(example = "True", required = true, value = "Wether the food is to be highlighted as dish of the day")
  
    public Boolean getIsDishOfDay() {
    return isDishOfDay;
  }

  public void setIsDishOfDay(Boolean isDishOfDay) {
    this.isDishOfDay = isDishOfDay;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Food food = (Food) o;
    return Objects.equals(this.foodId, food.foodId) &&
        Objects.equals(this.name, food.name) &&
        Objects.equals(this.categoryId, food.categoryId) &&
        Objects.equals(this.description, food.description) &&
        Objects.equals(this.price, food.price) &&
        Objects.equals(this.active, food.active) &&
        Objects.equals(this.isDishOfDay, food.isDishOfDay) &&
        Objects.equals(this.imageURL, food.imageURL);
  }

  @Override
  public int hashCode() {
    return Objects.hash(foodId, categoryId, name, description, price, imageURL, active, isDishOfDay);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Food {\n");
    
    sb.append("    foodId: ").append(toIndentedString(foodId)).append("\n");
    sb.append("    categoryId: ").append(toIndentedString(categoryId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    imageURL: ").append(toIndentedString(imageURL)).append("\n");
    sb.append("    active: ").append(toIndentedString(active)).append("\n");
    sb.append("    isDishOfDay: ").append(toIndentedString(isDishOfDay)).append("\n");
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
