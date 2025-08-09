package org.sparkproject.guava.util.concurrent;

import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public class UncheckedExecutionException extends RuntimeException {
   private static final long serialVersionUID = 0L;

   /** @deprecated */
   @Deprecated
   protected UncheckedExecutionException() {
   }

   /** @deprecated */
   @Deprecated
   protected UncheckedExecutionException(@CheckForNull String message) {
      super(message);
   }

   public UncheckedExecutionException(@CheckForNull String message, @CheckForNull Throwable cause) {
      super(message, cause);
   }

   public UncheckedExecutionException(@CheckForNull Throwable cause) {
      super(cause);
   }
}
