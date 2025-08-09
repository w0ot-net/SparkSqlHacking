package org.glassfish.jersey.internal.inject;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

public final class PrimitiveMapper {
   public static final Map primitiveToClassMap = getPrimitiveToClassMap();
   public static final Map primitiveToDefaultValueMap = getPrimitiveToDefaultValueMap();

   private static Map getPrimitiveToClassMap() {
      Map<Class, Class> m = new WeakHashMap();
      m.put(Boolean.TYPE, Boolean.class);
      m.put(Byte.TYPE, Byte.class);
      m.put(Character.TYPE, Character.class);
      m.put(Short.TYPE, Short.class);
      m.put(Integer.TYPE, Integer.class);
      m.put(Long.TYPE, Long.class);
      m.put(Float.TYPE, Float.class);
      m.put(Double.TYPE, Double.class);
      return Collections.unmodifiableMap(m);
   }

   private static Map getPrimitiveToDefaultValueMap() {
      Map<Class, Object> m = new WeakHashMap();
      m.put(Boolean.class, false);
      m.put(Byte.class, (byte)0);
      m.put(Character.class, '\u0000');
      m.put(Short.class, Short.valueOf((short)0));
      m.put(Integer.class, 0);
      m.put(Long.class, 0L);
      m.put(Float.class, 0.0F);
      m.put(Double.class, (double)0.0F);
      return Collections.unmodifiableMap(m);
   }

   private PrimitiveMapper() {
   }
}
