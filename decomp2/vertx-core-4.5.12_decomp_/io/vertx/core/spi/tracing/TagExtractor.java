package io.vertx.core.spi.tracing;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public interface TagExtractor {
   static TagExtractor empty() {
      return Extractors.EMPTY;
   }

   default int len(Object obj) {
      return 0;
   }

   default String name(Object obj, int index) {
      throw new IndexOutOfBoundsException("Invalid tag index " + index);
   }

   default String value(Object obj, int index) {
      throw new IndexOutOfBoundsException("Invalid tag index " + index);
   }

   default Map extract(Object obj) {
      Map<String, String> tags = new HashMap();
      this.extractTo(obj, tags);
      return tags;
   }

   default void extractTo(Object obj, Map tags) {
      int len = this.len(obj);

      for(int idx = 0; idx < len; ++idx) {
         tags.put(this.name(obj, idx), this.value(obj, idx));
      }

   }

   default void extractTo(Object obj, BiConsumer consumer) {
      int len = this.len(obj);

      for(int idx = 0; idx < len; ++idx) {
         consumer.accept(this.name(obj, idx), this.value(obj, idx));
      }

   }
}
