package org.apache.spark.rdd;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Null;

public final class PairRDDFunctions$ implements Serializable {
   public static final PairRDDFunctions$ MODULE$ = new PairRDDFunctions$();

   public Null $lessinit$greater$default$4(final RDD self) {
      return null;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(PairRDDFunctions$.class);
   }

   private PairRDDFunctions$() {
   }
}
