package home.klimov.testtaskspringdbpostgres.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import home.klimov.testtaskspringdbpostgres.constants.Constants;
import home.klimov.testtaskspringdbpostgres.entity.Director;
import home.klimov.testtaskspringdbpostgres.entity.Film;
import home.klimov.testtaskspringdbpostgres.repository.DirectorRepository;
import home.klimov.testtaskspringdbpostgres.service.DirectorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRepository directorRepository;
    private final ObjectMapper objectMapper;

    public DirectorServiceImpl(DirectorRepository directorRepository, ObjectMapper objectMapper) {
        this.directorRepository = directorRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public long create(Director director) {
        directorRepository.save(director);
        log.info(Constants.DIRECTOR_SAVED, toJson(director));
        return director.getId();
    }

    @Override
    public Optional<Director> read(long id) {
        Optional<Director> director = directorRepository.findById(id);
        director.ifPresent(data -> log.info(Constants.DIRECTOR_RECEIVED, id, toJson(director)));
        return Optional.empty();
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

    private synchronized String toJson(Object o) {
        String json = null;
        try {
            json = objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            log.warn(Constants.ERROR_PARSING_OF_OBJECT, o.getClass().getSimpleName(), e.toString());
        }
        return json;
    }
}
