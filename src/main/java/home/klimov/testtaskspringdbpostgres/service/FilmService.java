package home.klimov.testtaskspringdbpostgres.service;

import home.klimov.testtaskspringdbpostgres.entity.Film;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface FilmService {
    long create(Film film);

    Optional<Film> read(long id);

    Boolean update(Film film);

    Boolean delete(long id);

    List<Film> searchFilmsBetweenDate(Date date1, Date date2);

    List<Film> searchFilmsByDirectorLastName(String lastName);
}
