package org.apache.spark.streaming.api.java;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.api.java.JavaSparkContext.;
import org.apache.spark.streaming.dstream.DStream;
import org.apache.spark.streaming.dstream.DStream$;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Null;

public final class JavaPairDStream$ implements Serializable {
   public static final JavaPairDStream$ MODULE$ = new JavaPairDStream$();

   public JavaPairDStream fromPairDStream(final DStream dstream, final ClassTag evidence$1, final ClassTag evidence$2) {
      return new JavaPairDStream(dstream, evidence$1, evidence$2);
   }

   public JavaPairDStream fromJavaDStream(final JavaDStream dstream) {
      ClassTag cmk = .MODULE$.fakeClassTag();
      ClassTag cmv = .MODULE$.fakeClassTag();
      return new JavaPairDStream(dstream.dstream(), cmk, cmv);
   }

   public JavaPairDStream scalaToJavaLong(final JavaPairDStream dstream, final ClassTag evidence$3) {
      DStream x$1 = dstream.dstream();
      ClassTag x$3 = scala.reflect.ClassTag..MODULE$.Long();
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$1);
      return this.fromPairDStream(DStream$.MODULE$.toPairDStreamFunctions(x$1, evidence$3, x$3, (Ordering)null).mapValues((x$1x) -> $anonfun$scalaToJavaLong$1(BoxesRunTime.unboxToLong(x$1x)), scala.reflect.ClassTag..MODULE$.apply(Long.class)), evidence$3, scala.reflect.ClassTag..MODULE$.apply(Long.class));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(JavaPairDStream$.class);
   }

   // $FF: synthetic method
   public static final Long $anonfun$scalaToJavaLong$1(final long x$1) {
      return x$1;
   }

   private JavaPairDStream$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
