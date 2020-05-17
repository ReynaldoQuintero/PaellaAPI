package io.swagger.api;

import io.swagger.model.Order;
import io.swagger.model.RestError;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.model.Food;
import io.swagger.model.FoodOrder;
import io.swagger.model.IngredientFood;
import io.swagger.model.Order.StatusEnum;
import io.swagger.model.User;
import io.swagger.repository.FoodOrderRepository;
import io.swagger.repository.FoodRepository;
import io.swagger.repository.OrderRepository;
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
import org.springframework.data.domain.Example;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-07T10:08:47.568Z[GMT]")
@Controller
public class OrdersApiController implements OrdersApi {

    private static final Logger log = LoggerFactory.getLogger(OrdersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    @Autowired
    private OrderRepository orderRepo;
    @Autowired
    private FoodRepository foodRepo;
    @Autowired
    private FoodOrderRepository foodOrderRepo;
    @Autowired
    private UserRepository userRepo;

    @org.springframework.beans.factory.annotation.Autowired
    public OrdersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<Order>> ordersGet(@NotNull @ApiParam(value = "The access token given to the authenticated user.", required = true) @Valid @RequestParam(value = "access_token", required = true) String accessToken
,@ApiParam(value = "The email of the customer whose orders will be returned.") @Valid @RequestParam(value = "email", required = false) String email
,@ApiParam(value = "The status of the orders to return.") @Valid @RequestParam(value = "status", required = false) String status
,@ApiParam(value = "The name of the food to filter by.") @Valid @RequestParam(value = "foodName", required = false) String foodName
,@ApiParam(value = "The name of the client to filter by.") @Valid @RequestParam(value = "clientName", required = false) String clientName
,@ApiParam(value = "The last name of the client to filter by.") @Valid @RequestParam(value = "clientLastName", required = false) String clientLastName
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            List<Integer> userIds = new ArrayList();
            List<Integer> orderIds = new ArrayList();
            
            if(email != null || clientName != null || clientLastName != null){
                User user = new User();
                user.setEmail(email);
                user.setFirstname(clientName);
                user.setLastname(clientLastName);
                Example<User> exampleUser = Example.of(user);
                Iterable<User> users = this.userRepo.findAll(exampleUser);
                for (User u : users) {
                    userIds.add(u.getId());
                }
            }   
            if(foodName != null){
                List<Food> foods = this.foodRepo.findByName(foodName);
                List<Integer> foodIds = new ArrayList();
                for (Food f : foods) {
                    foodIds.add(f.getFoodId());
                }
                if(foodIds.size() > 0){
                    List<FoodOrder> foodOrders = this.foodOrderRepo.findByFoodIdIn(foodIds);
                    for (FoodOrder fo : foodOrders) {
                        orderIds.add(fo.getOrderId());
                    }
                }
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

            List<Order> orders = null;

            if(orderIds.size() > 0 && status != null && userIds.size() > 0){
                orders = this.orderRepo.findByIdInAndUserIDInAndStatus(orderIds, userIds, StatusEnum.valueOf(status));
            } else if (orderIds.size() > 0 && userIds.size() > 0){
                orders = this.orderRepo.findByIdInAndUserIDIn(orderIds, userIds);
            } else if (orderIds.size() > 0 && status != null){
                orders = this.orderRepo.findByIdInAndStatus(orderIds, StatusEnum.valueOf(status));
            } else if (userIds.size() > 0 && status != null){
                orders = this.orderRepo.findByUserIDInAndStatus(userIds, StatusEnum.valueOf(status));
            } else if (orderIds.size() > 0){
                orders = this.orderRepo.findByIdIn(orderIds);
            } else if (userIds.size() > 0){
                orders = this.orderRepo.findByUserIDIn(userIds);
            } else if (status != null){
                orders = this.orderRepo.findByStatus(StatusEnum.valueOf(status));
            } else {
                orders = this.orderRepo.findAll();
            }

            User auth = this.userRepo.findByEmail(authentication.getName());

            List<Order> finalOrders = new ArrayList();

            for(Order ord : orders){
                if(!ord.getUserID().equals(auth.getId()) && !flag){
                    continue;
                } else {
                    finalOrders.add(ord);
                }
                if(ord.getStatus().equals(StatusEnum.INCART)){
                    List<FoodOrder> fo = this.foodOrderRepo.findByOrderId(ord.getId());
                    BigDecimal total = BigDecimal.ZERO;
                    for (int i = 0; i < fo.size(); i++) {
                        Food food = this.foodRepo.getOne(fo.get(i).getFoodId());
                        BigDecimal subtotal = BigDecimal.ZERO;
                        subtotal = subtotal.add(food.getPrice().multiply(new BigDecimal(fo.get(i).getQuantity())));
                        fo.get(i).setSubTotal(subtotal);
                        this.foodOrderRepo.save(fo.get(i));
                        total = total.add(fo.get(i).getSubTotal());
                    }
                    ord.setTotal(total);
                    this.orderRepo.save(ord);
                }
            }    
            
            return new ResponseEntity<List<Order>>(finalOrders, HttpStatus.OK);
            
        }

        return new ResponseEntity<List<Order>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> ordersIdDelete(@ApiParam(value = "The ID of the order to delete.",required=true) @PathVariable("id") Integer id
,@NotNull @ApiParam(value = "The access token given to the authenticated user.", required = true) @Valid @RequestParam(value = "access_token", required = true) String accessToken
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
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        }
        Order toDelete = this.orderRepo.findOne(id);
        if(toDelete == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        this.orderRepo.delete(id);
        
        this.foodOrderRepo.deleteByOrderId(id);
        
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Order> ordersIdGet(@ApiParam(value = "The ID of the order to return.",required=true) @PathVariable("id") Integer id
,@NotNull @ApiParam(value = "The access token given to the authenticated user.", required = true) @Valid @RequestParam(value = "access_token", required = true) String accessToken
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            Order order = this.orderRepo.findOne(id);
            if(order == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            
            User user = this.userRepo.findOne(order.getUserID());
            
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
                return new ResponseEntity<Order>(HttpStatus.UNAUTHORIZED);
            }
            
            if(order.getStatus() == StatusEnum.INCART){
                List<FoodOrder> fo = this.foodOrderRepo.findByOrderId(order.getId());
                BigDecimal total = BigDecimal.ZERO;
                for (int i = 0; i < fo.size(); i++) {
                    Food food = this.foodRepo.getOne(fo.get(i).getFoodId());
                    BigDecimal subtotal = BigDecimal.ZERO;
                    subtotal = subtotal.add(food.getPrice().multiply(new BigDecimal(fo.get(i).getQuantity())));
                    fo.get(i).setSubTotal(subtotal);
                    this.foodOrderRepo.save(fo.get(i));
                    total = total.add(fo.get(i).getSubTotal());
                }
                order.setTotal(total);
                orderRepo.save(order);
            }
            
            return new ResponseEntity<>(order, HttpStatus.OK);
            
        }

        return new ResponseEntity<Order>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Order> ordersIdPut(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Order body
,@NotNull @ApiParam(value = "The access token given to the authenticated user.", required = true) @Valid @RequestParam(value = "access_token", required = true) String accessToken
,@ApiParam(value = "The ID of the order to update.",required=true) @PathVariable("id") Integer id
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            Order update = this.orderRepo.findOne(id);
            
            if(update == null){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); 
            }
            
            User user = this.userRepo.findOne(update.getUserID());
            
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
                return new ResponseEntity<Order>(HttpStatus.UNAUTHORIZED);
            }
            
            body.setId(id);
            if(body.getStatus() == StatusEnum.INCART){
                List<FoodOrder> fo = this.foodOrderRepo.findByOrderId(body.getId());
                BigDecimal total = BigDecimal.ZERO;
                for (int i = 0; i < fo.size(); i++) {
                    Food food = this.foodRepo.getOne(fo.get(i).getFoodId());
                    BigDecimal subtotal = BigDecimal.ZERO;
                    subtotal = subtotal.add(food.getPrice().multiply(new BigDecimal(fo.get(i).getQuantity())));
                    fo.get(i).setSubTotal(subtotal);
                    this.foodOrderRepo.save(fo.get(i));
                    total = total.add(fo.get(i).getSubTotal());
                }
                body.setTotal(total);
            }
            update = this.orderRepo.save(body);
            
            return new ResponseEntity<Order>(update, HttpStatus.OK); 
        }

        return new ResponseEntity<Order>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Order> ordersPost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Order body
,@NotNull @ApiParam(value = "The access token given to the authenticated user.", required = false) @Valid @RequestParam(value = "access_token", required = false) String accessToken
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            
            body.setId(null);
            
            User user = this.userRepo.findOne(body.getUserID());
            Authentication authentication = SecurityContextHolder.getContext()
            .getAuthentication();
            boolean flag = false;
            for(GrantedAuthority authority : authentication.getAuthorities()){
                if("ADMIN".equals(authority.getAuthority())){
                    flag = true;
                    break;
                }
            }
            System.out.println(user.getEmail() + " : " + authentication.getName());
            if(user.getEmail().equals(authentication.getName())){
                flag = true;
            }
            if(!flag){
                return new ResponseEntity<Order>(HttpStatus.UNAUTHORIZED);
            }
            body.setTotal(BigDecimal.ZERO);
            Order newOrder = this.orderRepo.save(body);
            
            return new ResponseEntity<Order>(newOrder, HttpStatus.OK); 
        }

