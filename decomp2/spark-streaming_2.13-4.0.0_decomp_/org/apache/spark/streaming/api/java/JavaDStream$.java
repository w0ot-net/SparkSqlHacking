package org.apache.spark.streaming.api.java;

import java.io.Serializable;
import org.apache.spark.streaming.dstream.DStream;
import scala.reflect.ClassTag;
import scala.runtime.ModuleSerializationProxy;

public final class JavaDStream$ implements Serializable {
   public static final JavaDStream$ MODULE$ = new JavaDStream$();

   public JavaDStream fromDStream(final DStream dstream, final ClassTag evidence$1) {
      return new JavaDStream(dstream, evidence$1);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(JavaDStream$.class);
   }

   private JavaDStream$() {
   }
}
