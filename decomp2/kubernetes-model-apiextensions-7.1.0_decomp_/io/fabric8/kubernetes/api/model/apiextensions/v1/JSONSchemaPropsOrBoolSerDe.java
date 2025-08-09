package io.fabric8.kubernetes.api.model.apiextensions.v1;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public class JSONSchemaPropsOrBoolSerDe {
   private JSONSchemaPropsOrBoolSerDe() {
   }

   public static class Serializer extends JsonSerializer {
      public void serialize(JSONSchemaPropsOrBool jsonSchemaPropsOrBool, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
         if (jsonSchemaPropsOrBool.getSchema() != null) {
            jsonGenerator.writeObject(jsonSchemaPropsOrBool.getSchema());
         } else {
            jsonGenerator.writeBoolean(jsonSchemaPropsOrBool.getAllows());
         }

      }
   }

   public static class Deserializer extends JsonDeserializer {
      public JSONSchemaPropsOrBool deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
         JSONSchemaPropsOrBoolBuilder builder = new JSONSchemaPropsOrBoolBuilder();
         if (jsonParser.isExpectedStartObjectToken()) {
            builder.withSchema((JSONSchemaProps)jsonParser.readValueAs(JSONSchemaProps.class));
            builder.withAllows(true);
         } else {
            builder.withAllows(jsonParser.getBooleanValue());
         }

         return builder.build();
      }
   }
}
