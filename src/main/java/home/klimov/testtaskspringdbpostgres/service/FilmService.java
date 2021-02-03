package home.klimov.testtaskspringdbpostgres.service;

import home.klimov.testtaskspringdbpostgres.entity.Director;
import home.klimov.testtaskspringdbpostgres.entity.Film;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface FilmService {
    long create(Film film);

    Optional<Film> read(long id);

    Boolean update(Film film);

    Boolean delete(long id);

    List<Film> searchFilmsByDirectorLastNameLike(String lastName);

    List<Film> searchFilmsByDirectorLastNameLikeAndReleaseDateAfter(String lastName, Date dateFrom);

    List<Film> searchFilmsByDirectorLastNameLikeAndReleaseDateBefore(String lastName, Date dateTo);

    List<Film> searchFilmsByReleaseDateBetween(Date dateFrom, Date dateTo);

    List<Film> searchFilmsByDirectorLastNameLikeAndReleaseDateBetween(String lastName, Date dateFrom, Date dateTo);

    List<Film> searchFilmsByReleaseDateBefore(Date dateTo);

    List<Film> searchFilmsByReleaseDateAfter(Date dateFrom);

    List<Film> searchFilmsByNameLike(String name);

    List<Film> searchAllFilms();
}
