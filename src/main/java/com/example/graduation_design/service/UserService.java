package com.example.graduation_design.service;

import com.example.graduation_design.entity.Admin;
import com.example.graduation_design.entity.Teacher;
import com.example.graduation_design.entity.User;
import com.example.graduation_design.repository.UserRepository;
import com.example.graduation_design.repository.adminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private adminRepository adminRepository;

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByNum(String num){
        return  userRepository.findByNum(num);
    }
}