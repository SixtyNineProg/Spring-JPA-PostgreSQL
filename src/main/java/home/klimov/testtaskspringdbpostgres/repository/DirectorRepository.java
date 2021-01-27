package home.klimov.testtaskspringdbpostgres.repository;

import home.klimov.testtaskspringdbpostgres.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository extends JpaRepository<Director, Long> {
}
