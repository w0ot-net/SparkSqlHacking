package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import java.time.Duration;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
final class Internal {
   @IgnoreJRERequirement
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
