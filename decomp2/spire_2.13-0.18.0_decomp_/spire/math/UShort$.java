package spire.math;

import algebra.ring.CommutativeRig;
import scala.math.BigInt;
import scala.package.;

public final class UShort$ implements UShortInstances {
   public static final UShort$ MODULE$ = new UShort$();
   private static CommutativeRig UShortAlgebra;
   private static BitString UShortBitString;
   private static NumberTag UShortTag;

   static {
      UShortInstances.$init$(MODULE$);
   }

   public final CommutativeRig UShortAlgebra() {
      return UShortAlgebra;
   }

   public final BitString UShortBitString() {
      return UShortBitString;
   }

   public final NumberTag UShortTag() {
      return UShortTag;
   }

   public final void spire$math$UShortInstances$_setter_$UShortAlgebra_$eq(final CommutativeRig x$1) {
      UShortAlgebra = x$1;
   }

   public final void spire$math$UShortInstances$_setter_$UShortBitString_$eq(final BitString x$1) {
      UShortBitString = x$1;
   }

   public final void spire$math$UShortInstances$_setter_$UShortTag_$eq(final NumberTag x$1) {
      UShortTag = x$1;
   }

   public final char apply(final char n) {
      return n;
   }

   public final char apply(final short n) {
      return (char)n;
   }

   public final char apply(final int n) {
      return (char)n;
   }

   public final char MinValue() {
      return this.apply((int)0);
   }

   public final char MaxValue() {
      return this.apply('\uffff');
   }

   public final byte toByte$extension(final char $this) {
      return (byte)$this;
   }

   public final char toChar$extension(final char $this) {
      return $this;
   }

   public final short toShort$extension(final char $this) {
      return (short)$this;
   }

   public final int toInt$extension(final char $this) {
      return $this;
   }

   public final long toLong$extension(final char $this) {
      return (long)$this;
   }

   public final float toFloat$extension(final char $this) {
      return (float)$this;
   }

   public final double toDouble$extension(final char $this) {
      return (double)$this;
   }

   public final BigInt toBigInt$extension(final char $this) {
      return .MODULE$.BigInt().apply(this.toInt$extension($this));
   }

   public final boolean isValidByte$extension(final char $this) {
      return $this == this.toByte$extension($this);
   }

   public final boolean isValidShort$extension(final char $this) {
      return $this == this.toShort$extension($this);
   }

   public final boolean isValidChar$extension(final char $this) {
      return true;
   }

   public final boolean isValidInt$extension(final char $this) {
      return true;
   }

   public final boolean isValidLong$extension(final char $this) {
      return true;
   }

   public final String toString$extension(final char $this) {
      return Integer.toString(this.toInt$extension($this));
   }

   public final boolean $eq$eq$extension(final char $this, final char that) {
      return $this == that;
   }

   public final boolean $bang$eq$extension(final char $this, final char that) {
      return $this != that;
   }

   public final boolean $eq$eq$eq$extension(final char $this, final char that) {
      return $this == that;
   }

   public final boolean $eq$bang$eq$extension(final char $this, final char that) {
      return $this != that;
   }

   public final boolean $less$eq$extension(final char $this, final char that) {
      return $this <= that;
   }

   public final boolean $less$extension(final char $this, final char that) {
      return $this < that;
   }

   public final boolean $greater$eq$extension(final char $this, final char that) {
      return $this >= that;
   }

   public final boolean $greater$extension(final char $this, final char that) {
      return $this > that;
   }

   public final char unary_$minus$extension(final char $this) {
      return this.apply(-$this);
   }

   public final char $plus$extension(final char $this, final char that) {
      return this.apply($this + that);
   }

   public final char $minus$extension(final char $this, final char that) {
      return this.apply($this - that);
   }

   public final char $times$extension(final char $this, final char that) {
      return this.apply($this * that);
   }

   public final char $div$extension(final char $this, final char that) {
      return this.apply($this / that);
   }

   public final char $percent$extension(final char $this, final char that) {
      return this.apply($this % that);
   }

   public final char unary_$tilde$extension(final char $this) {
      return this.apply(~$this);
   }

   public final char $less$less$extension(final char $this, final int shift) {
      return this.apply(($this & '\uffff') << (shift & 15));
   }

   public final char $greater$greater$extension(final char $this, final int shift) {
      return this.apply(($this & '\uffff') >>> (shift & 15));
   }

   public final char $greater$greater$greater$extension(final char $this, final int shift) {
      return this.apply(($this & '\uffff') >>> (shift & 15));
   }

   public final char $amp$extension(final char $this, final char that) {
      return this.apply((int)((char)($this & that)));
   }

   public final char $bar$extension(final char $this, final char that) {
      return this.apply((int)((char)($this | that)));
   }

   public final char $up$extension(final char $this, final char that) {
      return this.apply((int)((char)($this ^ that)));
   }

   public final char $times$times$extension(final char $this, final char that) {
      return this.apply((char)((int)package$.MODULE$.pow(this.toLong$extension($this), this.toLong$extension(that))));
   }

   public final int hashCode$extension(final char $this) {
      return Character.hashCode($this);
   }

   public final boolean equals$extension(final char $this, final Object x$1) {
      boolean var3;
      if (x$1 instanceof UShort) {
         var3 = true;
      } else {
         var3 = false;
      }

      boolean var10000;
      if (var3) {
         char var5 = ((UShort)x$1).signed();
         if ($this == var5) {
            var10000 = true;
            return var10000;
         }
      }

      var10000 = false;
      return var10000;
   }

   private UShort$() {
   }
}
