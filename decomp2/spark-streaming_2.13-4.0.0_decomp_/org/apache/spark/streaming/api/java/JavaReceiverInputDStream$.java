package org.apache.spark.streaming.api.java;

import java.io.Serializable;
import org.apache.spark.streaming.dstream.ReceiverInputDStream;
import scala.reflect.ClassTag;
import scala.runtime.ModuleSerializationProxy;

public final class JavaReceiverInputDStream$ implements Serializable {
   public static final JavaReceiverInputDStream$ MODULE$ = new JavaReceiverInputDStream$();

   public JavaReceiverInputDStream fromReceiverInputDStream(final ReceiverInputDStream receiverInputDStream, final ClassTag evidence$1) {
      return new JavaReceiverInputDStream(receiverInputDStream, evidence$1);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(JavaReceiverInputDStream$.class);
   }

   private JavaReceiverInputDStream$() {
   }
}
