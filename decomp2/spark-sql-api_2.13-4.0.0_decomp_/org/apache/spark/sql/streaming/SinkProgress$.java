package org.apache.spark.sql.streaming;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import scala.Option;
import scala.jdk.CollectionConverters.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.java8.JFunction0;

public final class SinkProgress$ implements Serializable {
   public static final SinkProgress$ MODULE$ = new SinkProgress$();
   private static final long DEFAULT_NUM_OUTPUT_ROWS = -1L;

   public Map $lessinit$greater$default$3() {
      return .MODULE$.MapHasAsJava((scala.collection.Map)scala.Predef..MODULE$.Map().apply(scala.collection.immutable.Nil..MODULE$)).asJava();
   }

   public long DEFAULT_NUM_OUTPUT_ROWS() {
      return DEFAULT_NUM_OUTPUT_ROWS;
   }

   public SinkProgress apply(final String description, final Option numOutputRows, final Map metrics) {
      return new SinkProgress(description, BoxesRunTime.unboxToLong(numOutputRows.getOrElse((JFunction0.mcJ.sp)() -> MODULE$.DEFAULT_NUM_OUTPUT_ROWS())), metrics);
   }

   public Map apply$default$3() {
      return .MODULE$.MapHasAsJava((scala.collection.Map)scala.Predef..MODULE$.Map().apply(scala.collection.immutable.Nil..MODULE$)).asJava();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SinkProgress$.class);
   }

   private SinkProgress$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
