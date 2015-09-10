package com.epam;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/createuser", method = RequestMethod.POST, produces = "application/json")
    public String createUser(@RequestParam("username") String userName, @RequestParam String password) {
        logger.info(String.format("createUser: username: %s", userName));

        if (userRepository.findByUserName(userName) != null) {
            return "user exists";
        }

        User user = new User(userName, password);
        userRepository.save(user);

        if (user.getId() > 0) {
            logger.info(String.format("user %s was saved with id: %s", user.getUserName(), user.getId()));
        }

        return "user created";
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST, produces = "application/json")
    public boolean auth(@RequestParam("username") String userName, @RequestParam String password) {
        logger.info(String.format("auth: username: %s", userName));

        User user = userRepository.findByUserName(userName);

        if (user != null) {
            if (user.getPassword().equals(password)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(UserService.class);
        app.setShowBanner(true);
        app.run(args);
    }

}
