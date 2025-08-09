package org.apache.spark.streaming.ui;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.streaming.Time;
import org.apache.spark.streaming.scheduler.BatchInfo;
import org.apache.spark.streaming.scheduler.OutputOperationInfo;
import scala.Option;
import scala.Some;
import scala.Tuple7;
import scala.collection.Iterable;
import scala.collection.immutable.Map;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashMap.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class BatchUIData$ implements Serializable {
   public static final BatchUIData$ MODULE$ = new BatchUIData$();

   public HashMap $lessinit$greater$default$6() {
      return (HashMap).MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
   }

   public Iterable $lessinit$greater$default$7() {
      return (Iterable)scala.package..MODULE$.Seq().empty();
   }

   public BatchUIData apply(final BatchInfo batchInfo) {
      HashMap outputOperations = (HashMap).MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      outputOperations.$plus$plus$eq(batchInfo.outputOperationInfos().transform((x$6, v) -> $anonfun$apply$1(BoxesRunTime.unboxToInt(x$6), v)));
      return new BatchUIData(batchInfo.batchTime(), batchInfo.streamIdToInputInfo(), batchInfo.submissionTime(), batchInfo.processingStartTime(), batchInfo.processingEndTime(), outputOperations, this.$lessinit$greater$default$7());
   }

   public HashMap apply$default$6() {
      return (HashMap).MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
   }

   public Iterable apply$default$7() {
      return (Iterable)scala.package..MODULE$.Seq().empty();
   }

   public BatchUIData apply(final Time batchTime, final Map streamIdToInputInfo, final long submissionTime, final Option processingStartTime, final Option processingEndTime, final HashMap outputOperations, final Iterable outputOpIdSparkJobIdPairs) {
      return new BatchUIData(batchTime, streamIdToInputInfo, submissionTime, processingStartTime, processingEndTime, outputOperations, outputOpIdSparkJobIdPairs);
   }

   public Option unapply(final BatchUIData x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple7(x$0.batchTime(), x$0.streamIdToInputInfo(), BoxesRunTime.boxToLong(x$0.submissionTime()), x$0.processingStartTime(), x$0.processingEndTime(), x$0.outputOperations(), x$0.outputOpIdSparkJobIdPairs())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BatchUIData$.class);
   }

   // $FF: synthetic method
   public static final OutputOperationUIData $anonfun$apply$1(final int x$6, final OutputOperationInfo v) {
      return OutputOperationUIData$.MODULE$.apply(v);
   }

   private BatchUIData$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
