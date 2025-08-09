package org.sparkproject.spark_core.protobuf;

@CheckReturnValue
final class MapFieldSchemas {
   private static final MapFieldSchema FULL_SCHEMA = loadSchemaForFullRuntime();
   private static final MapFieldSchema LITE_SCHEMA = new MapFieldSchemaLite();

   static MapFieldSchema full() {
      return FULL_SCHEMA;
   }

   static MapFieldSchema lite() {
      return LITE_SCHEMA;
   }

   private static MapFieldSchema loadSchemaForFullRuntime() {
      if (Protobuf.assumeLiteRuntime) {
         return null;
      } else {
         try {
            Class<?> clazz = Class.forName("org.sparkproject.spark_core.protobuf.MapFieldSchemaFull");
            return (MapFieldSchema)clazz.getDeclaredConstructor().newInstance();
         } catch (Exception var1) {
            return null;
         }
      }
   }

   private MapFieldSchemas() {
   }
}
