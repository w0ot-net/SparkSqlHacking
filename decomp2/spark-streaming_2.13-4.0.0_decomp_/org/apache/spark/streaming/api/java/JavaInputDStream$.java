package org.apache.spark.streaming.api.java;

import java.io.Serializable;
import org.apache.spark.streaming.dstream.InputDStream;
import scala.reflect.ClassTag;
import scala.runtime.ModuleSerializationProxy;

public final class JavaInputDStream$ implements Serializable {
   public static final JavaInputDStream$ MODULE$ = new JavaInputDStream$();

   public JavaInputDStream fromInputDStream(final InputDStream inputDStream, final ClassTag evidence$1) {
      return new JavaInputDStream(inputDStream, evidence$1);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(JavaInputDStream$.class);
   }

   private JavaInputDStream$() {
   }
}
