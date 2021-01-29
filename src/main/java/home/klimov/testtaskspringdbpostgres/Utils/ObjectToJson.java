package home.klimov.testtaskspringdbpostgres.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import home.klimov.testtaskspringdbpostgres.constants.Constants;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ObjectToJson {
    private static final Gson gson = new GsonBuilder().setDateFormat(Constants.DATE_FORMAT).create();

    public static synchronized String toJson(Object o) {
        return gson.toJson(o);
    }
}