        return new ResponseEntity<Order>(HttpStatus.NOT_IMPLEMENTED);
    }
    
    public ResponseEntity<List<FoodOrder>> foodOrdersGet(@NotNull @ApiParam(value = "The access token given to the authenticated user.", required = true) @Valid @RequestParam(value = "access_token", required = true) String accessToken
,@ApiParam(value = "The id of the order whose foodorders will be returned.") @Valid @RequestParam(value = "orderId", required = false) Integer orderId
){
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            
            if(orderId != null){
                
                Order order = this.orderRepo.findOne(orderId);
                
                Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
                User currentUser = this.userRepo.findByEmail(authentication.getName());
                boolean flag = false;
                for(GrantedAuthority authority : authentication.getAuthorities()){
                    if("ADMIN".equals(authority.getAuthority())){
                        flag = true;
                        break;
                    }
                }
                if(order.getUserID() == currentUser.getId()){
                    flag = true;
                }
                if(!flag){
                    return new ResponseEntity<List<FoodOrder>>(HttpStatus.UNAUTHORIZED);
                }
                
                
                return new ResponseEntity<List<FoodOrder>>(this.foodOrderRepo.findByOrderId(orderId), HttpStatus.OK);
                
            } else {
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
                    return new ResponseEntity<List<FoodOrder>>(HttpStatus.UNAUTHORIZED);
                }
                
                return new ResponseEntity<List<FoodOrder>>(this.foodOrderRepo.findAll(), HttpStatus.OK);
            }
            
            
            
            
        }

        return new ResponseEntity<List<FoodOrder>>(HttpStatus.NOT_IMPLEMENTED);
    }
    
    public ResponseEntity<FoodOrder> foodOrdersIdGet(@ApiParam(value = "The ID of the FoodOrder to return.",required=true) @PathVariable("id") Integer id
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            FoodOrder foodOrder = this.foodOrderRepo.findOne(id);
            if(foodOrder == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            
            Order order = this.orderRepo.findOne(foodOrder.getOrderId());
            
            if(order != null && order.getStatus() == StatusEnum.INCART){
                Food food = this.foodRepo.getOne(foodOrder.getFoodId());
                BigDecimal total = BigDecimal.ZERO;
                total = total.add(food.getPrice().multiply(new BigDecimal(foodOrder.getQuantity())));
                foodOrder.setSubTotal(total);
                this.foodOrderRepo.save(foodOrder);
            }
            
            return new ResponseEntity<>(foodOrder, HttpStatus.OK);
            
        }

        return new ResponseEntity<FoodOrder>(HttpStatus.NOT_IMPLEMENTED);
    }
    
    public ResponseEntity<FoodOrder> foodOrdersPost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody FoodOrder body
