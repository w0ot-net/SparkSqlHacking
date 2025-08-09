package scala.runtime;

import scala.math.Numeric;
import scala.math.Ordering;
import scala.math.package$;

public final class RichByte$ {
   public static final RichByte$ MODULE$ = new RichByte$();

   public final Numeric.ByteIsIntegral$ num$extension(final byte $this) {
      return Numeric.ByteIsIntegral$.MODULE$;
   }

   public final Ordering.Byte$ ord$extension(final byte $this) {
      return Ordering.Byte$.MODULE$;
   }

   public final double doubleValue$extension(final byte $this) {
      return (double)$this;
   }

   public final float floatValue$extension(final byte $this) {
      return (float)$this;
   }

   public final long longValue$extension(final byte $this) {
      return (long)$this;
   }

   public final int intValue$extension(final byte $this) {
      return $this;
   }

   public final byte byteValue$extension(final byte $this) {
      return $this;
   }

   public final short shortValue$extension(final byte $this) {
      return $this;
   }

   public final boolean isValidByte$extension(final byte $this) {
      return true;
   }

   public final byte abs$extension(final byte $this) {
      package$ var10000 = package$.MODULE$;
      return (byte)Math.abs($this);
   }

   public final byte max$extension(final byte $this, final byte that) {
      package$ var10000 = package$.MODULE$;
      return (byte)Math.max($this, that);
   }

   public final byte min$extension(final byte $this, final byte that) {
      package$ var10000 = package$.MODULE$;
      return (byte)Math.min($this, that);
   }

   public final int hashCode$extension(final byte $this) {
      return Byte.hashCode($this);
   }

   public final boolean equals$extension(final byte $this, final Object x$1) {
      if (x$1 instanceof RichByte) {
         byte var3 = ((RichByte)x$1).self();
         if ($this == var3) {
            return true;
         }
      }

      return false;
   }

   private RichByte$() {
   }
}
