package org.apache.logging.log4j.core.appender.nosql;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class AbstractNoSqlConnection implements NoSqlConnection {
   private final AtomicBoolean closed = new AtomicBoolean();

   public void close() {
      if (this.closed.compareAndSet(false, true)) {
         this.closeImpl();
      }

   }

   protected abstract void closeImpl();

   public boolean isClosed() {
      return this.closed.get();
   }
}
