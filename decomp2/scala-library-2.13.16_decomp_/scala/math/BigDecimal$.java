package scala.math;

import java.io.Serializable;
import java.math.MathContext;
import java.math.RoundingMode;
import scala.runtime.ModuleSerializationProxy;

public final class BigDecimal$ implements Serializable {
   public static final BigDecimal$ MODULE$ = new BigDecimal$();
   private static BigDecimal[] cache;
   private static final int minCached = -512;
   private static final int maxCached = 512;
   private static final MathContext defaultMathContext;
   private static volatile boolean bitmap$0;

   static {
      defaultMathContext = MathContext.DECIMAL128;
   }

   private final int maximumHashScale() {
      return 4934;
   }

   private final int hashCodeNotComputed() {
      return 1565550863;
   }

   private final double deci2binary() {
      return 3.3219280948873626;
   }

   public MathContext defaultMathContext() {
      return defaultMathContext;
   }

   private BigDecimal[] cache$lzycompute() {
      synchronized(this){}

      try {
         if (!bitmap$0) {
            cache = new BigDecimal[maxCached - minCached + 1];
            bitmap$0 = true;
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return cache;
   }

   private BigDecimal[] cache() {
      return !bitmap$0 ? this.cache$lzycompute() : cache;
   }

   public BigDecimal decimal(final double d, final MathContext mc) {
      return new BigDecimal(new java.math.BigDecimal(Double.toString(d), mc), mc);
   }

   public BigDecimal decimal(final double d) {
      return this.decimal(d, this.defaultMathContext());
   }

   public BigDecimal decimal(final float f, final MathContext mc) {
      return new BigDecimal(new java.math.BigDecimal(Float.toString(f), mc), mc);
   }

   public BigDecimal decimal(final float f) {
      return this.decimal(f, this.defaultMathContext());
   }

   public BigDecimal decimal(final long l, final MathContext mc) {
      return this.apply(l, mc);
   }

   public BigDecimal decimal(final long l) {
      return this.apply(l);
   }

   public BigDecimal decimal(final java.math.BigDecimal bd, final MathContext mc) {
      return new BigDecimal(bd.round(mc), mc);
   }

   public BigDecimal binary(final double d, final MathContext mc) {
      return new BigDecimal(new java.math.BigDecimal(d, mc), mc);
   }

   public BigDecimal binary(final double d) {
      return this.binary(d, this.defaultMathContext());
   }

   public BigDecimal exact(final java.math.BigDecimal repr) {
      MathContext mc = repr.precision() <= this.defaultMathContext().getPrecision() ? this.defaultMathContext() : new MathContext(repr.precision(), RoundingMode.HALF_EVEN);
      return new BigDecimal(repr, mc);
   }

   public BigDecimal exact(final double d) {
      return this.exact(new java.math.BigDecimal(d));
   }

   public BigDecimal exact(final BigInt bi) {
      return this.exact(new java.math.BigDecimal(bi.bigInteger()));
   }

   public BigDecimal exact(final long l) {
      return this.apply(l);
   }

   public BigDecimal exact(final String s) {
      return this.exact(new java.math.BigDecimal(s));
   }

   public BigDecimal exact(final char[] cs) {
      return this.exact(new java.math.BigDecimal(cs));
   }

   public BigDecimal valueOf(final double d) {
      return this.apply(java.math.BigDecimal.valueOf(d));
   }

   public BigDecimal valueOf(final long x) {
      return this.apply(x);
   }

   public BigDecimal apply(final int i) {
      return this.apply(i, this.defaultMathContext());
   }

   public BigDecimal apply(final int i, final MathContext mc) {
      MathContext var3 = this.defaultMathContext();
      if (mc == null) {
         if (var3 != null) {
            return this.apply((long)i, mc);
         }
      } else if (!mc.equals(var3)) {
         return this.apply((long)i, mc);
      }

      if (minCached <= i && i <= maxCached) {
         int offset = i - minCached;
         BigDecimal n = this.cache()[offset];
         if (n == null) {
            n = new BigDecimal(java.math.BigDecimal.valueOf((long)i), mc);
            this.cache()[offset] = n;
         }

         return n;
      } else {
         return this.apply((long)i, mc);
      }
   }

   public BigDecimal apply(final long l) {
      return (long)minCached <= l && l <= (long)maxCached ? this.apply((int)l) : new BigDecimal(java.math.BigDecimal.valueOf(l), this.defaultMathContext());
   }

   public BigDecimal apply(final long l, final MathContext mc) {
      return new BigDecimal(new java.math.BigDecimal(l, mc), mc);
   }

   public BigDecimal apply(final long unscaledVal, final int scale) {
      return this.apply(BigInt$.MODULE$.apply(unscaledVal), scale);
   }

   public BigDecimal apply(final long unscaledVal, final int scale, final MathContext mc) {
      return this.apply(BigInt$.MODULE$.apply(unscaledVal), scale, mc);
   }

   public BigDecimal apply(final double d) {
      return this.decimal(d, this.defaultMathContext());
   }

   public BigDecimal apply(final double d, final MathContext mc) {
      return this.decimal(d, mc);
   }

   public BigDecimal apply(final char[] x) {
      return this.exact(x);
   }

   public BigDecimal apply(final char[] x, final MathContext mc) {
      return new BigDecimal(new java.math.BigDecimal(x, mc), mc);
   }

   public BigDecimal apply(final String x) {
      return this.exact(x);
   }

   public BigDecimal apply(final String x, final MathContext mc) {
      return new BigDecimal(new java.math.BigDecimal(x, mc), mc);
   }

   public BigDecimal apply(final BigInt x) {
      return this.exact(x);
   }

   public BigDecimal apply(final BigInt x, final MathContext mc) {
      return new BigDecimal(new java.math.BigDecimal(x.bigInteger(), mc), mc);
   }

   public BigDecimal apply(final BigInt unscaledVal, final int scale) {
      return this.exact(new java.math.BigDecimal(unscaledVal.bigInteger(), scale));
   }

   public BigDecimal apply(final BigInt unscaledVal, final int scale, final MathContext mc) {
      return new BigDecimal(new java.math.BigDecimal(unscaledVal.bigInteger(), scale, mc), mc);
   }

   public BigDecimal apply(final java.math.BigDecimal bd) {
      return new BigDecimal(bd, this.defaultMathContext());
   }

   public BigDecimal int2bigDecimal(final int i) {
      return this.apply(i);
   }

   public BigDecimal long2bigDecimal(final long l) {
      return this.apply(l);
   }

   public BigDecimal double2bigDecimal(final double d) {
      return this.decimal(d);
   }

   public BigDecimal javaBigDecimal2bigDecimal(final java.math.BigDecimal x) {
      return x == null ? null : this.apply(x);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BigDecimal$.class);
   }

   private BigDecimal$() {
   }
}
