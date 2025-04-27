package com.jonssonyan;

import com.jonssonyan.model.dto.ReqDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@SpringBootApplication
@RestController
public class SpringBootValidationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootValidationApplication.class, args);
    }

    @PostMapping("signUp")
    private String signUp(@RequestBody @Valid ReqDto dto) {
        return "success";
    }

}
