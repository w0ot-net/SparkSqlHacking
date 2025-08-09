package org.sparkproject.guava.hash;

import java.nio.Buffer;
import org.sparkproject.guava.annotations.GwtIncompatible;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
final class Java8Compatibility {
   static void clear(Buffer b) {
      b.clear();
   }

   static void flip(Buffer b) {
      b.flip();
   }

   static void limit(Buffer b, int limit) {
      b.limit(limit);
   }

   static void position(Buffer b, int position) {
      b.position(position);
   }

   private Java8Compatibility() {
   }
}
