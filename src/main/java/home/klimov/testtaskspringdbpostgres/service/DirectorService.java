package home.klimov.testtaskspringdbpostgres.service;

import home.klimov.testtaskspringdbpostgres.entity.Director;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface DirectorService {
    long create(Director director);

    Optional<Director> readById(long id);

    Boolean update(Director director);

    Boolean delete(long id);

    List<Director> read(Director director);

    List<Director> read(String firstName, String lastName, Date birthDate);

    List<Director> searchAllDirectors();
}
