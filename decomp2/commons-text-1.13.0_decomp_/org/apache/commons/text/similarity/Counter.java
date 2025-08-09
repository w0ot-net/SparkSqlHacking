package org.apache.commons.text.similarity;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

final class Counter {
   public static Map of(CharSequence[] tokens) {
      Map<CharSequence, Integer> map = new HashMap();
      Stream.of(tokens).forEach((token) -> map.compute(token, (k, v) -> v != null ? v + 1 : 1));
      return map;
   }

   private Counter() {
   }
}
