package org.apache.spark.scheduler;

import java.io.Serializable;
import org.apache.spark.storage.BlockManagerId;
import scala.Option;
import scala.Some;
import scala.Tuple5;
import scala.None.;
import scala.runtime.AbstractFunction5;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class SparkListenerBlockManagerAdded$ extends AbstractFunction5 implements Serializable {
   public static final SparkListenerBlockManagerAdded$ MODULE$ = new SparkListenerBlockManagerAdded$();

   public Option $lessinit$greater$default$4() {
      return .MODULE$;
   }

   public Option $lessinit$greater$default$5() {
      return .MODULE$;
   }

   public final String toString() {
      return "SparkListenerBlockManagerAdded";
   }

   public SparkListenerBlockManagerAdded apply(final long time, final BlockManagerId blockManagerId, final long maxMem, final Option maxOnHeapMem, final Option maxOffHeapMem) {
      return new SparkListenerBlockManagerAdded(time, blockManagerId, maxMem, maxOnHeapMem, maxOffHeapMem);
   }

   public Option apply$default$4() {
      return .MODULE$;
   }

   public Option apply$default$5() {
      return .MODULE$;
   }

   public Option unapply(final SparkListenerBlockManagerAdded x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple5(BoxesRunTime.boxToLong(x$0.time()), x$0.blockManagerId(), BoxesRunTime.boxToLong(x$0.maxMem()), x$0.maxOnHeapMem(), x$0.maxOffHeapMem())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkListenerBlockManagerAdded$.class);
   }

   private SparkListenerBlockManagerAdded$() {
   }
}
