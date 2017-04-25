package uk.ac.brighton.uni.na3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uk.ac.brighton.uni.na3.database.services.interfaces.EventService;
import uk.ac.brighton.uni.na3.database.services.interfaces.UserService;

@SpringBootApplication
public class Application {
    public static Application instance;
    public UserService userService;
    public EventService eventService;

    @Autowired
    public Application(UserService userService, EventService eventService) {
        this.userService = userService;
        this.eventService = eventService;
        instance = this;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

//NOTE: Probably will need separate model classes just for the server, as we don't want to send all data to the clients & so we can just use annotated spring data classes
//NOTE: Just convert these to the client ones when sending