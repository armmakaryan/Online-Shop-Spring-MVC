package com.smartCode.springMvc.controller;

import com.smartCode.springMvc.model.User;
import com.smartCode.springMvc.service.user.UserService;
import com.smartCode.springMvc.util.constants.Parameter;
import com.smartCode.springMvc.util.constants.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    public UserService userService;

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam String email,
                              @RequestParam String password,
                              @RequestParam(required = false, defaultValue = "off") String remember_me,
                              HttpSession session) {

        try {
            userService.login(email, password);
            session.setAttribute(Parameter.EMAIL_PARAMETER, email);
            return new ModelAndView(Path.HOME_PATH);
        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView(Path.INDEX_PATH);
            modelAndView.addObject(Parameter.MESSAGE_ATTRIBUTE, e.getMessage());
            return modelAndView;
        }
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ModelAndView register(@RequestParam String name,
                                 @RequestParam String lastname,
                                 @RequestParam String email,
                                 @RequestParam String password,
                                 @RequestParam int age) {
        try {
            userService.register(name, lastname, email, password, age);
            return new ModelAndView(Path.HOME_PATH);
        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView(Path.REGISTER_PATH);
            modelAndView.addObject(Parameter.MESSAGE_ATTRIBUTE, e.getMessage());
            return modelAndView;
        }
    }

    @RequestMapping(path = "/Secure/changePassword", method = RequestMethod.POST)
    public ModelAndView changePassword(@RequestParam String email,
                                       @RequestParam String newPassword,
                                       @RequestParam String repeatPassword) {
        try {
            userService.changePassword(email, newPassword, repeatPassword);
            return new ModelAndView(Path.HOME_PATH);
        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView(Path.CHANGE_PASSWORD_PATH);
            modelAndView.addObject(Parameter.MESSAGE_ATTRIBUTE, e.getMessage());
            return modelAndView;
        }
    }

    @RequestMapping(path = ("/Secure/deleteAccount"))
    public ModelAndView deleteAccount(@RequestParam String email,
                                      @RequestParam String password) {
        try {
            userService.deleteAccount(email, password);
            return new ModelAndView(Path.INDEX_PATH);
        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView(Path.DELETE_ACCOUNT_PATH);
            modelAndView.addObject(Parameter.MESSAGE_ATTRIBUTE, e.getMessage());
            return modelAndView;
        }
    }
}
