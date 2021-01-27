package home.klimov.testtaskspringdbpostgres.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HollywoodController {
    @GetMapping
    public String index() {
        return "index";
    }
}
