package org.apache.spark.api.java;

import java.io.Serializable;
import org.apache.spark.rdd.RDD;
import scala.runtime.ModuleSerializationProxy;

public final class JavaDoubleRDD$ implements Serializable {
   public static final JavaDoubleRDD$ MODULE$ = new JavaDoubleRDD$();

   public JavaDoubleRDD fromRDD(final RDD rdd) {
      return new JavaDoubleRDD(rdd);
   }

   public RDD toRDD(final JavaDoubleRDD rdd) {
      return rdd.srdd();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(JavaDoubleRDD$.class);
   }

   private JavaDoubleRDD$() {
   }
}
