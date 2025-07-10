package com.cj.cjone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

// ci 테스트
@SpringBootApplication
@EnableFeignClients // Feign Client를 사용하도록 설정
public class CjoneApplication {

    public static void main(String[] args) {
        SpringApplication.run(CjoneApplication.class, args);
    }

}
