package home.klimov.testtaskspringdbpostgres.entity;

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
@Table(name = "director")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Director {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_date")
    @DateTimeFormat(pattern = Constants.DATE_FORMAT)
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date birthDate;

    @Override
    public String toString() {
        return
                "Director: "
                        + "ID: " + getId()
                        + ", First name: " + getFirstName()
                        + ", Last name: " + getLastName()
                        + ", Birth date: " + getBirthDate();
    }
}
