package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.repository.FoodOrderRepository;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Order
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-07T10:08:47.568Z[GMT]")
@Entity(name = "order_table")
public class Order   {
    
  @Autowired
  @Transient
  FoodOrderRepository foodOrderRepo;
    
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("userID")
  private Integer userID = null;

  /**
   * The status of the order
   */
  public enum StatusEnum {
    DELIVERED("Delivered"),
    UNDELIVERED("Undelivered"),
    INCART("In_cart");
    

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("status")
  private StatusEnum status = null;

  @JsonProperty("creation_date")
  private OffsetDateTime creationDate = null;

  @JsonProperty("update_date")
  private OffsetDateTime updateDate = null;

  @JsonProperty("total")
  private BigDecimal total = null;

  public Order id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * The identifier of the customer
   * @return id
  **/
  @ApiModelProperty(example = "1", value = "The identifier of the customer")
  
    public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Order userID(Integer userID) {
    this.userID = userID;
    return this;
  }

  /**
   * The identification of the user that made the order
   * @return userID
  **/
  @ApiModelProperty(example = "1", required = true, value = "The identification of the user that made the order")
      @NotNull

    public Integer getUserID() {
    return userID;
  }

  public void setUserID(Integer userID) {
    this.userID = userID;
  }

  public Order status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * The status of the order
   * @return status
  **/
  @ApiModelProperty(example = "In_cart", required = true, value = "The status of the order")
      @NotNull

    public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public Order creationDate(OffsetDateTime creationDate) {
    this.creationDate = creationDate;
    return this;
  }

  /**
   * The date the order was created
   * @return creationDate
  **/
  @ApiModelProperty(example = "2020-07-21T17:32:28Z", required = true, value = "The date the order was created")
      @NotNull

    @Valid
    public OffsetDateTime getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(OffsetDateTime creationDate) {
    this.creationDate = creationDate;
  }

  public Order updateDate(OffsetDateTime updateDate) {
    this.updateDate = updateDate;
    return this;
  }

  /**
   * The last date the order was updated
   * @return updateDate
  **/
  @ApiModelProperty(example = "2020-07-25T17:32:28Z", value = "The last date the order was updated")
  
    @Valid
    public OffsetDateTime getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(OffsetDateTime updateDate) {
    this.updateDate = updateDate;
  }

  public Order total(BigDecimal total) {
    this.total = total;
    return this;
  }

  /**
   * The total payed by the customer in this order
   * @return total
  **/
  @ApiModelProperty(example = "20.67", value = "The total payed by the customer in this order")
  
    @Valid
    public BigDecimal getTotal() {
    return total;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }
  
  
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Order order = (Order) o;
    return Objects.equals(this.id, order.id) &&
        Objects.equals(this.userID, order.userID) &&
        Objects.equals(this.status, order.status) &&
        Objects.equals(this.creationDate, order.creationDate) &&
        Objects.equals(this.updateDate, order.updateDate) &&
        Objects.equals(this.total, order.total);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userID, status, creationDate, updateDate, total);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Order {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    userID: ").append(toIndentedString(userID)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
    sb.append("    updateDate: ").append(toIndentedString(updateDate)).append("\n");
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
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
