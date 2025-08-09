package org.apache.spark.mllib.clustering;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class VectorWithNorm$ implements Serializable {
   public static final VectorWithNorm$ MODULE$ = new VectorWithNorm$();

   public double $lessinit$greater$default$3() {
      return (double)1.0F;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(VectorWithNorm$.class);
   }

   private VectorWithNorm$() {
   }
}
