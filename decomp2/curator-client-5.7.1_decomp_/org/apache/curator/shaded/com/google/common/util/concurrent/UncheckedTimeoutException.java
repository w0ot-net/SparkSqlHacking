package org.apache.curator.shaded.com.google.common.util.concurrent;

import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;

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
