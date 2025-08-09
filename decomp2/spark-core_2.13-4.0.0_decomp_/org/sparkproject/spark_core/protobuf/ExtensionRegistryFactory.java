package org.sparkproject.spark_core.protobuf;

final class ExtensionRegistryFactory {
   static final String FULL_REGISTRY_CLASS_NAME = "org.sparkproject.spark_core.protobuf.ExtensionRegistry";
   static final Class EXTENSION_REGISTRY_CLASS = reflectExtensionRegistry();

   static Class reflectExtensionRegistry() {
      try {
         return Class.forName("org.sparkproject.spark_core.protobuf.ExtensionRegistry");
      } catch (ClassNotFoundException var1) {
         return null;
      }
   }

   public static ExtensionRegistryLite create() {
      ExtensionRegistryLite result = invokeSubclassFactory("newInstance");
      return result != null ? result : new ExtensionRegistryLite();
   }

   public static ExtensionRegistryLite createEmpty() {
      ExtensionRegistryLite result = invokeSubclassFactory("getEmptyRegistry");
      return result != null ? result : ExtensionRegistryLite.EMPTY_REGISTRY_LITE;
   }

   static boolean isFullRegistry(ExtensionRegistryLite registry) {
      return !Protobuf.assumeLiteRuntime && EXTENSION_REGISTRY_CLASS != null && EXTENSION_REGISTRY_CLASS.isAssignableFrom(registry.getClass());
   }

   private static final ExtensionRegistryLite invokeSubclassFactory(String methodName) {
      if (EXTENSION_REGISTRY_CLASS == null) {
         return null;
      } else {
         try {
            return (ExtensionRegistryLite)EXTENSION_REGISTRY_CLASS.getDeclaredMethod(methodName).invoke((Object)null);
         } catch (Exception var2) {
            return null;
         }
      }
   }
}
