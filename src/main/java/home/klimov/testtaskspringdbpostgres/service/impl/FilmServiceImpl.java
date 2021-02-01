package home.klimov.testtaskspringdbpostgres.service.impl;

import home.klimov.testtaskspringdbpostgres.Utils.ObjectToJson;
import home.klimov.testtaskspringdbpostgres.constants.Constants;
import home.klimov.testtaskspringdbpostgres.entity.Film;
import home.klimov.testtaskspringdbpostgres.repository.FilmRepository;
import home.klimov.testtaskspringdbpostgres.service.FilmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@Slf4j
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;

    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public long create(Film film) {
        filmRepository.save(film);
        log.info(Constants.FILM_SAVED, ObjectToJson.toJson(film));
        return film.getId();
    }

    @Override
    public Optional<Film> read(long id) {
        Optional<Film> film = filmRepository.findById(id);
        film.ifPresent(data -> log.info(Constants.FILM_RECEIVED, id, ObjectToJson.filmToJson(film.get())));
        return film;
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

    @Override
    public List<Film> searchFilmsBetweenDate(Date date1, Date date2) {
        return filmRepository.findFilmsBetweenDate(date1, date2);
    }
}
