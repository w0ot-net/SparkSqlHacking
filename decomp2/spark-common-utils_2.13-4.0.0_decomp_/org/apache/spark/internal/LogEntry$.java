package org.apache.spark.internal;

import scala.Function0;

public final class LogEntry$ {
   public static final LogEntry$ MODULE$ = new LogEntry$();

   public LogEntry from(final Function0 msgWithCtx) {
      return new LogEntry(msgWithCtx);
   }

   private LogEntry$() {
   }
}
