package com.smartCode.springMvc.service.user;


import com.smartCode.springMvc.model.User;

public interface UserService {

    void register(String name, String lastname, String email, String password, int age);


    void login(String email, String password);

    void changePassword(String email, String newPassword, String repeatPassword);

    void deleteAccount(String email, String password);
}