package org.apache.spark.deploy.master;

import scala.Enumeration;
import scala.runtime.ModuleSerializationProxy;

public final class WorkerState$ extends Enumeration {
   public static final WorkerState$ MODULE$ = new WorkerState$();
   private static final Enumeration.Value ALIVE;
   private static final Enumeration.Value DEAD;
   private static final Enumeration.Value DECOMMISSIONED;
   private static final Enumeration.Value UNKNOWN;

   static {
      ALIVE = MODULE$.Value();
      DEAD = MODULE$.Value();
      DECOMMISSIONED = MODULE$.Value();
      UNKNOWN = MODULE$.Value();
   }

   public Enumeration.Value ALIVE() {
      return ALIVE;
   }

   public Enumeration.Value DEAD() {
      return DEAD;
   }

   public Enumeration.Value DECOMMISSIONED() {
      return DECOMMISSIONED;
   }

   public Enumeration.Value UNKNOWN() {
      return UNKNOWN;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(WorkerState$.class);
   }

   private WorkerState$() {
   }
}
