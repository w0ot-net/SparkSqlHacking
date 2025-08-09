package org.apache.spark.scheduler;

import scala.Enumeration;
import scala.runtime.ModuleSerializationProxy;

public final class SchedulingMode$ extends Enumeration {
   public static final SchedulingMode$ MODULE$ = new SchedulingMode$();
   private static final Enumeration.Value FAIR;
   private static final Enumeration.Value FIFO;
   private static final Enumeration.Value NONE;

   static {
      FAIR = MODULE$.Value();
      FIFO = MODULE$.Value();
      NONE = MODULE$.Value();
   }

   public Enumeration.Value FAIR() {
      return FAIR;
   }

   public Enumeration.Value FIFO() {
      return FIFO;
   }

   public Enumeration.Value NONE() {
      return NONE;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SchedulingMode$.class);
   }

   private SchedulingMode$() {
   }
}
