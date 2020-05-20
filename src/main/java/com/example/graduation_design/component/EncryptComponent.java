package com.example.graduation_design.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class EncryptComponent {

    @Value("${my.secretkey}")
    private String mysecretKey;
    @Value("${my.salt}")
    private String mySalt;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TextEncryptor encryptor;

    @Bean
    public TextEncryptor getEncrypt() {
        return Encryptors.text(mysecretKey, mySalt);
    }

    /**
     * 无法加密token，程序有错
     *
     * @param myToken
     * @return
     */
    public String EncryptToken(MyToken myToken) {
        try {
            String json = objectMapper.writeValueAsString(myToken);
            return encryptor.encrypt(json);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "服务器错误");
        }
    }


    /**
     * 解密时身份信息auth被篡改，说明无权限
     * @param auth
     * @return
     */
    public MyToken DecryptToken(String auth) {
        try {
            String json = encryptor.decrypt(auth);
           return objectMapper.readValue(json, MyToken.class);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"无权限");
        }


    }
}