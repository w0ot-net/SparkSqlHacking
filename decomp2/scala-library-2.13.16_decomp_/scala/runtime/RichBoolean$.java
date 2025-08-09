package scala.runtime;

import scala.math.Ordering;

public final class RichBoolean$ {
   public static final RichBoolean$ MODULE$ = new RichBoolean$();

   public final Ordering.Boolean$ ord$extension(final boolean $this) {
      return Ordering.Boolean$.MODULE$;
   }

   public final int hashCode$extension(final boolean $this) {
      return Boolean.hashCode($this);
   }

   public final boolean equals$extension(final boolean $this, final Object x$1) {
      if (x$1 instanceof RichBoolean) {
         boolean var3 = ((RichBoolean)x$1).self();
         if ($this == var3) {
            return true;
         }
      }

      return false;
   }

   private RichBoolean$() {
   }
}
