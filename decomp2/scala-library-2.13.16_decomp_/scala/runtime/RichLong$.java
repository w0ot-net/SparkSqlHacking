package scala.runtime;

import scala.math.Numeric;
import scala.math.Ordering;
import scala.math.package$;

public final class RichLong$ {
   public static final RichLong$ MODULE$ = new RichLong$();

   public final Numeric.LongIsIntegral$ num$extension(final long $this) {
      return Numeric.LongIsIntegral$.MODULE$;
   }

   public final Ordering.Long$ ord$extension(final long $this) {
      return Ordering.Long$.MODULE$;
   }

   public final double doubleValue$extension(final long $this) {
      return (double)$this;
   }

   public final float floatValue$extension(final long $this) {
      return (float)$this;
   }

   public final long longValue$extension(final long $this) {
      return $this;
   }

   public final int intValue$extension(final long $this) {
      return (int)$this;
   }

   public final byte byteValue$extension(final long $this) {
      return (byte)((int)$this);
   }

   public final short shortValue$extension(final long $this) {
      return (short)((int)$this);
   }

   public final boolean isValidByte$extension(final long $this) {
      return (long)((byte)((int)$this)) == $this;
   }

   public final boolean isValidShort$extension(final long $this) {
      return (long)((short)((int)$this)) == $this;
   }

   public final boolean isValidChar$extension(final long $this) {
      return (long)((char)((int)$this)) == $this;
   }

   public final boolean isValidInt$extension(final long $this) {
      return (long)((int)$this) == $this;
   }

   public final boolean isValidLong$extension(final long $this) {
      return true;
   }

   public final long abs$extension(final long $this) {
      package$ var10000 = package$.MODULE$;
      return Math.abs($this);
   }

   public final long max$extension(final long $this, final long that) {
      package$ var10000 = package$.MODULE$;
      return Math.max($this, that);
   }

   public final long min$extension(final long $this, final long that) {
      package$ var10000 = package$.MODULE$;
      return Math.min($this, that);
   }

   /** @deprecated */
   public final long round$extension(final long $this) {
      return $this;
   }

   public final String toBinaryString$extension(final long $this) {
      return Long.toBinaryString($this);
   }

   public final String toHexString$extension(final long $this) {
      return Long.toHexString($this);
   }

   public final String toOctalString$extension(final long $this) {
      return Long.toOctalString($this);
   }

   public final int hashCode$extension(final long $this) {
      return Long.hashCode($this);
   }

   public final boolean equals$extension(final long $this, final Object x$1) {
      if (x$1 instanceof RichLong) {
         long var4 = ((RichLong)x$1).self();
         if ($this == var4) {
            return true;
         }
      }

      return false;
   }

   private RichLong$() {
   }
}
