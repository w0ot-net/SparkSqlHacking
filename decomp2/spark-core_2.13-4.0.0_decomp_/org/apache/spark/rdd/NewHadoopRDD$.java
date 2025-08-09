package org.apache.spark.rdd;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class NewHadoopRDD$ implements Serializable {
   public static final NewHadoopRDD$ MODULE$ = new NewHadoopRDD$();
   private static final Object CONFIGURATION_INSTANTIATION_LOCK = new Object();

   public Object CONFIGURATION_INSTANTIATION_LOCK() {
      return CONFIGURATION_INSTANTIATION_LOCK;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(NewHadoopRDD$.class);
   }

   private NewHadoopRDD$() {
   }
}
