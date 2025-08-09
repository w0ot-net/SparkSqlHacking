package org.apache.logging.log4j.core.config.plugins.convert;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class DateTypeConverter {
   private static final Map CONSTRUCTORS = new ConcurrentHashMap();

   public static Date fromMillis(final long millis, final Class type) {
      try {
         return ((MethodHandle)CONSTRUCTORS.get(type)).invoke(millis);
      } catch (Throwable var4) {
         return null;
      }
   }

   private DateTypeConverter() {
   }

   static {
      MethodHandles.Lookup lookup = MethodHandles.publicLookup();

      for(Class dateClass : Arrays.asList(Date.class, java.sql.Date.class, Time.class, Timestamp.class)) {
         try {
            CONSTRUCTORS.put(dateClass, lookup.findConstructor(dateClass, MethodType.methodType(Void.TYPE, Long.TYPE)));
         } catch (IllegalAccessException | NoSuchMethodException var4) {
         }
      }

   }
}
