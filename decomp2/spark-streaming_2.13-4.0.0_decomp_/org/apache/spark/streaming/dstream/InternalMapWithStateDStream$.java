package org.apache.spark.streaming.dstream;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class InternalMapWithStateDStream$ implements Serializable {
   public static final InternalMapWithStateDStream$ MODULE$ = new InternalMapWithStateDStream$();
   private static final int org$apache$spark$streaming$dstream$InternalMapWithStateDStream$$DEFAULT_CHECKPOINT_DURATION_MULTIPLIER = 10;

   public int org$apache$spark$streaming$dstream$InternalMapWithStateDStream$$DEFAULT_CHECKPOINT_DURATION_MULTIPLIER() {
      return org$apache$spark$streaming$dstream$InternalMapWithStateDStream$$DEFAULT_CHECKPOINT_DURATION_MULTIPLIER;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(InternalMapWithStateDStream$.class);
   }

   private InternalMapWithStateDStream$() {
   }
}
