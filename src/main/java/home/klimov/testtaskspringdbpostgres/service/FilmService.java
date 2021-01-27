package home.klimov.testtaskspringdbpostgres.service;

import home.klimov.testtaskspringdbpostgres.entity.Film;

import java.util.Optional;

public interface FilmService {
    long create(Film film);

    Optional<Film> read(long id);

    Boolean update(Film film);

    Boolean delete(long id);
}
