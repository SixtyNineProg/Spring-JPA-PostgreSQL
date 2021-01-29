package home.klimov.testtaskspringdbpostgres.entity;

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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "director_id")
    private Director director;

    @Column(name = "name")
    private String name;

    @Column(name = "release_date")
    @DateTimeFormat(pattern = Constants.DATE_FORMAT)
    private Date releaseDate;

    @Column(name = "genre")
    private String genre;
}
