package org.apache.spark.mllib.tree.configuration;

import scala.Enumeration;
import scala.runtime.ModuleSerializationProxy;

public final class QuantileStrategy$ extends Enumeration {
   public static final QuantileStrategy$ MODULE$ = new QuantileStrategy$();
   private static final Enumeration.Value Sort;
   private static final Enumeration.Value MinMax;
   private static final Enumeration.Value ApproxHist;

   static {
      Sort = MODULE$.Value();
      MinMax = MODULE$.Value();
      ApproxHist = MODULE$.Value();
   }

   public Enumeration.Value Sort() {
      return Sort;
   }

   public Enumeration.Value MinMax() {
      return MinMax;
   }

   public Enumeration.Value ApproxHist() {
      return ApproxHist;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(QuantileStrategy$.class);
   }

   private QuantileStrategy$() {
   }
}
