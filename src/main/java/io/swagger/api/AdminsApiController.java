package io.swagger.api;

import io.swagger.model.RestError;
import io.swagger.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-07T10:08:47.568Z[GMT]")
@Controller
public class AdminsApiController implements AdminsApi {

    private static final Logger log = LoggerFactory.getLogger(AdminsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    @Autowired
    private UserRepository userRepo;

    @org.springframework.beans.factory.annotation.Autowired
    public AdminsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<User>> adminsGet(@NotNull @ApiParam(value = "The access token given to the authenticated user.", required = true) @Valid @RequestParam(value = "access_token", required = true) String accessToken
,@ApiParam(value = "The firstname of the admins to return.") @Valid @RequestParam(value = "firstname", required = false) String firstname
,@ApiParam(value = "The lastname of the admins to return.") @Valid @RequestParam(value = "lastname", required = false) String lastname
,@ApiParam(value = "The email of the admins to return.") @Valid @RequestParam(value = "email", required = false) String email
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            if(firstname != null && lastname != null && email != null){
                return new ResponseEntity<List<User>>(this.userRepo.findByPrivilegeAndFirstnameAndLastnameAndEmail(User.PrivilegeEnum.ADMIN, firstname, lastname, email), HttpStatus.OK);
            } else if(firstname != null && lastname != null){
                return new ResponseEntity<List<User>>(this.userRepo.findByPrivilegeAndFirstnameAndLastname(User.PrivilegeEnum.ADMIN, firstname, lastname), HttpStatus.OK);
            } else if (firstname != null && email != null){
                return new ResponseEntity<List<User>>(this.userRepo.findByPrivilegeAndFirstnameAndEmail(User.PrivilegeEnum.ADMIN, firstname, email), HttpStatus.OK);
            } else if (email != null && lastname != null){
                return new ResponseEntity<List<User>>(this.userRepo.findByPrivilegeAndLastnameAndEmail(User.PrivilegeEnum.ADMIN, lastname, email), HttpStatus.OK);
            } else if (email != null){
                return new ResponseEntity<List<User>>(this.userRepo.findByPrivilegeAndEmail(User.PrivilegeEnum.ADMIN, email), HttpStatus.OK);
            } else if (firstname != null){
                return new ResponseEntity<List<User>>(this.userRepo.findByPrivilegeAndFirstname(User.PrivilegeEnum.ADMIN, firstname), HttpStatus.OK);
            } else if (lastname != null){
                return new ResponseEntity<List<User>>(this.userRepo.findByPrivilegeAndLastname(User.PrivilegeEnum.ADMIN, lastname), HttpStatus.OK);
            } else {
                return new ResponseEntity<List<User>>(this.userRepo.findByPrivilege(User.PrivilegeEnum.ADMIN), HttpStatus.OK);
            }
        }

        return new ResponseEntity<List<User>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> adminsIdDelete(@ApiParam(value = "The ID of the admin to delete.",required=true) @PathVariable("id") Integer id
,@NotNull @ApiParam(value = "The access token given to the authenticated user.", required = true) @Valid @RequestParam(value = "access_token", required = true) String accessToken
) {
        User toDelete = this.userRepo.findByPrivilegeAndId(User.PrivilegeEnum.ADMIN, id);
        if(toDelete == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        this.userRepo.delete(id);
        
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<User> adminsIdGet(@ApiParam(value = "The ID of the admin to return.",required=true) @PathVariable("id") Integer id
,@NotNull @ApiParam(value = "The access token given to the authenticated user.", required = true) @Valid @RequestParam(value = "access_token", required = true) String accessToken
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            User user = this.userRepo.findByPrivilegeAndId(User.PrivilegeEnum.ADMIN, id);
            if(user == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            
            return new ResponseEntity<>(user, HttpStatus.OK);
            
        }

        return new ResponseEntity<User>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<User> adminsIdPut(@ApiParam(value = "" ,required=true )  @Valid @RequestBody User body
,@NotNull @ApiParam(value = "The access token given to the authenticated user.", required = true) @Valid @RequestParam(value = "access_token", required = true) String accessToken
,@ApiParam(value = "The ID of the admin to update.",required=true) @PathVariable("id") Integer id
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            User update = this.userRepo.findByPrivilegeAndId(User.PrivilegeEnum.ADMIN, id);
            
            if(update == null){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); 
            }
            
            body.setId(id);
            body.setPrivilege(User.PrivilegeEnum.ADMIN);
            update = this.userRepo.save(body);
            
            return new ResponseEntity<User>(update, HttpStatus.OK); 
        }

        return new ResponseEntity<User>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<User> adminsPost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody User body
,@NotNull @ApiParam(value = "The access token given to the authenticated user.", required = true) @Valid @RequestParam(value = "access_token", required = true) String accessToken
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            
            body.setId(null);
            body.setPrivilege(User.PrivilegeEnum.ADMIN);
            User newUser = this.userRepo.save(body);
            
            return new ResponseEntity<User>(newUser, HttpStatus.OK); 
        }

        return new ResponseEntity<User>(HttpStatus.NOT_IMPLEMENTED);
    }

}
