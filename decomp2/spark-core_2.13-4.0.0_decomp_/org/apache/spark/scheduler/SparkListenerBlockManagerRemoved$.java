package org.apache.spark.scheduler;

import java.io.Serializable;
import org.apache.spark.storage.BlockManagerId;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class SparkListenerBlockManagerRemoved$ extends AbstractFunction2 implements Serializable {
   public static final SparkListenerBlockManagerRemoved$ MODULE$ = new SparkListenerBlockManagerRemoved$();

   public final String toString() {
      return "SparkListenerBlockManagerRemoved";
   }

   public SparkListenerBlockManagerRemoved apply(final long time, final BlockManagerId blockManagerId) {
      return new SparkListenerBlockManagerRemoved(time, blockManagerId);
   }

   public Option unapply(final SparkListenerBlockManagerRemoved x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(BoxesRunTime.boxToLong(x$0.time()), x$0.blockManagerId())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkListenerBlockManagerRemoved$.class);
   }

   private SparkListenerBlockManagerRemoved$() {
   }
}
