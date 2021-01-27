package home.klimov.testtaskspringdbpostgres.service;

import home.klimov.testtaskspringdbpostgres.entity.Director;

import java.util.Optional;

public interface DirectorService {
    long create(Director director);

    Optional<Director> read(long id);

    Boolean update(Director director);

    Boolean delete(long id);
}
