package org.apache.spark.streaming.receiver;

import java.io.Serializable;
import org.apache.spark.storage.StreamBlockId;
import org.apache.spark.streaming.util.WriteAheadLogRecordHandle;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.ModuleSerializationProxy;

public final class WriteAheadLogBasedStoreResult$ extends AbstractFunction3 implements Serializable {
   public static final WriteAheadLogBasedStoreResult$ MODULE$ = new WriteAheadLogBasedStoreResult$();

   public final String toString() {
      return "WriteAheadLogBasedStoreResult";
   }

   public WriteAheadLogBasedStoreResult apply(final StreamBlockId blockId, final Option numRecords, final WriteAheadLogRecordHandle walRecordHandle) {
      return new WriteAheadLogBasedStoreResult(blockId, numRecords, walRecordHandle);
   }

   public Option unapply(final WriteAheadLogBasedStoreResult x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.blockId(), x$0.numRecords(), x$0.walRecordHandle())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(WriteAheadLogBasedStoreResult$.class);
   }

   private WriteAheadLogBasedStoreResult$() {
   }
}
