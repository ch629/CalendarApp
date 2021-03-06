package uk.ac.brighton.uni.na3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uk.ac.brighton.uni.na3.auth.AuthTokenManager;
import uk.ac.brighton.uni.na3.database.entities.UserAccount;
import uk.ac.brighton.uni.na3.database.services.interfaces.UserService;
import uk.ac.brighton.uni.na3.model.networking.request.LoginRequest;
import uk.ac.brighton.uni.na3.model.networking.request.RegisterRequest;
import uk.ac.brighton.uni.na3.model.networking.request.SingleDataRequest;
import uk.ac.brighton.uni.na3.model.networking.response.LoginResponse;
import uk.ac.brighton.uni.na3.model.networking.response.Response;
import uk.ac.brighton.uni.na3.model.networking.response.ResponseType;
import uk.ac.brighton.uni.na3.model.networking.response.SingleDataResponse;

import java.util.Arrays;

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
        UserAccount user = userService.findOne(login.getUsername());
        if (user != null) {
            if (Arrays.equals(login.getPassword(), user.getPassword())) {
                char[] authToken = AuthTokenManager.instance.generateAndUseAuthToken(user.getUsername());
                return new LoginResponse(ResponseType.OK, authToken);
            }
        }
        //Invalid username or password
        return new Response(ResponseType.BAD_REQUEST); //TODO: Check if BAD_REQUEST is correct, or UNAUTHORIZED
    }

    @GetMapping("/salt/{username}")
    @ResponseBody
    Response getSalt(@PathVariable("username") String username) {
        UserAccount user = userService.findOne(username);
        if (user == null) return new Response(ResponseType.NOT_FOUND);
        return new SingleDataResponse<>(user.getSalt());
    }

    @PostMapping("/register")
    @ResponseBody
    Response register(@RequestBody RegisterRequest request) {
        if (userService.findOne(request.getUsername()) == null) { //User doesn't exist
            UserAccount user = new UserAccount(request.getUsername(), "", "", "", "", "",
                    request.getPassword(), request.getSalt());
            userService.create(user);

            return new Response(ResponseType.OK);
        }
        return new Response(ResponseType.BAD_REQUEST); //TODO: More error types?
    }

    @PostMapping("/test")
    @ResponseBody
    Response test(@RequestBody SingleDataRequest<Integer> request) {
        if (request.getData() == 1) return new Response(ResponseType.OK);
        return new Response(ResponseType.BAD_REQUEST);
    }
}
