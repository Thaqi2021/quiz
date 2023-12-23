package com.tn.quiz.service.impl;

import com.tn.quiz.model.User;
import com.tn.quiz.model.UserRole;
import com.tn.quiz.repo.RoleRepository;
import com.tn.quiz.repo.UserRepository;
import com.tn.quiz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
        User usr =this.userRepository.findByUsername(user.getUsername());
        if(usr!=null){
            System.out.println("User Already Exists !!");
            throw new Exception("User already present !!");
        }
        else{
            for(UserRole ur:userRoles){
                roleRepository.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            usr=this.userRepository.save(user);

        }
        return usr;
    }

    @Override
    public User getUser(String userName) {
        return this.userRepository.findByUsername(userName);
    }

    @Override
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }
}
