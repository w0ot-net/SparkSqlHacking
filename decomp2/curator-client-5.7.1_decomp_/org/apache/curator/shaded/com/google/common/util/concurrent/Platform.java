package org.apache.curator.shaded.com.google.common.util.concurrent;

import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.common.base.Preconditions;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
final class Platform {
   static boolean isInstanceOfThrowableClass(@CheckForNull Throwable t, Class expectedClass) {
      return expectedClass.isInstance(t);
   }

   static void restoreInterruptIfIsInterruptedException(Throwable t) {
      Preconditions.checkNotNull(t);
      if (t instanceof InterruptedException) {
         Thread.currentThread().interrupt();
      }

   }

   private Platform() {
   }
}
