package org.example.web.controllers;

import org.apache.log4j.Logger;
import org.example.app.services.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/sign_up_page")
public class SingUpController {
    private final Logger logger = Logger.getLogger(LoginController.class);
    private SignUpService signUpService;

    @Autowired
    public SingUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @GetMapping("/create")
    public String user(Model model) {
        logger.info("new User");
        return "sign_up_page";
    }

    @PostMapping("/add")
    public String saveBook(@RequestParam(value = "user") String name,
                             @RequestParam(value = "password") String password,
                             @RequestParam(value = "password2") String password2) {
        if (signUpService.addUser(name, password, password2)) {
            return "redirect:/login";
        } else {
            return "sign_up_page";
        }
    }

}
