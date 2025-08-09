package shaded.parquet.net.openhft.hashing;

import java.lang.invoke.MethodHandle;

class MathsJDK9 extends Maths {
   private final MethodHandle multiplyHighMH;

   public MathsJDK9(MethodHandle multiplyHighMH) {
      this.multiplyHighMH = multiplyHighMH;
   }

   long unsignedLongMulXorFoldImp(long lhs, long rhs) {
      long upper = this.invokeExact(lhs, rhs) + (lhs >> 63 & rhs) + (rhs >> 63 & lhs);
      long lower = lhs * rhs;
      return lower ^ upper;
   }

   long unsignedLongMulHighImp(long lhs, long rhs) {
      return this.invokeExact(lhs, rhs) + (lhs >> 63 & rhs) + (rhs >> 63 & lhs);
   }

   private long invokeExact(long lhs, long rhs) {
      try {
         return this.multiplyHighMH.invokeExact(lhs, rhs);
      } catch (Throwable e) {
         throw new AssertionError(e);
      }
   }
}
