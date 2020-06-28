package com.example.graduation_design.service;

import com.example.graduation_design.entity.User;
import com.example.graduation_design.entity.directions;
import com.example.graduation_design.repository.UserRepository;
import com.example.graduation_design.repository.directionsRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private directionsRepository directionsRepository;
    @Autowired
    private PasswordEncoder encoder;

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByNum(String num){
        return  userRepository.findByNum(num);
    }

    /**
     * 在没有使用bcrypt加密算法之前的更改密码，加入brypt之后再进行更改
     * @param uid
     * @param newPwd
     */
    public void updatePwd(int uid,String newPwd){
      User u=  userRepository.findById(uid).orElse(null);
      if(u!=null){
          u.setPassword(encoder.encode(newPwd));
          userRepository.save(u);
      }
      else log.debug("{}", "没有找到该用户");
    }

    public List<directions> getDirections(){
        return directionsRepository.findAll();
    }
}