package org.apache.spark.streaming.dstream;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class ShuffledDStream$ implements Serializable {
   public static final ShuffledDStream$ MODULE$ = new ShuffledDStream$();

   public boolean $lessinit$greater$default$6() {
      return true;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ShuffledDStream$.class);
   }

   private ShuffledDStream$() {
   }
}
