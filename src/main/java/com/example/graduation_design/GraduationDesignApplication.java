package com.example.graduation_design;

import com.example.graduation_design.repository.Impl.baseRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableJpaRepositories(repositoryBaseClass = baseRepositoryImpl.class)
@SpringBootApplication
public class GraduationDesignApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraduationDesignApplication.class, args);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return  new BCryptPasswordEncoder();
    }

}
