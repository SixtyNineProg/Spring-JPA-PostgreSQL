package home.klimov.testtaskspringdbpostgres.Utils;

import home.klimov.testtaskspringdbpostgres.constants.Constants;
import home.klimov.testtaskspringdbpostgres.entity.Director;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ObjectToJsonTest {

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);

    private Date date1 = simpleDateFormat.parse("1998-04-30");

    private Director director = new Director(1, "Dmitry", "Klimov", date1);

    ObjectToJsonTest() throws ParseException {
    }

    @Test
    void toJson() {
        String expectedJsonStr
                = "{\"id\":1,\"firstName\":\"Dmitry\",\"lastName\":\"Klimov\",\"birthDate\":\"30-04-1998\"}";
        String jsonStr = ObjectToJson.toJson(director);
        assertEquals(expectedJsonStr, jsonStr);
    }

    @Test
    void testDate() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        System.out.println(formatter.format(date));
    }

    @Test
    void testSearchFilm() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        System.out.println(formatter.format(date));
    }
}