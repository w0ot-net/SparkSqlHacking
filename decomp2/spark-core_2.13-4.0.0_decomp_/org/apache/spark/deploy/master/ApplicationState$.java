package org.apache.spark.deploy.master;

import scala.Enumeration;
import scala.runtime.ModuleSerializationProxy;

public final class ApplicationState$ extends Enumeration {
   public static final ApplicationState$ MODULE$ = new ApplicationState$();
   private static final Enumeration.Value WAITING;
   private static final Enumeration.Value RUNNING;
   private static final Enumeration.Value FINISHED;
   private static final Enumeration.Value FAILED;
   private static final Enumeration.Value KILLED;
   private static final Enumeration.Value UNKNOWN;

   static {
      WAITING = MODULE$.Value();
      RUNNING = MODULE$.Value();
      FINISHED = MODULE$.Value();
      FAILED = MODULE$.Value();
      KILLED = MODULE$.Value();
      UNKNOWN = MODULE$.Value();
   }

   public Enumeration.Value WAITING() {
      return WAITING;
   }

   public Enumeration.Value RUNNING() {
      return RUNNING;
   }

   public Enumeration.Value FINISHED() {
      return FINISHED;
   }

   public Enumeration.Value FAILED() {
      return FAILED;
   }

   public Enumeration.Value KILLED() {
      return KILLED;
   }

   public Enumeration.Value UNKNOWN() {
      return UNKNOWN;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ApplicationState$.class);
   }

   private ApplicationState$() {
   }
}
