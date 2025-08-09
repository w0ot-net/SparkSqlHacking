package org.apache.spark.mllib.tree.configuration;

import scala.Enumeration;
import scala.runtime.ModuleSerializationProxy;

public final class FeatureType$ extends Enumeration {
   public static final FeatureType$ MODULE$ = new FeatureType$();
   private static final Enumeration.Value Continuous;
   private static final Enumeration.Value Categorical;

   static {
      Continuous = MODULE$.Value();
      Categorical = MODULE$.Value();
   }

   public Enumeration.Value Continuous() {
      return Continuous;
   }

   public Enumeration.Value Categorical() {
      return Categorical;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(FeatureType$.class);
   }

   private FeatureType$() {
   }
}
