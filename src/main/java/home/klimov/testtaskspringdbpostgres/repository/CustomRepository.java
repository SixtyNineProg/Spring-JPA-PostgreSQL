package home.klimov.testtaskspringdbpostgres.repository;

import home.klimov.testtaskspringdbpostgres.entity.Film;

import java.util.List;

public interface CustomRepository {
    List<Film> findFilmsByName(String name);
}
