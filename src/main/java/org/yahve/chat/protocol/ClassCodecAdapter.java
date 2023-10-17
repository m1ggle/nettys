package org.yahve.chat.protocol;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * @author m1ggle
 * @project nettys
 * @describe
 * @date 2023/10/17
 */
public class ClassCodecAdapter implements JsonSerializer<Class<?>>, JsonDeserializer<Class<?>> {
    @Override
    public Class<?> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        try {
            String element = jsonElement.getAsString();

            return Class.forName(element);
        }catch (Exception e){
            throw new JsonParseException(e);
        }
    }

    @Override
    public JsonElement serialize(Class<?> aClass, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(aClass.getName());
    }
}
