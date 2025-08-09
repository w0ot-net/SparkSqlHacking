package org.apache.logging.log4j.spi;

import java.util.HashMap;
import java.util.Map;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@NullMarked
public class NoOpThreadContextMap implements ThreadContextMap {
   public static final ThreadContextMap INSTANCE = new NoOpThreadContextMap();

   public void clear() {
   }

   public boolean containsKey(final String key) {
      return false;
   }

   public @Nullable String get(final String key) {
      return null;
   }

   public Map getCopy() {
      return new HashMap();
   }

   public @Nullable Map getImmutableMapOrNull() {
      return null;
   }

   public boolean isEmpty() {
      return true;
   }

   public void put(final String key, final String value) {
   }

   public void remove(final String key) {
   }
}
