package org.apache.spark.streaming.api.java;

import java.io.Serializable;
import java.util.Map;
import org.apache.spark.streaming.Time;
import scala.Option;
import scala.Some;
import scala.Tuple10;
import scala.None.;
import scala.runtime.AbstractFunction10;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class JavaBatchInfo$ extends AbstractFunction10 implements Serializable {
   public static final JavaBatchInfo$ MODULE$ = new JavaBatchInfo$();

   public final String toString() {
      return "JavaBatchInfo";
   }

   public JavaBatchInfo apply(final Time batchTime, final Map streamIdToInputInfo, final long submissionTime, final long processingStartTime, final long processingEndTime, final long schedulingDelay, final long processingDelay, final long totalDelay, final long numRecords, final Map outputOperationInfos) {
      return new JavaBatchInfo(batchTime, streamIdToInputInfo, submissionTime, processingStartTime, processingEndTime, schedulingDelay, processingDelay, totalDelay, numRecords, outputOperationInfos);
   }

   public Option unapply(final JavaBatchInfo x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple10(x$0.batchTime(), x$0.streamIdToInputInfo(), BoxesRunTime.boxToLong(x$0.submissionTime()), BoxesRunTime.boxToLong(x$0.processingStartTime()), BoxesRunTime.boxToLong(x$0.processingEndTime()), BoxesRunTime.boxToLong(x$0.schedulingDelay()), BoxesRunTime.boxToLong(x$0.processingDelay()), BoxesRunTime.boxToLong(x$0.totalDelay()), BoxesRunTime.boxToLong(x$0.numRecords()), x$0.outputOperationInfos())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(JavaBatchInfo$.class);
   }

   private JavaBatchInfo$() {
   }
}
