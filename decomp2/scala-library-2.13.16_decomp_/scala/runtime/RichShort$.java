package scala.runtime;

import scala.math.Numeric;
import scala.math.Ordering;
import scala.math.package$;

public final class RichShort$ {
   public static final RichShort$ MODULE$ = new RichShort$();

   public final Numeric.ShortIsIntegral$ num$extension(final short $this) {
      return Numeric.ShortIsIntegral$.MODULE$;
   }

   public final Ordering.Short$ ord$extension(final short $this) {
      return Ordering.Short$.MODULE$;
   }

   public final double doubleValue$extension(final short $this) {
      return (double)$this;
   }

   public final float floatValue$extension(final short $this) {
      return (float)$this;
   }

   public final long longValue$extension(final short $this) {
      return (long)$this;
   }

   public final int intValue$extension(final short $this) {
      return $this;
   }

   public final byte byteValue$extension(final short $this) {
      return (byte)$this;
   }

   public final short shortValue$extension(final short $this) {
      return $this;
   }

   public final boolean isValidShort$extension(final short $this) {
      return true;
   }

   public final short abs$extension(final short $this) {
      package$ var10000 = package$.MODULE$;
      return (short)Math.abs($this);
   }

   public final short max$extension(final short $this, final short that) {
      package$ var10000 = package$.MODULE$;
      return (short)Math.max($this, that);
   }

   public final short min$extension(final short $this, final short that) {
      package$ var10000 = package$.MODULE$;
      return (short)Math.min($this, that);
   }

   public final int hashCode$extension(final short $this) {
      return Short.hashCode($this);
   }

   public final boolean equals$extension(final short $this, final Object x$1) {
      if (x$1 instanceof RichShort) {
         short var3 = ((RichShort)x$1).self();
         if ($this == var3) {
            return true;
         }
      }

      return false;
   }

   private RichShort$() {
   }
}
