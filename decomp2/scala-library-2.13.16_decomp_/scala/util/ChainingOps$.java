package scala.util;

import scala.Function1;
import scala.runtime.BoxesRunTime;

public final class ChainingOps$ {
   public static final ChainingOps$ MODULE$ = new ChainingOps$();

   public final Object tap$extension(final Object $this, final Function1 f) {
      f.apply($this);
      return $this;
   }

   public final Object pipe$extension(final Object $this, final Function1 f) {
      return f.apply($this);
   }

   public final int hashCode$extension(final Object $this) {
      return $this.hashCode();
   }

   public final boolean equals$extension(final Object $this, final Object x$1) {
      if (x$1 instanceof ChainingOps) {
         Object var3 = x$1 == null ? null : ((ChainingOps)x$1).scala$util$ChainingOps$$self();
         if (BoxesRunTime.equals($this, var3)) {
            return true;
         }
      }

      return false;
   }

   private ChainingOps$() {
   }
}
