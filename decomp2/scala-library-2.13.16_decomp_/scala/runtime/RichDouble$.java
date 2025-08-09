package scala.runtime;

import scala.math.Fractional;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.math.Ordering$Double$TotalOrdering$;
import scala.math.package$;

public final class RichDouble$ {
   public static final RichDouble$ MODULE$ = new RichDouble$();

   public final Fractional num$extension(final double $this) {
      return Numeric.DoubleIsFractional$.MODULE$;
   }

   public final Ordering ord$extension(final double $this) {
      return Ordering$Double$TotalOrdering$.MODULE$;
   }

   public final double doubleValue$extension(final double $this) {
      return $this;
   }

   public final float floatValue$extension(final double $this) {
      return (float)$this;
   }

   public final long longValue$extension(final double $this) {
      return (long)$this;
   }

   public final int intValue$extension(final double $this) {
      return (int)$this;
   }

   public final byte byteValue$extension(final double $this) {
      return (byte)((int)$this);
   }

   public final short shortValue$extension(final double $this) {
      return (short)((int)$this);
   }

   public final boolean isWhole$extension(final double $this) {
      long l = (long)$this;
      return (double)l == $this || l == Long.MAX_VALUE && $this < Double.POSITIVE_INFINITY || l == Long.MIN_VALUE && $this > Double.NEGATIVE_INFINITY;
   }

   public final boolean isValidByte$extension(final double $this) {
      return (double)((byte)((int)$this)) == $this;
   }

   public final boolean isValidShort$extension(final double $this) {
      return (double)((short)((int)$this)) == $this;
   }

   public final boolean isValidChar$extension(final double $this) {
      return (double)((char)((int)$this)) == $this;
   }

   public final boolean isValidInt$extension(final double $this) {
      return (double)((int)$this) == $this;
   }

   public final boolean isNaN$extension(final double $this) {
      return Double.isNaN($this);
   }

   public final boolean isInfinity$extension(final double $this) {
      return Double.isInfinite($this);
   }

   public final boolean isFinite$extension(final double $this) {
      return Double.isFinite($this);
   }

   public final boolean isPosInfinity$extension(final double $this) {
      return Double.POSITIVE_INFINITY == $this;
   }

   public final boolean isNegInfinity$extension(final double $this) {
      return Double.NEGATIVE_INFINITY == $this;
   }

   public final double abs$extension(final double $this) {
      package$ var10000 = package$.MODULE$;
      return Math.abs($this);
   }

   public final double max$extension(final double $this, final double that) {
      package$ var10000 = package$.MODULE$;
      return Math.max($this, that);
   }

   public final double min$extension(final double $this, final double that) {
      package$ var10000 = package$.MODULE$;
      return Math.min($this, that);
   }

   /** @deprecated */
   public final int signum$extension(final double $this) {
      package$ var10000 = package$.MODULE$;
      return (int)Math.signum($this);
   }

   public final long round$extension(final double $this) {
      package$ var10000 = package$.MODULE$;
      return Math.round($this);
   }

   public final double ceil$extension(final double $this) {
      package$ var10000 = package$.MODULE$;
      return Math.ceil($this);
   }

   public final double floor$extension(final double $this) {
      package$ var10000 = package$.MODULE$;
      return Math.floor($this);
   }

   public final double toRadians$extension(final double $this) {
      package$ var10000 = package$.MODULE$;
      return Math.toRadians($this);
   }

   public final double toDegrees$extension(final double $this) {
      package$ var10000 = package$.MODULE$;
      return Math.toDegrees($this);
   }

   public final int hashCode$extension(final double $this) {
      return Double.hashCode($this);
   }

   public final boolean equals$extension(final double $this, final Object x$1) {
      if (x$1 instanceof RichDouble) {
         double var4 = ((RichDouble)x$1).self();
         if ($this == var4) {
            return true;
         }
      }

      return false;
   }

   private RichDouble$() {
   }
}
