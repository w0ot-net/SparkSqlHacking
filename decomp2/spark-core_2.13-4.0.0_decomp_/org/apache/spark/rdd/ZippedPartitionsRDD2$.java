package org.apache.spark.rdd;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class ZippedPartitionsRDD2$ implements Serializable {
   public static final ZippedPartitionsRDD2$ MODULE$ = new ZippedPartitionsRDD2$();

   public boolean $lessinit$greater$default$5() {
      return false;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ZippedPartitionsRDD2$.class);
   }

   private ZippedPartitionsRDD2$() {
   }
}
