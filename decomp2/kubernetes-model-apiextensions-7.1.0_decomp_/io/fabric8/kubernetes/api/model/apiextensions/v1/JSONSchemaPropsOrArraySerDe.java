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

public class JSONSchemaPropsOrArraySerDe {
   private JSONSchemaPropsOrArraySerDe() {
   }

   public static class Serializer extends JsonSerializer {
      public void serialize(JSONSchemaPropsOrArray jsonSchemaPropsOrArray, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
         if (jsonSchemaPropsOrArray.getJSONSchemas() != null && !jsonSchemaPropsOrArray.getJSONSchemas().isEmpty()) {
            jsonGenerator.writeStartArray();

            for(JSONSchemaProps schema : jsonSchemaPropsOrArray.getJSONSchemas()) {
               jsonGenerator.writeObject(schema);
            }

            jsonGenerator.writeEndArray();
         } else {
            jsonGenerator.writeObject(jsonSchemaPropsOrArray.getSchema());
         }

      }
   }

   public static class Deserializer extends JsonDeserializer {
      public JSONSchemaPropsOrArray deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
         JSONSchemaPropsOrArrayBuilder builder = new JSONSchemaPropsOrArrayBuilder();
         if (jsonParser.isExpectedStartObjectToken()) {
            builder.withSchema((JSONSchemaProps)jsonParser.readValueAs(JSONSchemaProps.class));
         } else if (jsonParser.isExpectedStartArrayToken()) {
            builder.withJSONSchemas((List)jsonParser.readValueAs(new TypeReference() {
            }));
         }

         return builder.build();
      }
   }
}
