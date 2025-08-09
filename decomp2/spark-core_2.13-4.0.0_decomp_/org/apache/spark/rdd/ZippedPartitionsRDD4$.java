package org.apache.spark.rdd;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class ZippedPartitionsRDD4$ implements Serializable {
   public static final ZippedPartitionsRDD4$ MODULE$ = new ZippedPartitionsRDD4$();

   public boolean $lessinit$greater$default$7() {
      return false;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ZippedPartitionsRDD4$.class);
   }

   private ZippedPartitionsRDD4$() {
   }
}
