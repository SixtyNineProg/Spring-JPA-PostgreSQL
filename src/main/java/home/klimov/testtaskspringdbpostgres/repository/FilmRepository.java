package home.klimov.testtaskspringdbpostgres.repository;

import home.klimov.testtaskspringdbpostgres.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Long> {
    @Query(value =
            "SELECT * FROM film f WHERE f.release_date BETWEEN :date1 AND :date2",
            nativeQuery = true)
    List<Film> findFilmsBetweenDate(
            @Param("date1") Date date1,
            @Param("date2") Date date2
    );

    @Query(value =
            "SELECT * FROM film f left join director d on f.director_id = d.id WHERE d.last_name like :last_name",
            nativeQuery = true)
    List<Film> findFilmsByDirectorLastName(@Param("last_name") String lastName);

    List<Film> findFilmsByDirectorLastNameAndReleaseDateBetween(String lastName, Date dateFrom, Date dateTo);
    List<Film> findFilmsByDirectorLastNameAndReleaseDateAfter(String lastName, Date dateFrom);
    List<Film> findFilmsByDirectorLastNameAndReleaseDateBefore(String lastName, Date dateTo);
    List<Film> findFilmsByReleaseDateBetween(Date dateFrom, Date dateTo);
    List<Film> findFilmsByReleaseDateAfter(Date dateFrom);
    List<Film> findFilmsByReleaseDateBefore(Date dateTo);

}
