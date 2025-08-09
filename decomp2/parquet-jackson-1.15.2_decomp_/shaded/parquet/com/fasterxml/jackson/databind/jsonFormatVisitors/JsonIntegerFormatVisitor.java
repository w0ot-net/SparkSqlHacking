package shaded.parquet.com.fasterxml.jackson.databind.jsonFormatVisitors;

import shaded.parquet.com.fasterxml.jackson.core.JsonParser;

public interface JsonIntegerFormatVisitor extends JsonValueFormatVisitor {
   void numberType(JsonParser.NumberType var1);

   public static class Base extends JsonValueFormatVisitor.Base implements JsonIntegerFormatVisitor {
      public void numberType(JsonParser.NumberType type) {
      }
   }
}
