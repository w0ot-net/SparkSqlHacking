package org.apache.curator.shaded.com.google.common.util.concurrent;

import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public class UncheckedExecutionException extends RuntimeException {
   private static final long serialVersionUID = 0L;

   protected UncheckedExecutionException() {
   }

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
