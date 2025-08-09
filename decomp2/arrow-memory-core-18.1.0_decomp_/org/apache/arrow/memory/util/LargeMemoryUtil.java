package org.apache.arrow.memory.util;

import org.apache.arrow.memory.BoundsChecking;

public final class LargeMemoryUtil {
   private LargeMemoryUtil() {
   }

   public static int checkedCastToInt(long length) {
      return BoundsChecking.BOUNDS_CHECKING_ENABLED ? Math.toIntExact(length) : (int)length;
   }

   public static int capAtMaxInt(long length) {
      return (int)Math.min(length, 2147483647L);
   }
}
