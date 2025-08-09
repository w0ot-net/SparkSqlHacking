package spire.math;

import algebra.ring.Field;
import java.io.Serializable;
import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.package.;
import scala.runtime.ModuleSerializationProxy;

public final class Number$ implements NumberInstances, Serializable {
   public static final Number$ MODULE$ = new Number$();
   private static final Number zero;
   private static final Number one;
   private static final SafeLong minInt;
   private static final SafeLong maxInt;
   private static final SafeLong minLong;
   private static final SafeLong maxLong;
   private static final BigDecimal minDouble;
   private static final BigDecimal maxDouble;
   private static Field NumberAlgebra;

   static {
      NumberInstances.$init$(MODULE$);
      zero = MODULE$.apply(0);
      one = MODULE$.apply(1);
      minInt = SafeLong$.MODULE$.apply(Integer.MIN_VALUE);
      maxInt = SafeLong$.MODULE$.apply(Integer.MAX_VALUE);
      minLong = SafeLong$.MODULE$.apply(Long.MIN_VALUE);
      maxLong = SafeLong$.MODULE$.apply(Long.MAX_VALUE);
      minDouble = .MODULE$.BigDecimal().apply(scala.Double..MODULE$.MinValue());
      maxDouble = .MODULE$.BigDecimal().apply(Double.MAX_VALUE);
   }

   public final Field NumberAlgebra() {
      return NumberAlgebra;
   }

   public final void spire$math$NumberInstances$_setter_$NumberAlgebra_$eq(final Field x$1) {
      NumberAlgebra = x$1;
   }

   public final Number zero() {
      return zero;
   }

   public final Number one() {
      return one;
   }

   public Number apply(final int n) {
      return new IntNumber(SafeLong$.MODULE$.apply(n));
   }

   public Number apply(final long n) {
      return new IntNumber(SafeLong$.MODULE$.apply(n));
   }

   public Number apply(final BigInt n) {
      return new IntNumber(SafeLong$.MODULE$.apply(n));
   }

   public Number apply(final SafeLong n) {
      return new IntNumber(n);
   }

   public Number apply(final BigDecimal n) {
      return new DecimalNumber(n);
   }

   public Number apply(final Rational n) {
      return new RationalNumber(n);
   }

   public Number apply(final Natural n) {
      return new IntNumber(SafeLong$.MODULE$.apply(n.toBigInt()));
   }

   public Number apply(final float n) {
      if (!Float.isNaN(n) && !Float.isInfinite(n)) {
         return new FloatNumber((double)n);
      } else {
         throw new IllegalArgumentException(Float.toString(n));
      }
   }

   public Number apply(final double n) {
      if (!Double.isNaN(n) && !Double.isInfinite(n)) {
         return new FloatNumber(n);
      } else {
         throw new IllegalArgumentException(Double.toString(n));
      }
   }

   public Number apply(final String s) {
      Number var10000;
      try {
         var10000 = this.apply(SafeLong$.MODULE$.apply(s));
      } catch (Exception var2) {
         var10000 = this.apply(.MODULE$.BigDecimal().apply(s));
      }

      return var10000;
   }

   public SafeLong minInt() {
      return minInt;
   }

   public SafeLong maxInt() {
      return maxInt;
   }

   public SafeLong minLong() {
      return minLong;
   }

   public SafeLong maxLong() {
      return maxLong;
   }

   public BigDecimal minDouble() {
      return minDouble;
   }

   public BigDecimal maxDouble() {
      return maxDouble;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Number$.class);
   }

   private Number$() {
   }
}
