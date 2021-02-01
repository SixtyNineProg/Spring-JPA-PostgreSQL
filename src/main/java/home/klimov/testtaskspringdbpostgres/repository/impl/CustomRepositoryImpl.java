package home.klimov.testtaskspringdbpostgres.repository.impl;

import home.klimov.testtaskspringdbpostgres.entity.Film;
import home.klimov.testtaskspringdbpostgres.repository.CustomRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class CustomRepositoryImpl implements CustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Film> findFilmsByName(String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Film> query = cb.createQuery(Film.class);
        Root<Film> film = query.from(Film.class);

        Path<String> path = film.get("name");

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.like(path, name));

        query.select(film).where(cb.or(predicates.toArray(new Predicate[predicates.size()])));

        return entityManager.createQuery(query).getResultList();
    }
}
