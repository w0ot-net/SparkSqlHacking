package org.apache.spark.rdd;

import scala.Enumeration;
import scala.runtime.ModuleSerializationProxy;

public final class CheckpointState$ extends Enumeration {
   public static final CheckpointState$ MODULE$ = new CheckpointState$();
   private static final Enumeration.Value Initialized;
   private static final Enumeration.Value CheckpointingInProgress;
   private static final Enumeration.Value Checkpointed;

   static {
      Initialized = MODULE$.Value();
      CheckpointingInProgress = MODULE$.Value();
      Checkpointed = MODULE$.Value();
   }

   public Enumeration.Value Initialized() {
      return Initialized;
   }

   public Enumeration.Value CheckpointingInProgress() {
      return CheckpointingInProgress;
   }

   public Enumeration.Value Checkpointed() {
      return Checkpointed;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(CheckpointState$.class);
   }

   private CheckpointState$() {
   }
}
