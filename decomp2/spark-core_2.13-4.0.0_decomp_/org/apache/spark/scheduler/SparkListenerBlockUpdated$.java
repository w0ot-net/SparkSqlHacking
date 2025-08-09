package org.apache.spark.scheduler;

import java.io.Serializable;
import org.apache.spark.storage.BlockUpdatedInfo;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class SparkListenerBlockUpdated$ extends AbstractFunction1 implements Serializable {
   public static final SparkListenerBlockUpdated$ MODULE$ = new SparkListenerBlockUpdated$();

   public final String toString() {
      return "SparkListenerBlockUpdated";
   }

   public SparkListenerBlockUpdated apply(final BlockUpdatedInfo blockUpdatedInfo) {
      return new SparkListenerBlockUpdated(blockUpdatedInfo);
   }

   public Option unapply(final SparkListenerBlockUpdated x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.blockUpdatedInfo()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkListenerBlockUpdated$.class);
   }

   private SparkListenerBlockUpdated$() {
   }
}
