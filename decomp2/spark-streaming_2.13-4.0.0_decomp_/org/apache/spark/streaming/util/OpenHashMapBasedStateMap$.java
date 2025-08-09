package org.apache.spark.streaming.util;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class OpenHashMapBasedStateMap$ implements Serializable {
   public static final OpenHashMapBasedStateMap$ MODULE$ = new OpenHashMapBasedStateMap$();
   private static final int DELTA_CHAIN_LENGTH_THRESHOLD = 20;
   private static final int DEFAULT_INITIAL_CAPACITY = 64;

   public int $lessinit$greater$default$2() {
      return this.DEFAULT_INITIAL_CAPACITY();
   }

   public int $lessinit$greater$default$3() {
      return this.DELTA_CHAIN_LENGTH_THRESHOLD();
   }

   public int DELTA_CHAIN_LENGTH_THRESHOLD() {
      return DELTA_CHAIN_LENGTH_THRESHOLD;
   }

   public int DEFAULT_INITIAL_CAPACITY() {
      return DEFAULT_INITIAL_CAPACITY;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(OpenHashMapBasedStateMap$.class);
   }

   private OpenHashMapBasedStateMap$() {
   }
}
