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
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-07T10:08:47.568Z[GMT]")
@Controller
public class CustomersApiController implements CustomersApi {

    private static final Logger log = LoggerFactory.getLogger(CustomersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    @Autowired
    private UserRepository userRepo;

    @org.springframework.beans.factory.annotation.Autowired
    public CustomersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<User>> customersGet(@NotNull @ApiParam(value = "The access token given to the authenticated user.", required = true) @Valid @RequestParam(value = "access_token", required = true) String accessToken
,@ApiParam(value = "The firstname of the customers to return.") @Valid @RequestParam(value = "firstname", required = false) String firstname
,@ApiParam(value = "The lastname of the customers to return.") @Valid @RequestParam(value = "lastname", required = false) String lastname
,@ApiParam(value = "The email of the customers to return.") @Valid @RequestParam(value = "email", required = false) String email
) {
        Authentication authentication = SecurityContextHolder.getContext()
        .getAuthentication();
        boolean flag = false;
        for(GrantedAuthority authority : authentication.getAuthorities()){
            if("ADMIN".equals(authority.getAuthority())){
                flag = true;
                break;
            }
        }
        if(!flag){
            return new ResponseEntity<List<User>>(HttpStatus.UNAUTHORIZED);
        }
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<List<User>>(userRepo.findByPrivilege(User.PrivilegeEnum.BASIC), HttpStatus.OK);
        }

        return new ResponseEntity<List<User>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> customersIdDelete(@ApiParam(value = "The ID of the customer to delete.",required=true) @PathVariable("id") Integer id
,@NotNull @ApiParam(value = "The access token given to the authenticated user.", required = true) @Valid @RequestParam(value = "access_token", required = true) String accessToken
) {
        User toDelete = this.userRepo.findByPrivilegeAndId(User.PrivilegeEnum.BASIC, id);
        if(toDelete == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        Authentication authentication = SecurityContextHolder.getContext()
        .getAuthentication();
        boolean flag = false;
        for(GrantedAuthority authority : authentication.getAuthorities()){
            if("ADMIN".equals(authority.getAuthority())){
                flag = true;
                break;
            }
        }
        if(toDelete.getEmail().equals(authentication.getName())){
            flag = true;
        }
        
        if(!flag){
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        }
        
        this.userRepo.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<User> customersIdGet(@ApiParam(value = "The ID of the customer to return.",required=true) @PathVariable("id") Integer id
,@NotNull @ApiParam(value = "The access token given to the authenticated user.", required = true) @Valid @RequestParam(value = "access_token", required = true) String accessToken
) {
        String accept = request.getHeader("Accept");
        
        User user = this.userRepo.findByPrivilegeAndId(User.PrivilegeEnum.BASIC, id);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        Authentication authentication = SecurityContextHolder.getContext()
        .getAuthentication();
        boolean flag = false;
        for(GrantedAuthority authority : authentication.getAuthorities()){
            if("ADMIN".equals(authority.getAuthority())){
                flag = true;
                break;
            }
        }
        if(user.getEmail().equals(authentication.getName())){
            flag = true;
        }
        
        if(!flag){
            return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
        }
        
        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<User>(user, HttpStatus.NOT_IMPLEMENTED);
        }

        return new ResponseEntity<User>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<User> customersIdPut(@ApiParam(value = "" ,required=true )  @Valid @RequestBody User body
,@NotNull @ApiParam(value = "The access token given to the authenticated user.", required = true) @Valid @RequestParam(value = "access_token", required = true) String accessToken
,@ApiParam(value = "The ID of the customer to update.",required=true) @PathVariable("id") Integer id
) {
        
        User toUpdate = this.userRepo.findByPrivilegeAndId(User.PrivilegeEnum.BASIC, id);
        if(toUpdate == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        Authentication authentication = SecurityContextHolder.getContext()
        .getAuthentication();
        boolean flag = false;
        for(GrantedAuthority authority : authentication.getAuthorities()){
            if("ADMIN".equals(authority.getAuthority())){
                flag = true;
                break;
            }
        }
        if(toUpdate.getEmail().equals(authentication.getName())){
            flag = true;
        }
        
        if(!flag){
            return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
        }
        
        body.setId(id);
        body.setPrivilege(User.PrivilegeEnum.BASIC);
        toUpdate = this.userRepo.save(body);
        String accept = request.getHeader("Accept");
        
        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<User>(toUpdate, HttpStatus.OK);
        }

        return new ResponseEntity<User>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<User> customersPost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody User body
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            
            body.setId(null);
            body.setPrivilege(User.PrivilegeEnum.BASIC);
            User newUser = this.userRepo.save(body);
            
            return new ResponseEntity<User>(newUser, HttpStatus.OK); 
        }

        return new ResponseEntity<User>(HttpStatus.NOT_IMPLEMENTED);
    }

}
