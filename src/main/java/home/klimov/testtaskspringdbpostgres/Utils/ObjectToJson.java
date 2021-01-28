package home.klimov.testtaskspringdbpostgres.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import home.klimov.testtaskspringdbpostgres.constants.Constants;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ObjectToJson {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static synchronized String toJson(Object o) {
        String json = null;
        try {
            json = objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            log.warn(Constants.ERROR_PARSING_OF_OBJECT, o.getClass().getSimpleName(), e.toString());
        }
        return json;
    }
}
