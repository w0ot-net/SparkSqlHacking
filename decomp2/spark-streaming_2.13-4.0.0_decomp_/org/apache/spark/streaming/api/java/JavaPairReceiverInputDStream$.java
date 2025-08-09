package org.apache.spark.streaming.api.java;

import java.io.Serializable;
import org.apache.spark.streaming.dstream.ReceiverInputDStream;
import scala.reflect.ClassTag;
import scala.runtime.ModuleSerializationProxy;

public final class JavaPairReceiverInputDStream$ implements Serializable {
   public static final JavaPairReceiverInputDStream$ MODULE$ = new JavaPairReceiverInputDStream$();

   public JavaPairReceiverInputDStream fromReceiverInputDStream(final ReceiverInputDStream receiverInputDStream, final ClassTag evidence$1, final ClassTag evidence$2) {
      return new JavaPairReceiverInputDStream(receiverInputDStream, evidence$1, evidence$2);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(JavaPairReceiverInputDStream$.class);
   }

   private JavaPairReceiverInputDStream$() {
   }
}
