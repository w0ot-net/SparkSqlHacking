package org.apache.spark.ui.storage;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple5;
import scala.None.;
import scala.runtime.AbstractFunction5;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class BlockTableRowData$ extends AbstractFunction5 implements Serializable {
   public static final BlockTableRowData$ MODULE$ = new BlockTableRowData$();

   public final String toString() {
      return "BlockTableRowData";
   }

   public BlockTableRowData apply(final String blockName, final String storageLevel, final long memoryUsed, final long diskUsed, final String executors) {
      return new BlockTableRowData(blockName, storageLevel, memoryUsed, diskUsed, executors);
   }

   public Option unapply(final BlockTableRowData x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple5(x$0.blockName(), x$0.storageLevel(), BoxesRunTime.boxToLong(x$0.memoryUsed()), BoxesRunTime.boxToLong(x$0.diskUsed()), x$0.executors())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BlockTableRowData$.class);
   }

   private BlockTableRowData$() {
   }
}
