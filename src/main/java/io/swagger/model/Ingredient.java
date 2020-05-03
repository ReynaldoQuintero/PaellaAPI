package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Ingredient
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-07T10:08:47.568Z[GMT]")
@Entity
public class Ingredient   {
    
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @JsonProperty("IngredientID")
  private Integer ingredientID = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("imageURL")
  private String imageURL = null;
  
  public Ingredient ingredientID(Integer ingredientID) {
    this.ingredientID = ingredientID;
    return this;
  }

  /**
   * The identifier of the ingredient
   * @return ingredientID
  **/
  @ApiModelProperty(example = "1", value = "The identifier of the ingredient")
  
    public Integer getIngredientID() {
    return ingredientID;
  }

  public void setIngredientID(Integer ingredientID) {
    this.ingredientID = ingredientID;
  }

  public Ingredient name(String name) {
    this.name = name;
    return this;
  }

  /**
   * The name of the ingredient.
   * @return name
  **/
  @ApiModelProperty(example = "Pickles", required = true, value = "The name of the ingredient.")
      @NotNull

    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Ingredient description(String description) {
    this.description = description;
    return this;
  }

  /**
   * The description of the ingredient.
   * @return description
  **/
  @ApiModelProperty(example = "Homegrown pickles", required = true, value = "The description of the ingredient.")
      @NotNull

    public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Ingredient imageURL(String imageURL) {
    this.imageURL = imageURL;
    return this;
  }

  /**
   * The URL of the picture of the ingredient.
   * @return imageURL
  **/
  @ApiModelProperty(example = "https://media.fromthegrapevine.com/assets/images/2017/9/fresh-pickles.jpg.839x0_q71_crop-scale.jpg", value = "The URL of the picture of the ingredient.")
  
    public String getImageURL() {
    return imageURL;
  }

  public void setImageURL(String imageURL) {
    this.imageURL = imageURL;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Ingredient ingredient = (Ingredient) o;
    return Objects.equals(this.ingredientID, ingredient.ingredientID) &&
        Objects.equals(this.name, ingredient.name) &&
        Objects.equals(this.description, ingredient.description) &&
        Objects.equals(this.imageURL, ingredient.imageURL);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ingredientID, name, description, imageURL);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Ingredient {\n");
    
    sb.append("    ingredientID: ").append(toIndentedString(ingredientID)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    imageURL: ").append(toIndentedString(imageURL)).append("\n");
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
