package org.sparkproject.guava.util.concurrent;

import java.util.logging.Logger;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.sparkproject.guava.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
final class LazyLogger {
   private final Object lock = new Object();
   private final String loggerName;
   private volatile @Nullable Logger logger;

   LazyLogger(Class ownerOfLogger) {
      this.loggerName = ownerOfLogger.getName();
   }

   Logger get() {
      Logger local = this.logger;
      if (local != null) {
         return local;
      } else {
         synchronized(this.lock) {
            local = this.logger;
            return local != null ? local : (this.logger = Logger.getLogger(this.loggerName));
         }
      }
   }
}
