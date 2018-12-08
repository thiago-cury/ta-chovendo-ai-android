package thiagocury.eti.br.tachovendoaii.helper;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import thiagocury.eti.br.tachovendoaii.model.Temperatura;

/**
 * Created by thiagocury on 03/01/2018.
 */

public class TemperaturaDeserializer implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonElement element = null;
        if(json.getAsJsonObject() != null){
            element = json.getAsJsonObject();
        }
        return (new Gson().fromJson(element,Temperatura.class));
    }
}
