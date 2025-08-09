package org.apache.spark.mllib.rdd;

import java.io.Serializable;
import org.apache.spark.rdd.RDD;
import scala.reflect.ClassTag;
import scala.runtime.ModuleSerializationProxy;

public final class MLPairRDDFunctions$ implements Serializable {
   public static final MLPairRDDFunctions$ MODULE$ = new MLPairRDDFunctions$();

   public MLPairRDDFunctions fromPairRDD(final RDD rdd, final ClassTag evidence$3, final ClassTag evidence$4) {
      return new MLPairRDDFunctions(rdd, evidence$3, evidence$4);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(MLPairRDDFunctions$.class);
   }

   private MLPairRDDFunctions$() {
   }
}
