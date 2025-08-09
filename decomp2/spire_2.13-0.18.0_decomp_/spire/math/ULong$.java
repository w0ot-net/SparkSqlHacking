package spire.math;

import algebra.ring.CommutativeRig;
import scala.Tuple2;
import scala.math.BigInt;
import scala.package.;

public final class ULong$ implements ULongInstances {
   public static final ULong$ MODULE$ = new ULong$();
   private static final long MinValue;
   private static final long MaxValue;
   private static final double LimitAsDouble;
   private static final BigInt LimitAsBigInt;
   private static CommutativeRig ULongAlgebra;
   private static BitString ULongBitString;
   private static NumberTag ULongTag;

   static {
      ULongInstances.$init$(MODULE$);
      MinValue = MODULE$.apply(0L);
      MaxValue = MODULE$.apply(-1L);
      LimitAsDouble = package$.MODULE$.pow((double)2.0F, (double)64.0F);
      LimitAsBigInt = .MODULE$.BigInt().apply(1).$less$less(64);
   }

   public final CommutativeRig ULongAlgebra() {
      return ULongAlgebra;
   }

   public final BitString ULongBitString() {
      return ULongBitString;
   }

   public final NumberTag ULongTag() {
      return ULongTag;
   }

   public final void spire$math$ULongInstances$_setter_$ULongAlgebra_$eq(final CommutativeRig x$1) {
      ULongAlgebra = x$1;
   }

   public final void spire$math$ULongInstances$_setter_$ULongBitString_$eq(final BitString x$1) {
      ULongBitString = x$1;
   }

   public final void spire$math$ULongInstances$_setter_$ULongTag_$eq(final NumberTag x$1) {
      ULongTag = x$1;
   }

   public final long apply(final long n) {
      return n;
   }

   public final long apply(final String s) {
      return this.fromBigInt(.MODULE$.BigInt().apply(s));
   }

   public final long fromInt(final int n) {
      return (long)n & 4294967295L;
   }

   public final long fromLong(final long n) {
      return n;
   }

   public final long fromBigInt(final BigInt n) {
      if (n.$less(scala.math.BigInt..MODULE$.int2bigInt(0))) {
         throw new IllegalArgumentException((new StringBuilder(4)).append(n).append(" < 0").toString());
      } else {
         return n.toLong();
      }
   }

   public BigInt ulongToBigInt(final long n) {
      return this.toBigInt$extension(n);
   }

   public final long MinValue() {
      return MinValue;
   }

   public final long MaxValue() {
      return MaxValue;
   }

   public final long pow(final long t, final long b, final long e) {
      while(e != 0L) {
         if ((e & 1L) == 1L) {
            long var10000 = t * b;
            long var7 = b * b;
            e >>>= (int)1L;
            b = var7;
            t = var10000;
         } else {
            long var10001 = b * b;
            e >>>= (int)1L;
            b = var10001;
            t = t;
         }
      }

      return t;
   }

   public final long gcd(final long a, final long b) {
      while(!this.$eq$eq$extension(b, 0L)) {
         long var10000 = b;
         b = this.$percent$extension(a, b);
         a = var10000;
      }

      return a;
   }

   public final double LimitAsDouble() {
      return LimitAsDouble;
   }

   public final BigInt LimitAsBigInt() {
      return LimitAsBigInt;
   }

   public final byte toByte$extension(final long $this) {
      return (byte)((int)$this);
   }

   public final char toChar$extension(final long $this) {
      return (char)((int)$this);
   }

   public final short toShort$extension(final long $this) {
      return (short)((int)$this);
   }

   public final int toInt$extension(final long $this) {
      return (int)$this;
   }

   public final long toLong$extension(final long $this) {
      return $this;
   }

   public final float toFloat$extension(final long $this) {
      return $this < 0L ? (float)(this.LimitAsDouble() + (double)$this) : (float)$this;
   }

   public final double toDouble$extension(final long $this) {
      return this.toBigInt$extension($this).toDouble();
   }

