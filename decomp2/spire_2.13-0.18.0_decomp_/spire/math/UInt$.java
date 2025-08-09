package spire.math;

import algebra.ring.CommutativeRig;
import scala.math.BigInt;
import scala.package.;

public final class UInt$ implements UIntInstances {
   public static final UInt$ MODULE$ = new UInt$();
   private static final int MinValue;
   private static final int MaxValue;
   private static CommutativeRig UIntAlgebra;
   private static BitString UIntBitString;
   private static NumberTag UIntTag;

   static {
      UIntInstances.$init$(MODULE$);
      MinValue = MODULE$.apply(0);
      MaxValue = MODULE$.apply(-1);
   }

   public final CommutativeRig UIntAlgebra() {
      return UIntAlgebra;
   }

   public final BitString UIntBitString() {
      return UIntBitString;
   }

   public final NumberTag UIntTag() {
      return UIntTag;
   }

   public final void spire$math$UIntInstances$_setter_$UIntAlgebra_$eq(final CommutativeRig x$1) {
      UIntAlgebra = x$1;
   }

   public final void spire$math$UIntInstances$_setter_$UIntBitString_$eq(final BitString x$1) {
      UIntBitString = x$1;
   }

   public final void spire$math$UIntInstances$_setter_$UIntTag_$eq(final NumberTag x$1) {
      UIntTag = x$1;
   }

   public final int apply(final int n) {
      return n;
   }

   public final int apply(final long n) {
      return (int)n;
   }

   public final int MinValue() {
      return MinValue;
   }

   public final int MaxValue() {
      return MaxValue;
   }

   public final byte toByte$extension(final int $this) {
      return (byte)$this;
   }

   public final char toChar$extension(final int $this) {
      return (char)$this;
   }

   public final short toShort$extension(final int $this) {
      return (short)$this;
   }

   public final int toInt$extension(final int $this) {
      return $this;
   }

   public final long toLong$extension(final int $this) {
      return (long)$this & 4294967295L;
   }

   public final float toFloat$extension(final int $this) {
      return (float)this.toLong$extension($this);
   }

   public final double toDouble$extension(final int $this) {
      return (double)this.toLong$extension($this);
   }

   public final BigInt toBigInt$extension(final int $this) {
      return .MODULE$.BigInt().apply(this.toLong$extension($this));
   }

   public final boolean isValidByte$extension(final int $this) {
      return this.toInt$extension($this) == this.toByte$extension($this);
   }

   public final boolean isValidShort$extension(final int $this) {
      return this.toInt$extension($this) == this.toShort$extension($this);
   }

   public final boolean isValidChar$extension(final int $this) {
      return this.toInt$extension($this) == this.toChar$extension($this);
   }

   public final boolean isValidInt$extension(final int $this) {
      return $this >= 0;
   }

   public final boolean isValidLong$extension(final int $this) {
      return true;
   }

   public final String toString$extension(final int $this) {
      return Long.toString(this.toLong$extension($this));
   }

   public final boolean $eq$eq$extension(final int $this, final int that) {
      return $this == that;
   }

   public final boolean $bang$eq$extension(final int $this, final int that) {
      return $this != that;
   }

   public final boolean $eq$eq$eq$extension(final int $this, final int that) {
      return $this == that;
   }

   public final boolean $eq$bang$eq$extension(final int $this, final int that) {
      return $this != that;
   }

   public final boolean $less$eq$extension(final int $this, final int that) {
      return this.toLong$extension($this) <= this.toLong$extension(that);
   }

   public final boolean $less$extension(final int $this, final int that) {
      return this.toLong$extension($this) < this.toLong$extension(that);
   }

   public final boolean $greater$eq$extension(final int $this, final int that) {
      return this.toLong$extension($this) >= this.toLong$extension(that);
   }

   public final boolean $greater$extension(final int $this, final int that) {
      return this.toLong$extension($this) > this.toLong$extension(that);
   }

   public final int unary_$minus$extension(final int $this) {
      return this.apply(-$this);
   }

   public final int $plus$extension(final int $this, final int that) {
      return this.apply($this + that);
   }

   public final int $minus$extension(final int $this, final int that) {
      return this.apply($this - that);
   }

   public final int $times$extension(final int $this, final int that) {
      return this.apply($this * that);
   }

   public final int $div$extension(final int $this, final int that) {
      return this.apply(this.toLong$extension($this) / this.toLong$extension(that));
   }

   public final int $percent$extension(final int $this, final int that) {
      return this.apply(this.toLong$extension($this) % this.toLong$extension(that));
   }

   public final int unary_$tilde$extension(final int $this) {
      return this.apply(~$this);
   }

   public final int $less$less$extension(final int $this, final int shift) {
      return this.apply($this << shift);
   }

   public final int $greater$greater$extension(final int $this, final int shift) {
      return this.apply($this >>> shift);
   }

   public final int $greater$greater$greater$extension(final int $this, final int shift) {
      return this.apply($this >>> shift);
   }

   public final int $amp$extension(final int $this, final int that) {
      return this.apply($this & that);
   }

   public final int $bar$extension(final int $this, final int that) {
      return this.apply($this | that);
   }

   public final int $up$extension(final int $this, final int that) {
      return this.apply($this ^ that);
   }

   public final int $times$times$extension(final int $this, final int that) {
      return this.apply(package$.MODULE$.pow(this.toLong$extension($this), this.toLong$extension(that)));
   }

   public final int hashCode$extension(final int $this) {
      return Integer.hashCode($this);
   }

   public final boolean equals$extension(final int $this, final Object x$1) {
      boolean var3;
      if (x$1 instanceof UInt) {
         var3 = true;
      } else {
         var3 = false;
      }

      boolean var10000;
      if (var3) {
         int var5 = ((UInt)x$1).signed();
         if ($this == var5) {
            var10000 = true;
            return var10000;
         }
      }

      var10000 = false;
      return var10000;
   }

   private UInt$() {
   }
}
