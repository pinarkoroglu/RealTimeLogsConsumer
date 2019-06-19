package com.pinar.consumer.Contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class KafkaController {
    @GetMapping("/home")
    public String home(Model model) {
        return "home";
    }

}
