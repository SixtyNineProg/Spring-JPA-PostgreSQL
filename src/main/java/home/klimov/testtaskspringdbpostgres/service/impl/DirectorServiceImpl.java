package home.klimov.testtaskspringdbpostgres.service.impl;

import home.klimov.testtaskspringdbpostgres.Utils.ObjectToJson;
import home.klimov.testtaskspringdbpostgres.constants.Constants;
import home.klimov.testtaskspringdbpostgres.entity.Director;
import home.klimov.testtaskspringdbpostgres.repository.DirectorRepository;
import home.klimov.testtaskspringdbpostgres.service.DirectorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRepository directorRepository;

    public DirectorServiceImpl(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    @Override
    public long create(Director director) {
        directorRepository.save(director);
        log.info(Constants.DIRECTOR_SAVED, ObjectToJson.toJson(director));
        return director.getId();
    }

    @Override
    public Optional<Director> read(long id) {
        Optional<Director> director = directorRepository.findById(id);
        director.ifPresent(data -> log.info(Constants.DIRECTOR_RECEIVED, id, ObjectToJson.toJson(director.get())));
        return director;
    }

    @Override
    public Boolean update(Director director) {
        return null;
    }

    @Override
    public Boolean delete(long id) {
        directorRepository.deleteById(id);
        log.info(Constants.DIRECTOR_DELETED, id);
        return true;
    }
}
