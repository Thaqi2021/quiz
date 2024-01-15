package com.tn.quiz.service.impl;

import com.tn.quiz.model.User;
import com.tn.quiz.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User sr =userRepository.findByUsername(username);
        if(sr==null){
            System.out.println("User Not Found ...");
            throw new UsernameNotFoundException("No User Found");
        }
        System.out.println(sr.toString());

        return sr;
    }
}
