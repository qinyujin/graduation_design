package com.example.graduation_design.repository;

import com.example.graduation_design.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.object.UpdatableSqlQuery;

public interface UserRepository extends baseRepository<User, Integer> {
    @Modifying
    @Query("update User u set  u.name=?1,u.num=?2,u.password=?3,u.role=?4 where  u.id=?5")
    void updateUser(String name, String num, String password, User.Role role,int id);

    User findByNum(String num);
}
