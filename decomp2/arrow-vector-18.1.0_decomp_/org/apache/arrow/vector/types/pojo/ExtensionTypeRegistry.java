package org.apache.arrow.vector.types.pojo;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class ExtensionTypeRegistry {
   private static final ConcurrentMap registry = new ConcurrentHashMap();

   public static void register(ArrowType.ExtensionType type) {
      registry.put(type.extensionName(), type);
   }

   public static void unregister(ArrowType.ExtensionType type) {
      registry.remove(type.extensionName());
   }

   public static ArrowType.ExtensionType lookup(String name) {
      return (ArrowType.ExtensionType)registry.get(name);
   }
}
