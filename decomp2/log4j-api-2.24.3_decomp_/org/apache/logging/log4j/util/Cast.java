package org.apache.logging.log4j.util;

@InternalApi
public final class Cast {
   public static Object cast(final Object o) {
      return o == null ? null : o;
   }

   private Cast() {
   }
}
