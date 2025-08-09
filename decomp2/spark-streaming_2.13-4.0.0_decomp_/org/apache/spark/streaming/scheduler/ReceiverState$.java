package org.apache.spark.streaming.scheduler;

import scala.Enumeration;
import scala.runtime.ModuleSerializationProxy;

public final class ReceiverState$ extends Enumeration {
   public static final ReceiverState$ MODULE$ = new ReceiverState$();
   private static final Enumeration.Value INACTIVE;
   private static final Enumeration.Value SCHEDULED;
   private static final Enumeration.Value ACTIVE;

   static {
      INACTIVE = MODULE$.Value();
      SCHEDULED = MODULE$.Value();
      ACTIVE = MODULE$.Value();
   }

   public Enumeration.Value INACTIVE() {
      return INACTIVE;
   }

   public Enumeration.Value SCHEDULED() {
      return SCHEDULED;
   }

   public Enumeration.Value ACTIVE() {
      return ACTIVE;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ReceiverState$.class);
   }

   private ReceiverState$() {
   }
}
