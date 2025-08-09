package org.apache.spark.scheduler;

import org.apache.spark.annotation.DeveloperApi;
import scala.Enumeration;
import scala.runtime.ModuleSerializationProxy;

@DeveloperApi
public final class TaskLocality$ extends Enumeration {
   public static final TaskLocality$ MODULE$ = new TaskLocality$();
   private static final Enumeration.Value PROCESS_LOCAL;
   private static final Enumeration.Value NODE_LOCAL;
   private static final Enumeration.Value NO_PREF;
   private static final Enumeration.Value RACK_LOCAL;
   private static final Enumeration.Value ANY;

   static {
      PROCESS_LOCAL = MODULE$.Value();
      NODE_LOCAL = MODULE$.Value();
      NO_PREF = MODULE$.Value();
      RACK_LOCAL = MODULE$.Value();
      ANY = MODULE$.Value();
   }

   public Enumeration.Value PROCESS_LOCAL() {
      return PROCESS_LOCAL;
   }

   public Enumeration.Value NODE_LOCAL() {
      return NODE_LOCAL;
   }

   public Enumeration.Value NO_PREF() {
      return NO_PREF;
   }

   public Enumeration.Value RACK_LOCAL() {
      return RACK_LOCAL;
   }

   public Enumeration.Value ANY() {
      return ANY;
   }

   public boolean isAllowed(final Enumeration.Value constraint, final Enumeration.Value condition) {
      return condition.$less$eq(constraint);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TaskLocality$.class);
   }

   private TaskLocality$() {
   }
}
