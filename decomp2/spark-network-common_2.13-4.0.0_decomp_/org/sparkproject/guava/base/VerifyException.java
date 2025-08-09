package org.sparkproject.guava.base;

import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public class VerifyException extends RuntimeException {
   public VerifyException() {
   }

   public VerifyException(@CheckForNull String message) {
      super(message);
   }

   public VerifyException(@CheckForNull Throwable cause) {
      super(cause);
   }

   public VerifyException(@CheckForNull String message, @CheckForNull Throwable cause) {
      super(message, cause);
   }
}
