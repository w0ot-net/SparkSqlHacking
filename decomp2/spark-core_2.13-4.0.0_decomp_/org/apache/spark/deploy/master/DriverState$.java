package org.apache.spark.deploy.master;

import scala.Enumeration;
import scala.runtime.ModuleSerializationProxy;

public final class DriverState$ extends Enumeration {
   public static final DriverState$ MODULE$ = new DriverState$();
   private static final Enumeration.Value SUBMITTED;
   private static final Enumeration.Value RUNNING;
   private static final Enumeration.Value FINISHED;
   private static final Enumeration.Value RELAUNCHING;
   private static final Enumeration.Value UNKNOWN;
   private static final Enumeration.Value KILLED;
   private static final Enumeration.Value FAILED;
   private static final Enumeration.Value ERROR;

   static {
      SUBMITTED = MODULE$.Value();
      RUNNING = MODULE$.Value();
      FINISHED = MODULE$.Value();
      RELAUNCHING = MODULE$.Value();
      UNKNOWN = MODULE$.Value();
      KILLED = MODULE$.Value();
      FAILED = MODULE$.Value();
      ERROR = MODULE$.Value();
   }

   public Enumeration.Value SUBMITTED() {
      return SUBMITTED;
   }

   public Enumeration.Value RUNNING() {
      return RUNNING;
   }

   public Enumeration.Value FINISHED() {
      return FINISHED;
   }

   public Enumeration.Value RELAUNCHING() {
      return RELAUNCHING;
   }

   public Enumeration.Value UNKNOWN() {
      return UNKNOWN;
   }

   public Enumeration.Value KILLED() {
      return KILLED;
   }

   public Enumeration.Value FAILED() {
      return FAILED;
   }

   public Enumeration.Value ERROR() {
      return ERROR;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(DriverState$.class);
   }

   private DriverState$() {
   }
}
