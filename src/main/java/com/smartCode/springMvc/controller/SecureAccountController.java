package com.smartCode.springMvc.controller;

import com.smartCode.springMvc.service.user.UserService;
import com.smartCode.springMvc.util.constants.Parameter;
import com.smartCode.springMvc.util.constants.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/secure")
public class SecureAccountController {

    @Autowired
    public UserService userService;

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session,
                         HttpServletResponse resp,
                         @CookieValue(name = Parameter.REMEMBER_COOKIE, required = false) Cookie cookie) {
        session.invalidate();
        if (cookie != null) {
            cookie.setMaxAge(0);
            resp.addCookie(cookie);
        }
        return Path.INDEX_PATH;
    }


    @RequestMapping(value = "/deleteAccount", method = RequestMethod.POST)
    public ModelAndView deleteAccount(@SessionAttribute(Parameter.EMAIL_PARAMETER) String email,
                                      @SessionAttribute(Parameter.PASSWORD_PARAMETER) String password) {
        try {
            userService.deleteAccount(email, password);
            return new ModelAndView(Path.INDEX_PATH);
        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView(Path.DELETE_ACCOUNT_PATH);
            modelAndView.addObject(Parameter.MESSAGE_ATTRIBUTE, e.getMessage());
            return modelAndView;
        }
    }

    @RequestMapping(value = "/secure/changePassword", method = RequestMethod.POST)
    public ModelAndView changePassword(@SessionAttribute(Parameter.EMAIL_PARAMETER) String email,
                                       @SessionAttribute(Parameter.NEW_PASSWORD_PARAMETER) String newPassword,
                                       @SessionAttribute(Parameter.REPEAT_PASSWORD_PARAMETER) String repeatPassword) {
        try {
            userService.changePassword(email, newPassword, repeatPassword);
            return new ModelAndView(Path.HOME_PATH);
        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView(Path.CHANGE_PASSWORD_PATH);
            modelAndView.addObject(Parameter.MESSAGE_ATTRIBUTE, e.getMessage());
            return modelAndView;
        }
    }
}