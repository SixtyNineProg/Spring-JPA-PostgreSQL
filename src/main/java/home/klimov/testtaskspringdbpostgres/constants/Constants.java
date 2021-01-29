package home.klimov.testtaskspringdbpostgres.constants;

public class Constants {
    public static final String FILM_SAVED = "Film saved successful : {}";
    public static final String FILM_RECEIVED = "Film with id={} received successful : {}";
    public static final String FILM_DELETED = "Film with id={} deleted successful";

    public static final String DIRECTOR_SAVED = "Director saved successful : {}";
    public static final String DIRECTOR_ALREADY_EXIST = "Director with the given parameters already exist with id = ";
    public static final String DIRECTOR_RECEIVED = "Director with id={} received successful : {}";
    public static final String DIRECTORS_RECEIVED = "Found a list of directors with size={}";
    public static final String DIRECTOR_DELETED = "Director with id={} deleted successful";

    public static final String DIRECTOR_NOT_FOUND_ID = "Not found Director with id = ";
    public static final String DIRECTOR_NOT_FOUND = "Not found Director with the given parameters";

    public static final String DATE_FORMAT = "dd-MM-yyyy";

    public static final String ERROR_PARSING_OF_OBJECT = "can't represent object of class {} in json form for logging: {}";
}