package scala;

import scala.runtime.BoxesRunTime;

public final class ValueOf$ {
   public static final ValueOf$ MODULE$ = new ValueOf$();

   public final int hashCode$extension(final Object $this) {
      return $this.hashCode();
   }

   public final boolean equals$extension(final Object $this, final Object x$1) {
      if (x$1 instanceof ValueOf) {
         Object var3 = x$1 == null ? null : ((ValueOf)x$1).value();
         if (BoxesRunTime.equals($this, var3)) {
            return true;
         }
      }

      return false;
   }

   private ValueOf$() {
   }
}
