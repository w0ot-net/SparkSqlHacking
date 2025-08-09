package org.apache.spark.rdd;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class MapPartitionsRDD$ implements Serializable {
   public static final MapPartitionsRDD$ MODULE$ = new MapPartitionsRDD$();

   public boolean $lessinit$greater$default$3() {
      return false;
   }

   public boolean $lessinit$greater$default$4() {
      return false;
   }

   public boolean $lessinit$greater$default$5() {
      return false;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(MapPartitionsRDD$.class);
   }

   private MapPartitionsRDD$() {
   }
}
