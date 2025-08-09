package org.glassfish.jaxb.core.v2.runtime;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class RuntimeUtil {
   public static final Map boxToPrimitive;
   public static final Map primitiveToBox;

   private RuntimeUtil() {
   }

   private static String getTypeName(Object o) {
      return o.getClass().getName();
   }

   static {
      Map<Class<?>, Class<?>> b = new HashMap();
      b.put(Byte.TYPE, Byte.class);
      b.put(Short.TYPE, Short.class);
      b.put(Integer.TYPE, Integer.class);
      b.put(Long.TYPE, Long.class);
      b.put(Character.TYPE, Character.class);
      b.put(Boolean.TYPE, Boolean.class);
      b.put(Float.TYPE, Float.class);
      b.put(Double.TYPE, Double.class);
      b.put(Void.TYPE, Void.class);
      primitiveToBox = Collections.unmodifiableMap(b);
      Map<Class<?>, Class<?>> p = new HashMap();

      for(Map.Entry e : b.entrySet()) {
         p.put((Class)e.getValue(), (Class)e.getKey());
      }

      boxToPrimitive = Collections.unmodifiableMap(p);
   }

   public static final class ToStringAdapter extends XmlAdapter {
      public Object unmarshal(String s) {
         throw new UnsupportedOperationException();
      }

      public String marshal(Object o) {
         return o == null ? null : o.toString();
      }
   }
}
