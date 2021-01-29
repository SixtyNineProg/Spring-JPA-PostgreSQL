package home.klimov.testtaskspringdbpostgres.service.impl;

import home.klimov.testtaskspringdbpostgres.Utils.ObjectToJson;
import home.klimov.testtaskspringdbpostgres.constants.Constants;
import home.klimov.testtaskspringdbpostgres.entity.Director;
import home.klimov.testtaskspringdbpostgres.repository.DirectorRepository;
import home.klimov.testtaskspringdbpostgres.service.DirectorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Transactional
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
    public Optional<Director> readById(long id) {
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

    @Override
    public List<Director> read(Director director) {
        List<Director> directors = directorRepository.findDirectorByAllFields(
                director.getFirstName(),
                director.getLastName(),
                director.getBirthDate()
        );
        log.info(Constants.DIRECTORS_RECEIVED, directors.size());
        return directors;
    }

    @Override
    public List<Director> read(String firstName, String lastName, Date birthDate) {
        List<Director> directors = directorRepository.findDirectorByAllFields(
                firstName,
                lastName,
                birthDate
        );
        log.info(Constants.DIRECTORS_RECEIVED, directors.size());
        return directors;
    }
}
