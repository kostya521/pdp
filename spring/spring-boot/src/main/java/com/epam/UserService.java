package com.epam;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class UserService {
    
    Logger logger = Logger.getLogger(UserService.class);

    @RequestMapping(value = "/createuser", method = RequestMethod.POST, produces = "application/json")
    public String createUser(@RequestParam("username") String userName, @RequestParam String password) {
        logger.info(String.format("createUser: username: %s", userName));
        return "";
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST, produces = "text/plain")
    public boolean auth(@RequestParam("username") String userName, @RequestParam String password) {
        logger.info(String.format("auth: username: %s", userName));
        return false;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(UserService.class);
        app.setShowBanner(true);
        app.run(args);
    }

}
