package com.tn.quiz.helper;


public class UserFoundException extends Exception {
    public UserFoundException(){
        super("User Already Exists ");
    }
    public UserFoundException(String msg){
        super(msg);
    }
}
