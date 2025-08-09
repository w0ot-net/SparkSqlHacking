package spire.math;

import java.io.Serializable;
import java.math.BigInteger;
import scala.math.BigInt;
import scala.math.BigInt.;
import scala.runtime.ModuleSerializationProxy;

public final class SafeLong$ implements SafeLongInstances, Serializable {
   public static final SafeLong$ MODULE$ = new SafeLong$();
   private static final SafeLong minusOne;
   private static final SafeLong zero;
   private static final SafeLong one;
   private static final SafeLong two;
   private static final SafeLong three;
   private static final SafeLong ten;
   private static final BigInteger big64;
   private static final SafeLong safe64;
   private static volatile SafeLongInstances.SafeLongAlgebra$ SafeLongAlgebra$module;
   private static volatile SafeLongInstances.SafeLongIsReal$ SafeLongIsReal$module;
   private static NumberTag SafeLongTag;

   static {
      SafeLongInstances.$init$(MODULE$);
      minusOne = new SafeLongLong(-1L);
      zero = new SafeLongLong(0L);
      one = new SafeLongLong(1L);
      two = new SafeLongLong(2L);
      three = new SafeLongLong(3L);
      ten = new SafeLongLong(10L);
      big64 = BigInteger.ONE.shiftLeft(63);
      safe64 = MODULE$.apply(.MODULE$.javaBigInteger2bigInt(MODULE$.big64()));
   }

   public SafeLongInstances.SafeLongAlgebra$ SafeLongAlgebra() {
      if (SafeLongAlgebra$module == null) {
         this.SafeLongAlgebra$lzycompute$1();
      }

      return SafeLongAlgebra$module;
   }

   public SafeLongInstances.SafeLongIsReal$ SafeLongIsReal() {
      if (SafeLongIsReal$module == null) {
         this.SafeLongIsReal$lzycompute$1();
      }

      return SafeLongIsReal$module;
   }

   public final NumberTag SafeLongTag() {
      return SafeLongTag;
   }

   public final void spire$math$SafeLongInstances$_setter_$SafeLongTag_$eq(final NumberTag x$1) {
      SafeLongTag = x$1;
   }

   public final SafeLong minusOne() {
      return minusOne;
   }

   public final SafeLong zero() {
      return zero;
   }

   public final SafeLong one() {
      return one;
   }

   public final SafeLong two() {
      return two;
   }

   public final SafeLong three() {
      return three;
   }

   public final SafeLong ten() {
      return ten;
   }

   public final BigInteger big64() {
      return big64;
   }

   public final SafeLong safe64() {
      return safe64;
   }

   public SafeLong apply(final int x) {
      return new SafeLongLong((long)x);
   }

   public SafeLong apply(final long x) {
      return new SafeLongLong(x);
   }

   public SafeLong apply(final BigInt x) {
      return (SafeLong)(x.isValidLong() ? new SafeLongLong(x.toLong()) : new SafeLongBigInteger(x.bigInteger()));
   }

   public SafeLong apply(final String s) {
      SafeLong var10000;
      try {
         var10000 = this.apply(Long.parseLong(s));
      } catch (Exception var2) {
         var10000 = this.apply(.MODULE$.javaBigInteger2bigInt(new BigInteger(s)));
      }

      return var10000;
   }

   public SafeLong longGcd(final long x, final long y) {
      return (SafeLong)(x == 0L ? absWrap$1(y) : (y == 0L ? absWrap$1(x) : (x == Long.MIN_VALUE ? (y == Long.MIN_VALUE ? this.safe64() : this.apply(package$.MODULE$.gcd(y, x % y))) : (y == Long.MIN_VALUE ? new SafeLongLong(package$.MODULE$.gcd(x, y % x)) : new SafeLongLong(package$.MODULE$.gcd(x, y % x))))));
   }

   public SafeLong mixedGcd(final long x, final BigInteger y) {
      return (SafeLong)(y.signum() == 0 ? (x >= 0L ? new SafeLongLong(x) : (x == Long.MIN_VALUE ? this.safe64() : new SafeLongLong(-x))) : (x == 0L ? this.apply(.MODULE$.javaBigInteger2bigInt(y.abs())) : (x == Long.MIN_VALUE ? this.apply(.MODULE$.javaBigInteger2bigInt(this.big64().gcd(y))) : new SafeLongLong(package$.MODULE$.gcd(x, y.remainder(BigInteger.valueOf(x)).longValue())))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SafeLong$.class);
   }

   private final void SafeLongAlgebra$lzycompute$1() {
      synchronized(this){}

      try {
         if (SafeLongAlgebra$module == null) {
            SafeLongAlgebra$module = new SafeLongInstances.SafeLongAlgebra$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void SafeLongIsReal$lzycompute$1() {
      synchronized(this){}

      try {
         if (SafeLongIsReal$module == null) {
            SafeLongIsReal$module = new SafeLongInstances.SafeLongIsReal$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private static final SafeLong absWrap$1(final long x) {
      return x >= 0L ? MODULE$.apply(x) : (x == Long.MIN_VALUE ? MODULE$.safe64() : MODULE$.apply(-x));
   }

   private SafeLong$() {
   }
}
