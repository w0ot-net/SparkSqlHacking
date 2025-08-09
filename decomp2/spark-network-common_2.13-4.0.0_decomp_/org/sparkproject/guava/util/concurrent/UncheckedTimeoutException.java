package org.sparkproject.guava.util.concurrent;

import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
public class UncheckedTimeoutException extends RuntimeException {
   private static final long serialVersionUID = 0L;

   public UncheckedTimeoutException() {
   }

   public UncheckedTimeoutException(@CheckForNull String message) {
      super(message);
   }

   public UncheckedTimeoutException(@CheckForNull Throwable cause) {
      super(cause);
   }

   public UncheckedTimeoutException(@CheckForNull String message, @CheckForNull Throwable cause) {
      super(message, cause);
   }
}