   public final BigInt toBigInt$extension(final long $this) {
      return $this < 0L ? this.LimitAsBigInt().$plus(scala.math.BigInt..MODULE$.long2bigInt($this)) : .MODULE$.BigInt().apply($this);
   }

   public final String toString$extension(final long $this) {
      return $this >= 0L ? Long.toString($this) : this.LimitAsBigInt().$plus(scala.math.BigInt..MODULE$.long2bigInt($this)).toString();
   }

   public final boolean $eq$eq$extension(final long $this, final long that) {
      return $this == that;
   }

   public final boolean $bang$eq$extension(final long $this, final long that) {
      return $this != that;
   }

   public final boolean $eq$eq$eq$extension(final long $this, final long that) {
      return $this == that;
   }

   public final boolean $eq$bang$eq$extension(final long $this, final long that) {
      return $this != that;
   }

   public final boolean $less$eq$extension(final long $this, final long that) {
      return $this >= 0L ? $this <= that || that < 0L : that >= $this && that < 0L;
   }

   public final boolean $less$extension(final long $this, final long that) {
      return $this >= 0L ? $this < that || that < 0L : that > $this && that < 0L;
   }

   public final boolean $greater$eq$extension(final long $this, final long that) {
      return this.$less$eq$extension(that, $this);
   }

   public final boolean $greater$extension(final long $this, final long that) {
      return this.$less$extension(that, $this);
   }

   public final long unary_$minus$extension(final long $this) {
      return this.apply(-$this);
   }

   public final long $plus$extension(final long $this, final long that) {
      return this.apply($this + that);
   }

   public final long $minus$extension(final long $this, final long that) {
      return this.apply($this - that);
   }

   public final long $times$extension(final long $this, final long that) {
      return this.apply($this * that);
   }

   public final long $div$extension(final long $this, final long that) {
      if (that == 0L) {
         throw new ArithmeticException("/ by zero");
      } else {
         long var10000;
         if (that < 0L) {
            var10000 = this.apply((long)($this < 0L && $this >= that ? 1 : 0));
         } else if ($this >= 0L) {
            var10000 = this.apply($this / that);
         } else {
            long half = $this >>> 1;
            var10000 = half < that ? this.apply(1L) : this.apply((half / that << 1) + ((half % that << 1) + ($this & 1L)) / that);
         }

         return var10000;
      }
   }

   public final long $percent$extension(final long $this, final long that) {
      return this.$minus$extension($this, this.$times$extension(this.$div$extension($this, that), that));
   }

   public final Tuple2 $div$percent$extension(final long $this, final long that) {
      long q = this.$div$extension($this, that);
      return new Tuple2(new ULong(q), new ULong(this.$minus$extension($this, this.$times$extension(q, that))));
   }

   public final long unary_$tilde$extension(final long $this) {
      return this.apply(~$this);
   }

   public final long $less$less$extension(final long $this, final int shift) {
      return this.apply($this << shift);
   }

   public final long $greater$greater$extension(final long $this, final int shift) {
      return this.apply($this >>> shift);
   }

   public final long $greater$greater$greater$extension(final long $this, final int shift) {
      return this.apply($this >>> shift);
   }

   public final long $amp$extension(final long $this, final long that) {
      return this.apply($this & that);
   }

   public final long $bar$extension(final long $this, final long that) {
      return this.apply($this | that);
   }

   public final long $up$extension(final long $this, final long that) {
      return this.apply($this ^ that);
   }

   public final long $times$times$extension(final long $this, final long that) {
      return this.pow(1L, $this, that);
   }

   public final long gcd$extension(final long $this, final long that) {
      return this.gcd($this, that);
   }

   public final int hashCode$extension(final long $this) {
      return Long.hashCode($this);
   }

   public final boolean equals$extension(final long $this, final Object x$1) {
      boolean var4;
      if (x$1 instanceof ULong) {
         var4 = true;
      } else {
         var4 = false;
      }

      boolean var10000;
      if (var4) {
         long var6 = ((ULong)x$1).signed();
         if ($this == var6) {
            var10000 = true;
            return var10000;
         }
      }

      var10000 = false;
      return var10000;
   }

   private ULong$() {
   }
}
