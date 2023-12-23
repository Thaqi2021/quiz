package com.tn.quiz.controller;

import com.tn.quiz.model.Role;
import com.tn.quiz.model.User;
import com.tn.quiz.model.UserRole;
import com.tn.quiz.service.UserService;
import com.tn.quiz.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping("/save")
    public ResponseEntity<User> createUser(@RequestBody User ur) throws Exception {
        Set<UserRole> roles = new HashSet<UserRole>();
        Role role= new Role();
        role.setRoleId(44L);
        role.setRoleName("ADMIN");
        UserRole usr = new UserRole();
        usr.setRole(role);
        usr.setUser(ur);
        roles.add(usr);


        User newUser= this.userService.createUser(ur,roles);
        return ResponseEntity.ok(newUser);

    }

    @GetMapping("/findUser/{userName}")
    public User getUserByUserName(@PathVariable("userName") String userName){
       return this.userService.getUser(userName);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        this.userService.deleteUser(userId);

    }
}
