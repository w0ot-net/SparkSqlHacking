package org.sparkproject.spark_core.protobuf;

@CheckReturnValue
final class ListFieldSchemas {
   private static final ListFieldSchema FULL_SCHEMA = loadSchemaForFullRuntime();
   private static final ListFieldSchema LITE_SCHEMA = new ListFieldSchemaLite();

   static ListFieldSchema full() {
      return FULL_SCHEMA;
   }

   static ListFieldSchema lite() {
      return LITE_SCHEMA;
   }

   private static ListFieldSchema loadSchemaForFullRuntime() {
      if (Protobuf.assumeLiteRuntime) {
         return null;
      } else {
         try {
            Class<?> clazz = Class.forName("org.sparkproject.spark_core.protobuf.ListFieldSchemaFull");
            return (ListFieldSchema)clazz.getDeclaredConstructor().newInstance();
         } catch (Exception var1) {
            return null;
         }
      }
   }

   private ListFieldSchemas() {
   }
}
