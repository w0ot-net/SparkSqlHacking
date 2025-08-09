package io.fabric8.kubernetes.api.model.apiextensions.v1;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.List;

public class JSONSchemaPropsOrStringArraySerDe {
   private JSONSchemaPropsOrStringArraySerDe() {
   }

   public static class Serializer extends JsonSerializer {
      public void serialize(JSONSchemaPropsOrStringArray jsonSchemaPropsOrStringArray, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
         if (jsonSchemaPropsOrStringArray.getProperty() != null && !jsonSchemaPropsOrStringArray.getProperty().isEmpty()) {
            jsonGenerator.writeStartArray();

            for(String property : jsonSchemaPropsOrStringArray.getProperty()) {
               jsonGenerator.writeObject(property);
            }

            jsonGenerator.writeEndArray();
         } else {
            jsonGenerator.writeObject(jsonSchemaPropsOrStringArray.getSchema());
         }

      }
   }

   public static class Deserializer extends JsonDeserializer {
      public JSONSchemaPropsOrStringArray deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
         JSONSchemaPropsOrStringArrayBuilder builder = new JSONSchemaPropsOrStringArrayBuilder();
         if (jsonParser.isExpectedStartObjectToken()) {
            builder.withSchema((JSONSchemaProps)jsonParser.readValueAs(JSONSchemaProps.class));
         } else if (jsonParser.isExpectedStartArrayToken()) {
            builder.withProperty((List)jsonParser.readValueAs(new TypeReference() {
            }));
         }

         return builder.build();
      }
   }
}
