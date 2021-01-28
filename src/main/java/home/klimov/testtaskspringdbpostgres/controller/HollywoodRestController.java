package home.klimov.testtaskspringdbpostgres.controller;

import home.klimov.testtaskspringdbpostgres.Utils.ObjectToJson;
import home.klimov.testtaskspringdbpostgres.constants.Constants;
import home.klimov.testtaskspringdbpostgres.entity.Director;
import home.klimov.testtaskspringdbpostgres.service.DirectorService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@RestController
public class HollywoodRestController {

    private final DirectorService directorService;

    public HollywoodRestController(DirectorService directorService) {
        this.directorService = directorService;
    }

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
    public ResponseEntity<?> saveDirector(
            @RequestParam(value = "first_name") String firstName,
            @RequestParam(value = "last_name") String lastName,
            @RequestParam(value = "birth_date") Date birth_date) {
        try {
            Director director = new Director();
            director.setFirstName(firstName);
            director.setLastName(lastName);
            director.setBirthDate(birth_date);
            return ResponseEntity.ok(directorService.create(director));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/delete_director")
    public ResponseEntity<?> deleteDirector(
            @RequestParam(value = "id") long id) {
        try {
            return ResponseEntity.ok(directorService.delete(id));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/read_director")
    public ResponseEntity<?> readDirector(
            @RequestParam(value = "id") long id) {
        Optional<Director> director = directorService.read(id);
        return director.map(value -> new ResponseEntity<>(ObjectToJson.toJson(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(Constants.DIRECTOR_NOT_FOUND + id, HttpStatus.NOT_FOUND));
    }
}
