package org.apache.spark.deploy.history;

import scala.Enumeration;
import scala.runtime.ModuleSerializationProxy;

public final class LogType$ extends Enumeration {
   public static final LogType$ MODULE$ = new LogType$();
   private static final Enumeration.Value DriverLogs;
   private static final Enumeration.Value EventLogs;

   static {
      DriverLogs = MODULE$.Value();
      EventLogs = MODULE$.Value();
   }

   public Enumeration.Value DriverLogs() {
      return DriverLogs;
   }

   public Enumeration.Value EventLogs() {
      return EventLogs;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(LogType$.class);
   }

   private LogType$() {
   }
}
