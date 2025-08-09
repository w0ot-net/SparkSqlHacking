package org.sparkproject.guava.util.concurrent;

import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.base.Preconditions;

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
