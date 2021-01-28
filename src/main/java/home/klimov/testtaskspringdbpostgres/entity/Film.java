package home.klimov.testtaskspringdbpostgres.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import home.klimov.testtaskspringdbpostgres.Utils.CustomDateSerializer;
import home.klimov.testtaskspringdbpostgres.constants.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "film")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Film {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "director_id")
    private long directorId;

    @Column(name = "name")
    private String name;

    @Column(name = "release_date")
    @DateTimeFormat(pattern = Constants.DATE_FORMAT)
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date releaseDate;

    @Column(name = "genre")
    private String genre;
}
