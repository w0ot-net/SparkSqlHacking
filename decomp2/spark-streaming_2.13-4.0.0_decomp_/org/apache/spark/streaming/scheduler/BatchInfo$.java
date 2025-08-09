package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import org.apache.spark.streaming.Time;
import scala.Option;
import scala.Some;
import scala.Tuple6;
import scala.None.;
import scala.collection.immutable.Map;
import scala.runtime.AbstractFunction6;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class BatchInfo$ extends AbstractFunction6 implements Serializable {
   public static final BatchInfo$ MODULE$ = new BatchInfo$();

   public final String toString() {
      return "BatchInfo";
   }

   public BatchInfo apply(final Time batchTime, final Map streamIdToInputInfo, final long submissionTime, final Option processingStartTime, final Option processingEndTime, final Map outputOperationInfos) {
      return new BatchInfo(batchTime, streamIdToInputInfo, submissionTime, processingStartTime, processingEndTime, outputOperationInfos);
   }

   public Option unapply(final BatchInfo x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple6(x$0.batchTime(), x$0.streamIdToInputInfo(), BoxesRunTime.boxToLong(x$0.submissionTime()), x$0.processingStartTime(), x$0.processingEndTime(), x$0.outputOperationInfos())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BatchInfo$.class);
   }

   private BatchInfo$() {
   }
}
