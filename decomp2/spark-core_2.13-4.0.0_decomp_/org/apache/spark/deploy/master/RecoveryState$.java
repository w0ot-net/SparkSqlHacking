package org.apache.spark.deploy.master;

import scala.Enumeration;
import scala.runtime.ModuleSerializationProxy;

public final class RecoveryState$ extends Enumeration {
   public static final RecoveryState$ MODULE$ = new RecoveryState$();
   private static final Enumeration.Value STANDBY;
   private static final Enumeration.Value ALIVE;
   private static final Enumeration.Value RECOVERING;
   private static final Enumeration.Value COMPLETING_RECOVERY;

   static {
      STANDBY = MODULE$.Value();
      ALIVE = MODULE$.Value();
      RECOVERING = MODULE$.Value();
      COMPLETING_RECOVERY = MODULE$.Value();
   }

   public Enumeration.Value STANDBY() {
      return STANDBY;
   }

   public Enumeration.Value ALIVE() {
      return ALIVE;
   }

   public Enumeration.Value RECOVERING() {
      return RECOVERING;
   }

   public Enumeration.Value COMPLETING_RECOVERY() {
      return COMPLETING_RECOVERY;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RecoveryState$.class);
   }

   private RecoveryState$() {
   }
}
