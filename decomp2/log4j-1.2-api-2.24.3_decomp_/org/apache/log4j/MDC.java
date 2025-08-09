package org.apache.log4j;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import org.apache.logging.log4j.ThreadContext;

public final class MDC {
   private static final ThreadLocal localMap = new InheritableThreadLocal() {
      protected Map initialValue() {
         return new HashMap();
      }

      protected Map childValue(final Map parentValue) {
         return parentValue == null ? new HashMap() : new HashMap(parentValue);
      }
   };

   private MDC() {
   }

   public static void put(final String key, final String value) {
      ((Map)localMap.get()).put(key, value);
      ThreadContext.put(key, value);
   }

   public static void put(final String key, final Object value) {
      ((Map)localMap.get()).put(key, value);
      ThreadContext.put(key, value.toString());
   }

   public static Object get(final String key) {
      return ((Map)localMap.get()).get(key);
   }

   public static void remove(final String key) {
      ((Map)localMap.get()).remove(key);
      ThreadContext.remove(key);
   }

   public static void clear() {
      ((Map)localMap.get()).clear();
      ThreadContext.clearMap();
   }

   public static Hashtable getContext() {
      return new Hashtable((Map)localMap.get());
   }
}