,@NotNull @ApiParam(value = "The access token given to the authenticated user.", required = false) @Valid @RequestParam(value = "access_token", required = false) String accessToken
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            
            body.setId(null);
            System.out.println("Fetching order: "+body.getOrderId());
            Order order = orderRepo.findOne(body.getOrderId());
            System.out.println("Found order: "+order.toString());
            User user = this.userRepo.findOne(order.getUserID());
            
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
                return new ResponseEntity<FoodOrder>(HttpStatus.UNAUTHORIZED);
            }
            
            
            Food food = this.foodRepo.getOne(body.getFoodId());
            BigDecimal total = BigDecimal.ZERO;
            System.out.println("Calculating sub total: "+food.getPrice()+" * "+new BigDecimal(body.getQuantity()));
            total = total.add(food.getPrice().multiply(new BigDecimal(body.getQuantity())));
            body.setSubTotal(total);
            
            FoodOrder newOrder = this.foodOrderRepo.save(body);
            
            return new ResponseEntity<FoodOrder>(newOrder, HttpStatus.OK); 
        }

        return new ResponseEntity<FoodOrder>(HttpStatus.NOT_IMPLEMENTED);
    }
    
    public ResponseEntity<FoodOrder> foodOrdersIdPut(@ApiParam(value = "" ,required=true )  @Valid @RequestBody FoodOrder body
,@NotNull @ApiParam(value = "The access token given to the authenticated user.", required = false) @Valid @RequestParam(value = "access_token", required = false) String accessToken,
@ApiParam(value = "The ID of the foodOrder to update.",required=true) @PathVariable("id") Integer id
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            FoodOrder update = this.foodOrderRepo.findOne(id);
            
            if(update == null){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); 
            }
            
            body.setId(id);
            Order order = this.orderRepo.findOne(body.getOrderId());
            
            User user = this.userRepo.findOne(order.getUserID());
            
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
                return new ResponseEntity<FoodOrder>(HttpStatus.UNAUTHORIZED);
            }
            
            if(order != null && order.getStatus() == StatusEnum.INCART){
                Food food = this.foodRepo.getOne(body.getFoodId());
                BigDecimal total = BigDecimal.ZERO;
                total = total.add(food.getPrice().multiply(new BigDecimal(body.getQuantity())));
                body.setSubTotal(total);
            }
            update = this.foodOrderRepo.save(body);
            
            return new ResponseEntity<FoodOrder>(update, HttpStatus.OK); 
        }

        return new ResponseEntity<FoodOrder>(HttpStatus.NOT_IMPLEMENTED);
    }
    
    public ResponseEntity<Void> foodOrdersIdDelete(@ApiParam(value = "The ID of the order to delete.",required=true) @PathVariable("id") Integer id
,@NotNull @ApiParam(value = "The access token given to the authenticated user.", required = false) @Valid @RequestParam(value = "access_token", required = false) String accessToken
) {
        FoodOrder toDelete = this.foodOrderRepo.findOne(id);
        if(toDelete == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        Order order = this.orderRepo.findOne(toDelete.getOrderId());
            
        User user = this.userRepo.findOne(order.getUserID());

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
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        }
        
        this.foodOrderRepo.delete(id);
        
        if(order != null){
            List<FoodOrder> fo = this.foodOrderRepo.findByOrderId(order.getId());
            BigDecimal total = BigDecimal.ZERO;
            for (int i = 0; i < fo.size(); i++) {
                Food food = this.foodRepo.getOne(fo.get(i).getFoodId());
                BigDecimal subtotal = BigDecimal.ZERO;
                subtotal = subtotal.add(food.getPrice().multiply(new BigDecimal(fo.get(i).getQuantity())));
                fo.get(i).setSubTotal(subtotal);
                this.foodOrderRepo.save(fo.get(i));
                total = total.add(fo.get(i).getSubTotal());
            }
            order.setTotal(total);
            this.orderRepo.save(order);
        }
        
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
