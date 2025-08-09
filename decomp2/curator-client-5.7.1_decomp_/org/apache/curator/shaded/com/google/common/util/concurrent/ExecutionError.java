package org.apache.curator.shaded.com.google.common.util.concurrent;

import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public class ExecutionError extends Error {
   private static final long serialVersionUID = 0L;

   protected ExecutionError() {
   }

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
