package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import org.apache.spark.streaming.receiver.ReceivedBlockStoreResult;
import scala.Option;
import scala.Some;
import scala.Tuple4;
import scala.None.;
import scala.runtime.AbstractFunction4;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ReceivedBlockInfo$ extends AbstractFunction4 implements Serializable {
   public static final ReceivedBlockInfo$ MODULE$ = new ReceivedBlockInfo$();

   public final String toString() {
      return "ReceivedBlockInfo";
   }

   public ReceivedBlockInfo apply(final int streamId, final Option numRecords, final Option metadataOption, final ReceivedBlockStoreResult blockStoreResult) {
      return new ReceivedBlockInfo(streamId, numRecords, metadataOption, blockStoreResult);
   }

   public Option unapply(final ReceivedBlockInfo x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple4(BoxesRunTime.boxToInteger(x$0.streamId()), x$0.numRecords(), x$0.metadataOption(), x$0.blockStoreResult())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ReceivedBlockInfo$.class);
   }

   private ReceivedBlockInfo$() {
   }
}
