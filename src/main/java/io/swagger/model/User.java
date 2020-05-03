package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * User
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-07T10:08:47.568Z[GMT]")
@Entity(name = "user_table")
public class User   {
    
    
  
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("firstname")
  private String firstname = null;

  @JsonProperty("lastname")
  private String lastname = null;

  @JsonProperty("email")
  @Column(unique=true)
  private String email = null;

  @JsonProperty("password")
  private String password = null;

  /**
   * the privilege the user has
   */
  public enum PrivilegeEnum {
    BASIC("Basic"),
    
    ADMIN("Admin");

    private String value;

    PrivilegeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static PrivilegeEnum fromValue(String text) {
      for (PrivilegeEnum b : PrivilegeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("privilege")
  private PrivilegeEnum privilege = null;
  
  public User(String fn, String ln, String email, String pw, String priv) {
    this.firstname = fn;
    this.lastname = ln;
    this.email = email;
    this.password = pw;
    this.privilege = PrivilegeEnum.valueOf(priv);
  }
  
  public User(){}

  public User id(Integer id) {
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

  public User firstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  /**
   * The first name of the customer
   * @return firstname
  **/
  @ApiModelProperty(example = "John", required = true, value = "The first name of the customer")
      @NotNull

    public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public User lastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  /**
   * The last name of the customer
   * @return lastname
  **/
  @ApiModelProperty(example = "Doe", required = true, value = "The last name of the customer")
      @NotNull

    public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public User email(String email) {
    this.email = email;
    return this;
  }

  /**
   * the email of the customer
   * @return email
  **/
  @ApiModelProperty(example = "johndoe@example.com", required = true, value = "the email of the customer")
      @NotNull

    public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public User password(String password) {
    this.password = password;
    return this;
  }

  /**
   * the password
   * @return password
  **/
  @ApiModelProperty(example = "examplepassword123", required = true, value = "the password")
      @NotNull

    public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public User privilege(PrivilegeEnum privilege) {
    this.privilege = privilege;
    return this;
  }

  /**
   * the privilege the user has
   * @return privilege
  **/
  @ApiModelProperty(example = "Basic", value = "the privilege the user has")
  
    public PrivilegeEnum getPrivilege() {
    return privilege;
  }

  public void setPrivilege(PrivilegeEnum privilege) {
    this.privilege = privilege;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(this.id, user.id) &&
        Objects.equals(this.firstname, user.firstname) &&
        Objects.equals(this.lastname, user.lastname) &&
        Objects.equals(this.email, user.email) &&
        Objects.equals(this.password, user.password) &&
        Objects.equals(this.privilege, user.privilege);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname, email, password, privilege);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class User {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    firstname: ").append(toIndentedString(firstname)).append("\n");
    sb.append("    lastname: ").append(toIndentedString(lastname)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    privilege: ").append(toIndentedString(privilege)).append("\n");
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
