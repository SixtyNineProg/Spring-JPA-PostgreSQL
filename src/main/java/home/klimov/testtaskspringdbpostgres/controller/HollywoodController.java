package home.klimov.testtaskspringdbpostgres.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HollywoodController {

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/create_director")
    public String createDirector() {
        return "create_director";
    }

    @GetMapping("/delete_director")
    public String deleteDirector() {
        return "delete_director";
    }

    @GetMapping("/read_director")
    public String readDirector() {
        return "read_director";
    }

    @GetMapping("/create_film")
    public String createFilm() {
        return "create_film";
    }

    @GetMapping("/delete_film")
    public String deleteFilm() {
        return "delete_film";
    }

    @GetMapping("/read_film")
    public String readFilm() {
        return "read_film";
    }
}
