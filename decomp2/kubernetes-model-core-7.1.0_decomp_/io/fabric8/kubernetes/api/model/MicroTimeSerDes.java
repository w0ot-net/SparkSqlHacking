package io.fabric8.kubernetes.api.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public class MicroTimeSerDes {
   private MicroTimeSerDes() {
   }

   public static class Serializer extends JsonSerializer {
      public void serialize(MicroTime value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
         if (value != null) {
            if (value.getTime() != null) {
               jgen.writeString(value.getTime());
            }
         } else {
            jgen.writeNull();
         }

      }
   }

   public static class Deserializer extends JsonDeserializer {
      public MicroTime deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
         ObjectCodec oc = jsonParser.getCodec();
         JsonNode node = (JsonNode)oc.readTree(jsonParser);
         MicroTime microTime = null;
         if (node != null) {
            microTime = new MicroTime(node.asText());
         }

         return microTime;
      }
   }
}
