package uk.ac.brighton.uni.na3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import uk.ac.brighton.uni.na3.auth.AuthTokenManager;
import uk.ac.brighton.uni.na3.database.entities.User;
import uk.ac.brighton.uni.na3.database.services.interfaces.UserService;
import uk.ac.brighton.uni.na3.model.networking.request.LoginRequest;
import uk.ac.brighton.uni.na3.model.networking.response.LoginResponse;
import uk.ac.brighton.uni.na3.model.networking.response.Response;
import uk.ac.brighton.uni.na3.model.networking.response.ResponseType;

@Controller("/")
@EnableAutoConfiguration
public class MainController {
    private final UserService userService;

    @Autowired
    public MainController(UserService service) {
        this.userService = service;
    }

    @RequestMapping("/")
    @ResponseBody
    String index() {
        return "Hello World!";
    }

    @PostMapping("/login")
    @ResponseBody
    Response login(@RequestBody LoginRequest login) {
        User user = userService.findOne(login.getUsername());
        if (user != null) {
            if (login.getPassword() == user.getPassword()) {
                char[] authToken = AuthTokenManager.instance.generateAndUseAuthToken(user.getUsername());
                return new LoginResponse(ResponseType.OK, authToken);
            }
        }
        //Invalid username or password
        return new Response(ResponseType.BAD_REQUEST); //TODO: Check if BAD_REQUEST is correct, or UNAUTHORIZED
    }
}
