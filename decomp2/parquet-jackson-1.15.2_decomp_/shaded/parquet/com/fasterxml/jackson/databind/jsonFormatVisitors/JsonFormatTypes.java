package shaded.parquet.com.fasterxml.jackson.databind.jsonFormatVisitors;

import java.util.HashMap;
import java.util.Map;
import shaded.parquet.com.fasterxml.jackson.annotation.JsonCreator;
import shaded.parquet.com.fasterxml.jackson.annotation.JsonValue;

public enum JsonFormatTypes {
   STRING,
   NUMBER,
   INTEGER,
   BOOLEAN,
   OBJECT,
   ARRAY,
   NULL,
   ANY;

   private static final Map _byLCName = new HashMap();

   @JsonValue
   public String value() {
      return this.name().toLowerCase();
   }

   @JsonCreator
   public static JsonFormatTypes forValue(String s) {
      return (JsonFormatTypes)_byLCName.get(s);
   }

   static {
      for(JsonFormatTypes t : values()) {
         _byLCName.put(t.name().toLowerCase(), t);
      }

   }
}
