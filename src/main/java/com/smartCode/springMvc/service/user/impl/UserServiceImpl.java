package com.smartCode.springMvc.service.user.impl;

import com.smartCode.springMvc.exceptions.UserNotFoundException;
import com.smartCode.springMvc.exceptions.ValidationException;
import com.smartCode.springMvc.model.User;
import com.smartCode.springMvc.repository.UserRepository;
import com.smartCode.springMvc.service.user.UserService;
import am.smartCode.jdbc.util.constants.Message;
import com.smartCode.springMvc.util.encoder.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void register(@NotBlank String name,
                         @NotBlank String lastname,
                         @Email @NotBlank String email,
                         @Size(min = 8) @NotBlank String password,
                         @Positive int age) {
        if (userRepository.findByEmail(email) != null) {
            throw new ValidationException(Message.EMAIL_IS_NOT_AVAILABLE);
        }
        User user = new User();
        user.setName(name);
        user.setLastname(lastname);
        user.setEmail(email);
        user.setPassword(MD5Encoder.encode(password));
        user.setAge(age);
        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public void login(@Email @NotBlank String email,
                      @Size(min = 8) @NotBlank String password) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException(Message.USER_NOT_FOUND);
        }
        if (!Objects.equals(user.getPassword(), MD5Encoder.encode(password))) {
            throw new ValidationException(Message.INVALID_PASSWORD);
        }
    }

    @Override
    @Transactional
    public void deleteAccount(@Email @NotBlank String email,
                              @Size(min = 8) @NotBlank String password) {
        if (!userRepository.findByEmail(email).getPassword().equals(MD5Encoder.encode(password))) {
            throw new ValidationException(Message.INVALID_PASSWORD);
        }
        userRepository.delete(userRepository.findByEmail(email));
    }

    @Override
    @Transactional
    public void changePassword(@Email @NotBlank String email,
                               @Size(min = 8) @NotBlank String newPassword,
                               @Size(min = 8) @NotBlank String repeatPassword) {
        if (!Objects.equals(newPassword, repeatPassword)) {
            throw new RuntimeException("Passwords does not match");
        }
        User user = userRepository.findByEmail(email);
        user.setPassword(newPassword);
        userRepository.save(user);
    }
}