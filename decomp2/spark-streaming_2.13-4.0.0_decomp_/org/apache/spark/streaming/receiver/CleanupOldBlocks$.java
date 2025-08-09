package org.apache.spark.streaming.receiver;

import java.io.Serializable;
import org.apache.spark.streaming.Time;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class CleanupOldBlocks$ extends AbstractFunction1 implements Serializable {
   public static final CleanupOldBlocks$ MODULE$ = new CleanupOldBlocks$();

   public final String toString() {
      return "CleanupOldBlocks";
   }

   public CleanupOldBlocks apply(final Time threshTime) {
      return new CleanupOldBlocks(threshTime);
   }

   public Option unapply(final CleanupOldBlocks x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.threshTime()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(CleanupOldBlocks$.class);
   }

   private CleanupOldBlocks$() {
   }
}
