package uk.ac.brighton.uni.na3.controllers;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import uk.ac.brighton.uni.na3.model.networking.request.LoginRequest;
import uk.ac.brighton.uni.na3.model.networking.response.LoginResponse;

@Controller
@EnableAutoConfiguration
public class MainController {
    @RequestMapping("/")
    @ResponseBody
    String index() {
        return "Hello World!";
    }

    @PostMapping("/login")
    @ResponseBody
    LoginResponse login(@RequestBody LoginRequest login) {
        return null;
    }
}
