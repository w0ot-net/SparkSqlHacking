package spire.math;

import algebra.ring.CommutativeRig;
import scala.math.BigInt;
import scala.package.;
import scala.runtime.BoxesRunTime;

public final class UByte$ implements UByteInstances {
   public static final UByte$ MODULE$ = new UByte$();
   private static CommutativeRig UByteAlgebra;
   private static BitString UByteBitString;
   private static NumberTag UByteTag;

   static {
      UByteInstances.$init$(MODULE$);
   }

   public final CommutativeRig UByteAlgebra() {
      return UByteAlgebra;
   }

   public final BitString UByteBitString() {
      return UByteBitString;
   }

   public final NumberTag UByteTag() {
      return UByteTag;
   }

   public final void spire$math$UByteInstances$_setter_$UByteAlgebra_$eq(final CommutativeRig x$1) {
      UByteAlgebra = x$1;
   }

   public final void spire$math$UByteInstances$_setter_$UByteBitString_$eq(final BitString x$1) {
      UByteBitString = x$1;
   }

   public final void spire$math$UByteInstances$_setter_$UByteTag_$eq(final NumberTag x$1) {
      UByteTag = x$1;
   }

   public final byte apply(final byte n) {
      return n;
   }

   public final byte apply(final int n) {
      return (byte)n;
   }

   public final byte MinValue() {
      return this.apply((int)0);
   }

   public final byte MaxValue() {
      return this.apply((int)-1);
   }

   public final byte toByte$extension(final byte $this) {
      return $this;
   }

   public final char toChar$extension(final byte $this) {
      return (char)($this & 255);
   }

   public final short toShort$extension(final byte $this) {
      return (short)($this & 255);
   }

   public final int toInt$extension(final byte $this) {
      return $this & 255;
   }

   public final long toLong$extension(final byte $this) {
      return (long)$this & 255L;
   }

   public final float toFloat$extension(final byte $this) {
      return (float)this.toInt$extension($this);
   }

   public final double toDouble$extension(final byte $this) {
      return (double)this.toInt$extension($this);
   }

   public final BigInt toBigInt$extension(final byte $this) {
      return .MODULE$.BigInt().apply(this.toInt$extension($this));
   }

   public final byte byteValue$extension(final byte $this) {
      return this.toByte$extension($this);
   }

   public final short shortValue$extension(final byte $this) {
      return this.toShort$extension($this);
   }

   public final int intValue$extension(final byte $this) {
      return this.toInt$extension($this);
   }

   public final long longValue$extension(final byte $this) {
      return this.toLong$extension($this);
   }

   public final float floatValue$extension(final byte $this) {
      return this.toFloat$extension($this);
   }

   public final double doubleValue$extension(final byte $this) {
      return this.toDouble$extension($this);
   }

   public final boolean isWhole$extension(final byte $this) {
      return true;
   }

   public final Object underlying$extension(final byte $this) {
      return BoxesRunTime.boxToByte($this);
   }

   public final boolean isValidByte$extension(final byte $this) {
      return $this >= 0;
   }

   public final boolean isValidShort$extension(final byte $this) {
      return true;
   }

   public final boolean isValidChar$extension(final byte $this) {
      return true;
   }

   public final boolean isValidInt$extension(final byte $this) {
      return true;
   }

   public final boolean isValidLong$extension(final byte $this) {
      return true;
   }

   public final String toString$extension(final byte $this) {
      return Integer.toString(this.toInt$extension($this));
   }

   public final boolean $eq$eq$extension(final byte $this, final byte that) {
      return $this == that;
   }

   public final boolean $bang$eq$extension(final byte $this, final byte that) {
      return $this != that;
   }

   public final boolean $eq$eq$eq$extension(final byte $this, final byte that) {
      return $this == that;
   }

   public final boolean $eq$bang$eq$extension(final byte $this, final byte that) {
      return $this != that;
   }

   public final boolean $less$eq$extension(final byte $this, final byte that) {
      return this.toInt$extension($this) <= this.toInt$extension(that);
   }

   public final boolean $less$extension(final byte $this, final byte that) {
      return this.toInt$extension($this) < this.toInt$extension(that);
   }

   public final boolean $greater$eq$extension(final byte $this, final byte that) {
      return this.toInt$extension($this) >= this.toInt$extension(that);
   }

   public final boolean $greater$extension(final byte $this, final byte that) {
      return this.toInt$extension($this) > this.toInt$extension(that);
   }

   public final byte unary_$minus$extension(final byte $this) {
      return this.apply(-$this);
   }

   public final byte $plus$extension(final byte $this, final byte that) {
      return this.apply($this + that);
   }

   public final byte $minus$extension(final byte $this, final byte that) {
      return this.apply($this - that);
   }

   public final byte $times$extension(final byte $this, final byte that) {
      return this.apply($this * that);
   }

   public final byte $div$extension(final byte $this, final byte that) {
      return this.apply(this.toInt$extension($this) / this.toInt$extension(that));
   }

   public final byte $percent$extension(final byte $this, final byte that) {
      return this.apply(this.toInt$extension($this) % this.toInt$extension(that));
   }

   public final byte unary_$tilde$extension(final byte $this) {
      return this.apply(~$this);
   }

   public final byte $less$less$extension(final byte $this, final int shift) {
      return this.apply(($this & 255) << (shift & 7));
   }

   public final byte $greater$greater$extension(final byte $this, final int shift) {
      return this.apply(($this & 255) >>> (shift & 7));
   }

   public final byte $greater$greater$greater$extension(final byte $this, final int shift) {
      return this.apply(($this & 255) >>> (shift & 7));
   }

   public final byte $amp$extension(final byte $this, final byte that) {
      return this.apply($this & 255 & that & 255);
   }

   public final byte $bar$extension(final byte $this, final byte that) {
      return this.apply($this & 255 | that & 255);
   }

   public final byte $up$extension(final byte $this, final byte that) {
      return this.apply($this & 255 ^ that & 255);
   }

   public final byte $times$times$extension(final byte $this, final byte that) {
      return this.apply((int)package$.MODULE$.pow(this.toLong$extension($this), this.toLong$extension(that)));
   }

   public final int hashCode$extension(final byte $this) {
      return Byte.hashCode($this);
   }

   public final boolean equals$extension(final byte $this, final Object x$1) {
      boolean var3;
      if (x$1 instanceof UByte) {
         var3 = true;
      } else {
         var3 = false;
      }

      boolean var10000;
      if (var3) {
         byte var5 = ((UByte)x$1).signed();
         if ($this == var5) {
            var10000 = true;
            return var10000;
         }
      }

      var10000 = false;
      return var10000;
   }

   private UByte$() {
   }
}
