package org.apache.curator.shaded.com.google.common.util.concurrent;

import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.VisibleForTesting;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
public final class UncaughtExceptionHandlers {
   private UncaughtExceptionHandlers() {
   }

   public static Thread.UncaughtExceptionHandler systemExit() {
      return new Exiter(Runtime.getRuntime());
   }

   @VisibleForTesting
   static final class Exiter implements Thread.UncaughtExceptionHandler {
      private static final Logger logger = Logger.getLogger(Exiter.class.getName());
      private final Runtime runtime;

      Exiter(Runtime runtime) {
         this.runtime = runtime;
      }

      public void uncaughtException(Thread t, Throwable e) {
         try {
            logger.log(Level.SEVERE, String.format(Locale.ROOT, "Caught an exception in %s.  Shutting down.", t), e);
         } catch (Error | RuntimeException errorInLogging) {
            System.err.println(e.getMessage());
            System.err.println(((Throwable)errorInLogging).getMessage());
         } finally {
            this.runtime.exit(1);
         }

      }
   }
}
