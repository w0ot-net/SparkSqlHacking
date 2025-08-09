package org.apache.spark.rdd;

import org.apache.spark.annotation.DeveloperApi;
import scala.Enumeration;
import scala.runtime.ModuleSerializationProxy;

@DeveloperApi
public final class DeterministicLevel$ extends Enumeration {
   public static final DeterministicLevel$ MODULE$ = new DeterministicLevel$();
   private static final Enumeration.Value DETERMINATE;
   private static final Enumeration.Value UNORDERED;
   private static final Enumeration.Value INDETERMINATE;

   static {
      DETERMINATE = MODULE$.Value();
      UNORDERED = MODULE$.Value();
      INDETERMINATE = MODULE$.Value();
   }

   public Enumeration.Value DETERMINATE() {
      return DETERMINATE;
   }

   public Enumeration.Value UNORDERED() {
      return UNORDERED;
   }

   public Enumeration.Value INDETERMINATE() {
      return INDETERMINATE;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(DeterministicLevel$.class);
   }

   private DeterministicLevel$() {
   }
}
