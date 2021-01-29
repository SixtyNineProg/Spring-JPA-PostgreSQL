package home.klimov.testtaskspringdbpostgres.controller;

import home.klimov.testtaskspringdbpostgres.Utils.ObjectToJson;
import home.klimov.testtaskspringdbpostgres.constants.Constants;
import home.klimov.testtaskspringdbpostgres.entity.Director;
import home.klimov.testtaskspringdbpostgres.entity.Film;
import home.klimov.testtaskspringdbpostgres.service.DirectorService;
import home.klimov.testtaskspringdbpostgres.service.FilmService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class HollywoodRestController {

    private final DirectorService directorService;
    private final FilmService filmService;

    public HollywoodRestController(DirectorService directorService, FilmService filmService) {
        this.directorService = directorService;
        this.filmService = filmService;
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
            List<Director> directors = directorService.read(firstName, lastName, birth_date);
            if (!directors.isEmpty()) {
                return new ResponseEntity<>(Constants.DIRECTOR_ALREADY_EXIST + directors.get(0).getId(), HttpStatus.OK);
            } else {
                Director director = new Director();
                director.setFirstName(firstName);
                director.setLastName(lastName);
                director.setBirthDate(birth_date);
                return ResponseEntity.ok(directorService.create(director));
            }
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

    @PostMapping("/read_director_by_id")
    public ResponseEntity<?> readDirectorById(
            @RequestParam(value = "id") long id) {
        Optional<Director> director = directorService.readById(id);
        return director.map(value -> new ResponseEntity<>(ObjectToJson.toJson(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(Constants.DIRECTOR_NOT_FOUND_ID + id, HttpStatus.NOT_FOUND));
    }

    @PostMapping("/read_director")
    public ResponseEntity<?> readDirector(
            @RequestParam(value = "first_name") String firstName,
            @RequestParam(value = "last_name") String lastName,
            @RequestParam(value = "birth_date") Date birth_date) {
        Director director = new Director();
        director.setFirstName(firstName);
        director.setLastName(lastName);
        director.setBirthDate(birth_date);
        List<Director> directors = directorService.read(director);
        if (!directors.isEmpty()) {
            return ResponseEntity.ok(ObjectToJson.toJson(directors.get(0).getId()));
        } else return new ResponseEntity<>(Constants.DIRECTOR_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/film")
    public ResponseEntity<?> saveFilm(
            @RequestParam(value = "first_name") String firstName,
            @RequestParam(value = "last_name") String lastName,
            @RequestParam(value = "birth_date") Date birth_date,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "release_date") Date release_date,
            @RequestParam(value = "genre") String genre) {
        try {
            List<Director> directors = directorService.read(firstName, lastName, birth_date);
            if (!directors.isEmpty()) {
                Film film = new Film();
                film.setDirector(directors.get(0));
                film.setName(name);
                film.setReleaseDate(release_date);
                film.setGenre(genre);
                return ResponseEntity.ok(filmService.create(film));
            } else {
                Director director = new Director();
                director.setFirstName(firstName);
                director.setLastName(lastName);
                director.setBirthDate(birth_date);
                Film film = new Film();
                film.setDirector(director);
                film.setName(name);
                film.setReleaseDate(release_date);
                film.setGenre(genre);
                return ResponseEntity.ok(filmService.create(film));
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/delete_film")
    public ResponseEntity<?> deleteFilm(
            @RequestParam(value = "id") long id) {
        try {
            return ResponseEntity.ok(filmService.delete(id));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/read_film")
    public ResponseEntity<?> readFilm(
            @RequestParam(value = "id") long id) {
        Optional<Film> film = filmService.read(id);
        return film.map(value -> new ResponseEntity<>(value.toString(), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(Constants.FILM_NOT_FOUND_ID + id, HttpStatus.NOT_FOUND));
    }
}
