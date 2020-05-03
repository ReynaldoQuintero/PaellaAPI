/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.swagger.repository;

import io.swagger.model.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Component;

/**
 *
 * @author reyna
 */
@Component
public interface UserRepository extends CrudRepository<User, Integer>, QueryByExampleExecutor<User>{
    List<User> findByPrivilege(User.PrivilegeEnum privilege);
    User findByEmail(String email);
    User findByPrivilegeAndId(User.PrivilegeEnum privilege, Integer id);
    List<User> findByPrivilegeAndFirstname(User.PrivilegeEnum privilege, String firstname);
    List<User> findByPrivilegeAndLastname(User.PrivilegeEnum privilege, String lastname);
    List<User> findByPrivilegeAndEmail(User.PrivilegeEnum privilege, String email);
    List<User> findByPrivilegeAndFirstnameAndLastname(User.PrivilegeEnum privilege, String firstname, String lastname);
    List<User> findByPrivilegeAndFirstnameAndEmail(User.PrivilegeEnum privilege, String firstname, String email);
    List<User> findByPrivilegeAndLastnameAndEmail(User.PrivilegeEnum privilege, String lastname, String email);
    List<User> findByPrivilegeAndFirstnameAndLastnameAndEmail(User.PrivilegeEnum privilege, String firstname, String lastname, String email);
}
