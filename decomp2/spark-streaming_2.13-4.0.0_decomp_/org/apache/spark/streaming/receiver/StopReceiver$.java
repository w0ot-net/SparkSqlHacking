package org.apache.spark.streaming.receiver;

import scala.runtime.ModuleSerializationProxy;

public final class StopReceiver$ implements ReceiverMessage {
   public static final StopReceiver$ MODULE$ = new StopReceiver$();

   private Object writeReplace() {
      return new ModuleSerializationProxy(StopReceiver$.class);
   }

   private StopReceiver$() {
   }
}
