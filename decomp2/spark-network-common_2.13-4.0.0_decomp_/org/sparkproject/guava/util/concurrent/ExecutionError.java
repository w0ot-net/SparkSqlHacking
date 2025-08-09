package org.sparkproject.guava.util.concurrent;

import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public class ExecutionError extends Error {
   private static final long serialVersionUID = 0L;

   /** @deprecated */
   @Deprecated
   protected ExecutionError() {
   }

   /** @deprecated */
   @Deprecated
   protected ExecutionError(@CheckForNull String message) {
      super(message);
   }

   public ExecutionError(@CheckForNull String message, @CheckForNull Error cause) {
      super(message, cause);
   }

   public ExecutionError(@CheckForNull Error cause) {
      super(cause);
   }
}
