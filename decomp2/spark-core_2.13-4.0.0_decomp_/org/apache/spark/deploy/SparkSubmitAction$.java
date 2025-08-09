package org.apache.spark.deploy;

import scala.Enumeration;
import scala.runtime.ModuleSerializationProxy;

public final class SparkSubmitAction$ extends Enumeration {
   public static final SparkSubmitAction$ MODULE$ = new SparkSubmitAction$();
   private static final Enumeration.Value SUBMIT;
   private static final Enumeration.Value KILL;
   private static final Enumeration.Value REQUEST_STATUS;
   private static final Enumeration.Value PRINT_VERSION;

   static {
      SUBMIT = MODULE$.Value();
      KILL = MODULE$.Value();
      REQUEST_STATUS = MODULE$.Value();
      PRINT_VERSION = MODULE$.Value();
   }

   public Enumeration.Value SUBMIT() {
      return SUBMIT;
   }

   public Enumeration.Value KILL() {
      return KILL;
   }

   public Enumeration.Value REQUEST_STATUS() {
      return REQUEST_STATUS;
   }

   public Enumeration.Value PRINT_VERSION() {
      return PRINT_VERSION;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkSubmitAction$.class);
   }

   private SparkSubmitAction$() {
   }
}
