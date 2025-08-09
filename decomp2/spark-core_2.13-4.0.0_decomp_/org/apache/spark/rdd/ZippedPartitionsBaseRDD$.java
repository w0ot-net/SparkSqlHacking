package org.apache.spark.rdd;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class ZippedPartitionsBaseRDD$ implements Serializable {
   public static final ZippedPartitionsBaseRDD$ MODULE$ = new ZippedPartitionsBaseRDD$();

   public boolean $lessinit$greater$default$3() {
      return false;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ZippedPartitionsBaseRDD$.class);
   }

   private ZippedPartitionsBaseRDD$() {
   }
}
