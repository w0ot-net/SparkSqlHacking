package org.apache.avro.util;

import java.util.Objects;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

public class MapUtil {
   private MapUtil() {
   }

   public static Object computeIfAbsent(ConcurrentMap map, Object key, Function mappingFunction) {
      V value = (V)map.get(key);
      if (value != null) {
         return value;
      } else {
         Objects.requireNonNull(mappingFunction);
         return map.computeIfAbsent(key, mappingFunction::apply);
      }
   }
}
