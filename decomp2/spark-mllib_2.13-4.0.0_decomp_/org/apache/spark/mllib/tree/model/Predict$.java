package org.apache.spark.mllib.tree.model;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class Predict$ implements Serializable {
   public static final Predict$ MODULE$ = new Predict$();

   public double $lessinit$greater$default$2() {
      return (double)0.0F;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Predict$.class);
   }

   private Predict$() {
   }
}
