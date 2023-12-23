package com.tn.quiz.service;

import com.tn.quiz.model.User;
import com.tn.quiz.model.UserRole;

import java.util.Set;

public interface UserService {
    public User createUser(User user, Set<UserRole> userRoles) throws Exception;
    public User getUser(String userName);
    public void deleteUser(Long userId);
}
