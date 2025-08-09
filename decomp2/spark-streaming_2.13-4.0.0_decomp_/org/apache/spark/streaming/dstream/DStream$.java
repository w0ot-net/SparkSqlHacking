package org.apache.spark.streaming.dstream;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.util.CallSite;
import scala.collection.StringOps.;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Null;
import scala.util.matching.Regex;

public final class DStream$ implements Serializable {
   public static final DStream$ MODULE$ = new DStream$();
   private static final Regex SPARK_CLASS_REGEX;
   private static final Regex SPARK_STREAMING_TESTCLASS_REGEX;
   private static final Regex SPARK_EXAMPLES_CLASS_REGEX;
   private static final Regex SCALA_CLASS_REGEX;

   static {
      SPARK_CLASS_REGEX = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("^org\\.apache\\.spark"));
      SPARK_STREAMING_TESTCLASS_REGEX = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("^org\\.apache\\.spark\\.streaming\\.test"));
      SPARK_EXAMPLES_CLASS_REGEX = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("^org\\.apache\\.spark\\.examples"));
      SCALA_CLASS_REGEX = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("^scala"));
   }

   private Regex SPARK_CLASS_REGEX() {
      return SPARK_CLASS_REGEX;
   }

   private Regex SPARK_STREAMING_TESTCLASS_REGEX() {
      return SPARK_STREAMING_TESTCLASS_REGEX;
   }

   private Regex SPARK_EXAMPLES_CLASS_REGEX() {
      return SPARK_EXAMPLES_CLASS_REGEX;
   }

   private Regex SCALA_CLASS_REGEX() {
      return SCALA_CLASS_REGEX;
   }

   public PairDStreamFunctions toPairDStreamFunctions(final DStream stream, final ClassTag kt, final ClassTag vt, final Ordering ord) {
      return new PairDStreamFunctions(stream, kt, vt, ord);
   }

   public Null toPairDStreamFunctions$default$4(final DStream stream) {
      return null;
   }

   public CallSite getCreationSite() {
      return org.apache.spark.util.Utils..MODULE$.getCallSite((className) -> BoxesRunTime.boxToBoolean($anonfun$getCreationSite$1(this, className)));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(DStream$.class);
   }

   private static final boolean doesMatch$1(final Regex r, final String className$1) {
      return r.findFirstIn(className$1).isDefined();
   }

   private final boolean streamingExclusionFunction$1(final String className) {
      boolean isSparkClass = doesMatch$1(this.SPARK_CLASS_REGEX(), className);
      boolean isSparkExampleClass = doesMatch$1(this.SPARK_EXAMPLES_CLASS_REGEX(), className);
      boolean isSparkStreamingTestClass = doesMatch$1(this.SPARK_STREAMING_TESTCLASS_REGEX(), className);
      boolean isScalaClass = doesMatch$1(this.SCALA_CLASS_REGEX(), className);
      return (isSparkClass || isScalaClass) && !isSparkExampleClass && !isSparkStreamingTestClass;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getCreationSite$1(final DStream$ $this, final String className) {
      return $this.streamingExclusionFunction$1(className);
   }

   private DStream$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
