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
    public List<Film> searchFilmsByDirectorLastNameLike(String lastName) {
        return filmRepository.findFilmsByDirectorLastLike(lastName);
    }

    @Override
    public List<Film> searchFilmsByDirectorLastNameLikeAndReleaseDateAfter(String lastName, Date dateFrom) {
        return filmRepository.findFilmsByDirectorLastNameLikeAndReleaseDateAfter(lastName, dateFrom);
    }

    @Override
    public List<Film> searchFilmsByDirectorLastNameLikeAndReleaseDateBefore(String lastName, Date dateTo) {
        return filmRepository.findFilmsByDirectorLastNameLikeAndReleaseDateBefore(lastName,dateTo);
    }

    @Override
    public List<Film> searchFilmsByReleaseDateBetween(Date dateFrom, Date dateTo) {
        return filmRepository.findFilmsByReleaseDateBetween(dateFrom, dateTo);
    }

    @Override
    public List<Film> searchFilmsByDirectorLastNameLikeAndReleaseDateBetween(String lastName, Date dateFrom, Date dateTo) {
        return filmRepository.findFilmsByDirectorLastNameLikeAndReleaseDateBetween(lastName, dateFrom, dateTo);
    }

    @Override
    public List<Film> searchFilmsByReleaseDateBefore(Date dateTo) {
        return filmRepository.findFilmsByReleaseDateBefore(dateTo);
    }

    @Override
    public List<Film> searchFilmsByReleaseDateAfter(Date dateFrom) {
        return filmRepository.findFilmsByReleaseDateAfter(dateFrom);
    }

    @Override
    public List<Film> searchFilmsByNameLike(String name) {
        return filmRepository.findFilmsByNameLike(name);
    }
}
