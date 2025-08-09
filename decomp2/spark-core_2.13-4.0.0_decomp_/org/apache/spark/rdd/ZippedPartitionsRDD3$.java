package org.apache.spark.rdd;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class ZippedPartitionsRDD3$ implements Serializable {
   public static final ZippedPartitionsRDD3$ MODULE$ = new ZippedPartitionsRDD3$();

   public boolean $lessinit$greater$default$6() {
      return false;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ZippedPartitionsRDD3$.class);
   }

   private ZippedPartitionsRDD3$() {
   }
}
