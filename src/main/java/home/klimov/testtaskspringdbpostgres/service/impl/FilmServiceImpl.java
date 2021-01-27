package home.klimov.testtaskspringdbpostgres.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import home.klimov.testtaskspringdbpostgres.constants.Constants;
import home.klimov.testtaskspringdbpostgres.entity.Film;
import home.klimov.testtaskspringdbpostgres.repository.FilmRepository;
import home.klimov.testtaskspringdbpostgres.service.FilmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;
    private final ObjectMapper objectMapper;

    public FilmServiceImpl(FilmRepository filmRepository, ObjectMapper objectMapper) {
        this.filmRepository = filmRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public long create(Film film) {
        filmRepository.save(film);
        log.info(Constants.FILM_SAVED, toJson(film));
        return film.getId();
    }

    @Override
    public Optional<Film> read(long id) {
        Optional<Film> film = filmRepository.findById(id);
        film.ifPresent(data -> log.info(Constants.FILM_RECEIVED, id, toJson(film)));
        return Optional.empty();
    }

    @Override
    public Boolean update(Film film) {
        return null;
    }

    @Override
    public Boolean delete(long id) {
        filmRepository.deleteById(id);
        log.info(Constants.FILM_DELETED, id);
        return true;
    }

    private synchronized String toJson(Object o) {
        String json = null;
        try {
            json = objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            log.warn(Constants.ERROR_PARSING_OF_OBJECT, o.getClass().getSimpleName(), e.toString());
        }
        return json;
    }
}
