package scala.runtime;

import scala.collection.immutable.Range;
import scala.collection.immutable.Range$;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.math.package$;

public final class RichInt$ {
   public static final RichInt$ MODULE$ = new RichInt$();

   public final Numeric.IntIsIntegral$ num$extension(final int $this) {
      return Numeric.IntIsIntegral$.MODULE$;
   }

   public final Ordering.Int$ ord$extension(final int $this) {
      return Ordering.Int$.MODULE$;
   }

   public final double doubleValue$extension(final int $this) {
      return (double)$this;
   }

   public final float floatValue$extension(final int $this) {
      return (float)$this;
   }

   public final long longValue$extension(final int $this) {
      return (long)$this;
   }

   public final int intValue$extension(final int $this) {
      return $this;
   }

   public final byte byteValue$extension(final int $this) {
      return (byte)$this;
   }

   public final short shortValue$extension(final int $this) {
      return (short)$this;
   }

   /** @deprecated */
   public final boolean isWhole$extension(final int $this) {
      return true;
   }

   public final boolean isValidInt$extension(final int $this) {
      return true;
   }

   public final boolean isValidLong$extension(final int $this) {
      return true;
   }

   public final int abs$extension(final int $this) {
      package$ var10000 = package$.MODULE$;
      return Math.abs($this);
   }

   public final int max$extension(final int $this, final int that) {
      package$ var10000 = package$.MODULE$;
      return Math.max($this, that);
   }

   public final int min$extension(final int $this, final int that) {
      package$ var10000 = package$.MODULE$;
      return Math.min($this, that);
   }

   /** @deprecated */
   public final int round$extension(final int $this) {
      return $this;
   }

   public final String toBinaryString$extension(final int $this) {
      return Integer.toBinaryString($this);
   }

   public final String toHexString$extension(final int $this) {
      return Integer.toHexString($this);
   }

   public final String toOctalString$extension(final int $this) {
      return Integer.toOctalString($this);
   }

   public final Range until$extension(final int $this, final int end) {
      Range$ var10000 = Range$.MODULE$;
      return new Range.Exclusive($this, end, 1);
   }

   public final Range until$extension(final int $this, final int end, final int step) {
      Range$ var10000 = Range$.MODULE$;
      return new Range.Exclusive($this, end, step);
   }

   public final Range.Inclusive to$extension(final int $this, final int end) {
      Range$ var10000 = Range$.MODULE$;
      return new Range.Inclusive($this, end, 1);
   }

   public final Range.Inclusive to$extension(final int $this, final int end, final int step) {
      Range$ var10000 = Range$.MODULE$;
      return new Range.Inclusive($this, end, step);
   }

   public final int hashCode$extension(final int $this) {
      return Integer.hashCode($this);
   }

   public final boolean equals$extension(final int $this, final Object x$1) {
      if (x$1 instanceof RichInt) {
         int var3 = ((RichInt)x$1).self();
         if ($this == var3) {
            return true;
         }
      }

      return false;
   }

   private RichInt$() {
   }
}
