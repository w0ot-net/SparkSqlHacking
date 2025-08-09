package shaded.parquet.net.openhft.hashing;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;
import org.jetbrains.annotations.NotNull;

class Maths {
   @NotNull
   private static final Maths INSTANCE;

   public static long unsignedLongMulXorFold(long lhs, long rhs) {
      return INSTANCE.unsignedLongMulXorFoldImp(lhs, rhs);
   }

   public static long unsignedLongMulHigh(long lhs, long rhs) {
      return INSTANCE.unsignedLongMulHighImp(lhs, rhs);
   }

   long unsignedLongMulXorFoldImp(long lhs, long rhs) {
      long lhs_l = lhs & 4294967295L;
      long lhs_h = lhs >>> 32;
      long rhs_l = rhs & 4294967295L;
      long rhs_h = rhs >>> 32;
      long lo_lo = lhs_l * rhs_l;
      long hi_lo = lhs_h * rhs_l;
      long lo_hi = lhs_l * rhs_h;
      long hi_hi = lhs_h * rhs_h;
      long cross = (lo_lo >>> 32) + (hi_lo & 4294967295L) + lo_hi;
      long upper = (hi_lo >>> 32) + (cross >>> 32) + hi_hi;
      long lower = cross << 32 | lo_lo & 4294967295L;
      return lower ^ upper;
   }

   long unsignedLongMulHighImp(long lhs, long rhs) {
      long lhs_l = lhs & 4294967295L;
      long lhs_h = lhs >>> 32;
      long rhs_l = rhs & 4294967295L;
      long rhs_h = rhs >>> 32;
      long lo_lo = lhs_l * rhs_l;
      long hi_lo = lhs_h * rhs_l;
      long lo_hi = lhs_l * rhs_h;
      long hi_hi = lhs_h * rhs_h;
      long cross = (lo_lo >>> 32) + (hi_lo & 4294967295L) + lo_hi;
      long upper = (hi_lo >>> 32) + (cross >>> 32) + hi_hi;
      return upper;
   }

   static {
      Maths maths = null;

      try {
         Method multiplyHigh = Math.class.getDeclaredMethod("multiplyHigh", Integer.TYPE, Integer.TYPE);
         MethodHandle multiplyHighMH = MethodHandles.lookup().unreflect(multiplyHigh);
         maths = new MathsJDK9(multiplyHighMH);
      } catch (Throwable var3) {
         maths = new Maths();
      }

      INSTANCE = maths;
   }
}
