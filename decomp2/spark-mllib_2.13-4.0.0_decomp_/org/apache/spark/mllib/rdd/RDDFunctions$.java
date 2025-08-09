package org.apache.spark.mllib.rdd;

import java.io.Serializable;
import org.apache.spark.rdd.RDD;
import scala.reflect.ClassTag;
import scala.runtime.ModuleSerializationProxy;

public final class RDDFunctions$ implements Serializable {
   public static final RDDFunctions$ MODULE$ = new RDDFunctions$();

   public RDDFunctions fromRDD(final RDD rdd, final ClassTag evidence$2) {
      return new RDDFunctions(rdd, evidence$2);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RDDFunctions$.class);
   }

   private RDDFunctions$() {
   }
}
