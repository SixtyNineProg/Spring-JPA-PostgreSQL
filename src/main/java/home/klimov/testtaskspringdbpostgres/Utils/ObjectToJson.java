package home.klimov.testtaskspringdbpostgres.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import home.klimov.testtaskspringdbpostgres.constants.Constants;
import home.klimov.testtaskspringdbpostgres.entity.Director;
import home.klimov.testtaskspringdbpostgres.entity.Film;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

@Slf4j
public class ObjectToJson {
    private static final Gson gson = new GsonBuilder().setDateFormat(Constants.DATE_FORMAT).create(); //.setPrettyPrinting()

    public static synchronized String toJson(Object o) {
        return gson.toJson(o);
    }

    public static synchronized String filmToJson(Film film) {
        Film unProxyFilm = EntityUnProxy.initializeAndUnProxy(film);
        Director unProxyDirector = EntityUnProxy.initializeAndUnProxy(film.getDirector());
        unProxyFilm.setDirector(unProxyDirector);
        return gson.toJson(unProxyFilm);
    }
}
