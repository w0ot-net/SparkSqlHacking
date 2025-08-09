package org.apache.spark.sql.catalyst.util;

import scala.Enumeration;
import scala.runtime.ModuleSerializationProxy;

public final class IntervalStringStyles$ extends Enumeration {
   public static final IntervalStringStyles$ MODULE$ = new IntervalStringStyles$();
   private static final Enumeration.Value ANSI_STYLE;
   private static final Enumeration.Value HIVE_STYLE;

   static {
      ANSI_STYLE = MODULE$.Value();
      HIVE_STYLE = MODULE$.Value();
   }

   public Enumeration.Value ANSI_STYLE() {
      return ANSI_STYLE;
   }

   public Enumeration.Value HIVE_STYLE() {
      return HIVE_STYLE;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(IntervalStringStyles$.class);
   }

   private IntervalStringStyles$() {
   }
}
