package com.tn.quiz.controller;

import com.tn.quiz.config.JwtHelper;
import com.tn.quiz.helper.UserFoundException;
import com.tn.quiz.model.*;
import com.tn.quiz.service.UserService;
import com.tn.quiz.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {
    @Autowired
    JwtHelper jwtHelper;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;
    @PostMapping("/signUp")
    public ResponseEntity<User> createUser(@RequestBody User ur) throws Exception {
        Set<UserRole> roles = new HashSet<UserRole>();
        Role role= new Role();
        role.setRoleId(2L);
        role.setRoleName("NORMAL");
        UserRole usr = new UserRole();
        usr.setRole(role);
        usr.setUser(ur);
        roles.add(usr);
        ur.setPassword(passwordEncoder.encode(ur.getPassword()));


        User newUser= this.userService.createUser(ur,roles);
        return ResponseEntity.ok(newUser);

    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
        System.out.println("Login In ");

        this.doAuthenticate(request.getUsername(), request.getPassword());
        System.out.println("check1");
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        System.out.println("check2");

        String token = this.jwtHelper.generateToken(userDetails);
        System.out.println("check3");

        JwtResponse response = JwtResponse.builder()
                .tokens(token)
//                .username(userDetails.getUsername())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal){
        return (User) this.userDetailsService.loadUserByUsername(principal.getName());

    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            authenticationManager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

    @ExceptionHandler(UserFoundException.class)
    public String UserFoundexception(UserFoundException ex){
        System.out.println("User Found............");
        return ex.toString();
    }





}
