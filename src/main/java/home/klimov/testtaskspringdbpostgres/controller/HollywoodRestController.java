package home.klimov.testtaskspringdbpostgres.controller;

import home.klimov.testtaskspringdbpostgres.entity.Director;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class HollywoodRestController {

    private final AtomicLong counter = new AtomicLong();

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/director")
    public void saveDirector(
            @RequestParam(value = "first_name") String firstName,
            @RequestParam(value = "last_name") String lastName,
            @RequestParam(value = "birth_date") Date birth_date) {
        if (firstName != null && lastName != null && birth_date != null) {
            Director director = new Director(counter.incrementAndGet(), firstName, lastName, birth_date);
            director.setFirstName("aaa");
        }
//        try {
//            return ResponseEntity.ok(historyService.save(history));
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }
}
