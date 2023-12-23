package com.tn.quiz;

import com.tn.quiz.model.User;
import com.tn.quiz.model.Role;


import com.tn.quiz.model.UserRole;
import com.tn.quiz.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class QuizApplication  implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(QuizApplication.class, args);
	}

	@Autowired
	UserServiceImpl userservice;

	@Override
	public void run(String... args) throws Exception {


	}
}
