package org.apache.spark.streaming.rdd;

import java.io.Serializable;
import org.apache.spark.storage.StorageLevel;
import scala.Array.;
import scala.runtime.ModuleSerializationProxy;

public final class WriteAheadLogBackedBlockRDD$ implements Serializable {
   public static final WriteAheadLogBackedBlockRDD$ MODULE$ = new WriteAheadLogBackedBlockRDD$();

   public boolean[] $lessinit$greater$default$4() {
      return (boolean[]).MODULE$.empty(scala.reflect.ClassTag..MODULE$.Boolean());
   }

   public boolean $lessinit$greater$default$5() {
      return false;
   }

   public StorageLevel $lessinit$greater$default$6() {
      return org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_ONLY_SER();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(WriteAheadLogBackedBlockRDD$.class);
   }

   private WriteAheadLogBackedBlockRDD$() {
   }
}
