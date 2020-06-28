package com.example.graduation_design.controller;

import com.example.graduation_design.component.MyToken;
import com.example.graduation_design.entity.User;
import com.example.graduation_design.entity.directions;
import com.example.graduation_design.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
@Slf4j
public class userController {

    @Autowired
    private UserService userService;


    @Autowired
    private PasswordEncoder encoder;

    @PatchMapping("updatePwd")
    public String updatePwd(HttpServletRequest request, @RequestAttribute(MyToken.UID) int uid, @RequestBody User user) {

        User u = userService.getUserById(uid);//先根据num查询数据库中的用户
        //再判断密码是否相同，相同则不给更改
        if (!encoder.matches(user.getPassword(), u.getPassword())) {
            userService.updatePwd(u.getId(), user.getPassword());
            return "更新成功";
        } else return "密码不能与原始密码相同";
    }

    /**
     * 忘记的时候重置密码
     * @param user
     * @return
     */
    @PatchMapping("resetPassword")
    public Map resetPassword(@RequestBody User user){
       int uid=userService.getUserByNum(user.getNum()).getId();
        log.debug("uid:{}", uid);
        userService.updatePwd(uid, String.valueOf(user.getNum()));
        return Map.of("password",user.getNum());
    }

    @GetMapping("directions")
    public Map getDirections() {
        List<directions> directionsList = userService.getDirections();

        return Map.of("directionsList",directionsList);
    }
}