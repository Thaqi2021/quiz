package com.tn.quiz.controller;

import com.tn.quiz.helper.UserFoundException;
import com.tn.quiz.model.Role;
import com.tn.quiz.model.User;
import com.tn.quiz.model.UserRole;
import com.tn.quiz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @PostMapping("/save")
    public ResponseEntity<User> createUser(@RequestBody User ur) throws Exception {
        Set<UserRole> roles = new HashSet<UserRole>();
        Role role= new Role();
        role.setRoleId(2L);
        role.setRoleName("Normal");
        UserRole usr = new UserRole();
        usr.setRole(role);
        usr.setUser(ur);
        roles.add(usr);
        ur.setPassword(passwordEncoder.encode(ur.getPassword()));


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
