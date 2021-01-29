package home.klimov.testtaskspringdbpostgres.repository;

import home.klimov.testtaskspringdbpostgres.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface DirectorRepository extends JpaRepository<Director, Long> {
    @Query(value =
            "SELECT * FROM director d WHERE d.first_name = :first_name AND d.last_name = :last_name AND d.birth_date = :birth_date",
            nativeQuery = true)
    List<Director> findDirectorByAllFields(
            @Param("first_name") String first_name,
            @Param("last_name")String last_name,
            @Param("birth_date")Date birth_date
    );
}
