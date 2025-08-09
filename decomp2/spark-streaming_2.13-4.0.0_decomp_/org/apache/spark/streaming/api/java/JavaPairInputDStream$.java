package org.apache.spark.streaming.api.java;

import java.io.Serializable;
import org.apache.spark.streaming.dstream.InputDStream;
import scala.reflect.ClassTag;
import scala.runtime.ModuleSerializationProxy;

public final class JavaPairInputDStream$ implements Serializable {
   public static final JavaPairInputDStream$ MODULE$ = new JavaPairInputDStream$();

   public JavaPairInputDStream fromInputDStream(final InputDStream inputDStream, final ClassTag evidence$1, final ClassTag evidence$2) {
      return new JavaPairInputDStream(inputDStream, evidence$1, evidence$2);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(JavaPairInputDStream$.class);
   }

   private JavaPairInputDStream$() {
   }
}
