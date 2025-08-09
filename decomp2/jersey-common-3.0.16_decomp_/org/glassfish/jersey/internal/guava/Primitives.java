package org.glassfish.jersey.internal.guava;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class Primitives {
   private static final Map PRIMITIVE_TO_WRAPPER_TYPE;

   private Primitives() {
   }

   private static void add(Map forward, Class key, Class value) {
      forward.put(key, value);
   }

   public static Class wrap(Class type) {
      Preconditions.checkNotNull(type);
      Class<T> wrapped = (Class)PRIMITIVE_TO_WRAPPER_TYPE.get(type);
      return wrapped == null ? type : wrapped;
   }

   static {
      Map<Class<?>, Class<?>> primToWrap = new HashMap(16);
      add(primToWrap, Boolean.TYPE, Boolean.class);
      add(primToWrap, Byte.TYPE, Byte.class);
      add(primToWrap, Character.TYPE, Character.class);
      add(primToWrap, Double.TYPE, Double.class);
      add(primToWrap, Float.TYPE, Float.class);
      add(primToWrap, Integer.TYPE, Integer.class);
      add(primToWrap, Long.TYPE, Long.class);
      add(primToWrap, Short.TYPE, Short.class);
      add(primToWrap, Void.TYPE, Void.class);
      PRIMITIVE_TO_WRAPPER_TYPE = Collections.unmodifiableMap(primToWrap);
   }
}
