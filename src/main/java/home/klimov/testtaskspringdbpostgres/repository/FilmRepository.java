package home.klimov.testtaskspringdbpostgres.repository;

import home.klimov.testtaskspringdbpostgres.entity.Director;
import home.klimov.testtaskspringdbpostgres.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Long> {
    @Query(value =
            "SELECT * " +
                    "FROM film f left join director d ON f.director_id = d.id " +
                    "WHERE " +
                    "lower(d.last_name) ILIKE lower(:last_name)",
            nativeQuery = true)
    List<Film> findFilmsByDirectorLastNameLike(
            @Param("last_name") String lastName);

    @Query(value =
            "SELECT * " +
                    "FROM film f left join director d ON f.director_id = d.id " +
                    "WHERE " +
                    "lower(d.last_name) LIKE lower(:last_name) " +
                    "AND f.release_date > :date_from",
            nativeQuery = true)
    List<Film> findFilmsByDirectorLastNameLikeAndReleaseDateAfter(
            @Param("last_name")String lastName,
            @Param("date_from")Date dateFrom);

    @Query(value =
            "SELECT * " +
                    "FROM film f left join director d ON f.director_id = d.id " +
                    "WHERE " +
                    "lower(d.last_name) LIKE lower(:last_name) " +
                    "AND f.release_date < :date_to",
            nativeQuery = true)
    List<Film> findFilmsByDirectorLastNameLikeAndReleaseDateBefore(
            @Param("last_name") String lastName,
            @Param("date_to") Date dateTo);

    List<Film> findFilmsByReleaseDateBetween(Date dateFrom, Date dateTo);

    @Query(value =
            "SELECT * " +
                    "FROM film f left join director d ON f.director_id = d.id " +
                    "WHERE " +
                    "lower(d.last_name) LIKE lower(:last_name) " +
                    "AND f.release_date BETWEEN :date_from AND :date_to",
            nativeQuery = true)
    List<Film> findFilmsByDirectorLastNameLikeAndReleaseDateBetween(
            @Param("last_name") String lastName,
            @Param("date_from") Date dateFrom,
            @Param("date_to") Date dateTo);

    List<Film> findFilmsByReleaseDateBefore(Date dateTo);

    List<Film> findFilmsByReleaseDateAfter(Date dateFrom);

    List<Film> findFilmsByNameLikeIgnoreCase(String name);

    List<Film> findFilmBy();
}
