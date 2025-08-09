package org.apache.orc.protobuf;

@CheckReturnValue
final class NewInstanceSchemas {
   private static final NewInstanceSchema FULL_SCHEMA = loadSchemaForFullRuntime();
   private static final NewInstanceSchema LITE_SCHEMA = new NewInstanceSchemaLite();

   static NewInstanceSchema full() {
      return FULL_SCHEMA;
   }

   static NewInstanceSchema lite() {
      return LITE_SCHEMA;
   }

   private static NewInstanceSchema loadSchemaForFullRuntime() {
      try {
         Class<?> clazz = Class.forName("org.apache.orc.protobuf.NewInstanceSchemaFull");
         return (NewInstanceSchema)clazz.getDeclaredConstructor().newInstance();
      } catch (Exception var1) {
         return null;
      }
   }
}
