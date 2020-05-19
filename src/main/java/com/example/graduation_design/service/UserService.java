package com.example.graduation_design.service;

import com.example.graduation_design.entity.User;
import com.example.graduation_design.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

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
          u.setPassword(newPwd);
          userRepository.save(u);
      }
      else log.debug("{}", "没有找到该用户");
    }
}