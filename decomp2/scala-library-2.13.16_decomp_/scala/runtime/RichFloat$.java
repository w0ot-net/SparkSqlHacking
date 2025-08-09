package scala.runtime;

import scala.math.Fractional;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.math.Ordering$Float$TotalOrdering$;
import scala.math.package$;

public final class RichFloat$ {
   public static final RichFloat$ MODULE$ = new RichFloat$();

   public final Fractional num$extension(final float $this) {
      return Numeric.FloatIsFractional$.MODULE$;
   }

   public final Ordering ord$extension(final float $this) {
      return Ordering$Float$TotalOrdering$.MODULE$;
   }

   public final double doubleValue$extension(final float $this) {
      return (double)$this;
   }

   public final float floatValue$extension(final float $this) {
      return $this;
   }

   public final long longValue$extension(final float $this) {
      return (long)$this;
   }

   public final int intValue$extension(final float $this) {
      return (int)$this;
   }

   public final byte byteValue$extension(final float $this) {
      return (byte)((int)$this);
   }

   public final short shortValue$extension(final float $this) {
      return (short)((int)$this);
   }

   public final boolean isWhole$extension(final float $this) {
      long l = (long)$this;
      return (float)l == $this || l == Long.MAX_VALUE && $this < Float.POSITIVE_INFINITY || l == Long.MIN_VALUE && $this > Float.NEGATIVE_INFINITY;
   }

   public final boolean isValidByte$extension(final float $this) {
      return (float)((byte)((int)$this)) == $this;
   }

   public final boolean isValidShort$extension(final float $this) {
      return (float)((short)((int)$this)) == $this;
   }

   public final boolean isValidChar$extension(final float $this) {
      return (float)((char)((int)$this)) == $this;
   }

   public final boolean isValidInt$extension(final float $this) {
      int i = (int)$this;
      return (float)i == $this && i != Integer.MAX_VALUE;
   }

   public final boolean isNaN$extension(final float $this) {
      return Float.isNaN($this);
   }

   public final boolean isInfinity$extension(final float $this) {
      return Float.isInfinite($this);
   }

   public final boolean isFinite$extension(final float $this) {
      return Float.isFinite($this);
   }

   public final boolean isPosInfinity$extension(final float $this) {
      return Float.POSITIVE_INFINITY == $this;
   }

   public final boolean isNegInfinity$extension(final float $this) {
      return Float.NEGATIVE_INFINITY == $this;
   }

   public final float abs$extension(final float $this) {
      package$ var10000 = package$.MODULE$;
      return Math.abs($this);
   }

   public final float max$extension(final float $this, final float that) {
      package$ var10000 = package$.MODULE$;
      return Math.max($this, that);
   }

   public final float min$extension(final float $this, final float that) {
      package$ var10000 = package$.MODULE$;
      return Math.min($this, that);
   }

   /** @deprecated */
   public final int signum$extension(final float $this) {
      package$ var10000 = package$.MODULE$;
      return (int)Math.signum($this);
   }

   public final int round$extension(final float $this) {
      package$ var10000 = package$.MODULE$;
      return Math.round($this);
   }

   public final float ceil$extension(final float $this) {
      package$ var10000 = package$.MODULE$;
      return (float)Math.ceil((double)$this);
   }

   public final float floor$extension(final float $this) {
      package$ var10000 = package$.MODULE$;
      return (float)Math.floor((double)$this);
   }

   public final float toRadians$extension(final float $this) {
      package$ var10000 = package$.MODULE$;
      return (float)Math.toRadians((double)$this);
   }

   public final float toDegrees$extension(final float $this) {
      package$ var10000 = package$.MODULE$;
      return (float)Math.toDegrees((double)$this);
   }

   public final int hashCode$extension(final float $this) {
      return Float.hashCode($this);
   }

   public final boolean equals$extension(final float $this, final Object x$1) {
      if (x$1 instanceof RichFloat) {
         float var3 = ((RichFloat)x$1).self();
         if ($this == var3) {
            return true;
         }
      }

      return false;
   }

   private RichFloat$() {
   }
}
