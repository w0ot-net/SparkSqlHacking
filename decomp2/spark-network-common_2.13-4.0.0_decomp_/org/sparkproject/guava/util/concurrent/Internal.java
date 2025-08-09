package org.sparkproject.guava.util.concurrent;

import java.time.Duration;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
final class Internal {
   static long toNanosSaturated(Duration duration) {
      try {
         return duration.toNanos();
      } catch (ArithmeticException var2) {
         return duration.isNegative() ? Long.MIN_VALUE : Long.MAX_VALUE;
      }
   }

   private Internal() {
   }
}
