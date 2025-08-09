package io.jsonwebtoken.lang;

import java.util.HashMap;
import java.util.Map;

public final class Maps {
   private Maps() {
   }

   public static MapBuilder of(Object key, Object value) {
      return (new HashMapBuilder()).and(key, value);
   }

   private static class HashMapBuilder implements MapBuilder {
      private final Map data;

      private HashMapBuilder() {
         this.data = new HashMap();
      }

      public MapBuilder and(Object key, Object value) {
         this.data.put(key, value);
         return this;
      }

      public Map build() {
         return java.util.Collections.unmodifiableMap(this.data);
      }
   }

   public interface MapBuilder extends Builder {
      MapBuilder and(Object var1, Object var2);

      Map build();
   }
}
