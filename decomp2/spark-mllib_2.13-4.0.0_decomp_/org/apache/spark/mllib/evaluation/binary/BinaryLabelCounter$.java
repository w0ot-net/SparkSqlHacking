package org.apache.spark.mllib.evaluation.binary;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class BinaryLabelCounter$ implements Serializable {
   public static final BinaryLabelCounter$ MODULE$ = new BinaryLabelCounter$();

   public double $lessinit$greater$default$1() {
      return (double)0.0F;
   }

   public double $lessinit$greater$default$2() {
      return (double)0.0F;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BinaryLabelCounter$.class);
   }

   private BinaryLabelCounter$() {
   }
}
