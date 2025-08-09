package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class ShuffleMergeFinalized$ extends AbstractFunction1 implements Serializable {
   public static final ShuffleMergeFinalized$ MODULE$ = new ShuffleMergeFinalized$();

   public final String toString() {
      return "ShuffleMergeFinalized";
   }

   public ShuffleMergeFinalized apply(final ShuffleMapStage stage) {
      return new ShuffleMergeFinalized(stage);
   }

   public Option unapply(final ShuffleMergeFinalized x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.stage()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ShuffleMergeFinalized$.class);
   }

   private ShuffleMergeFinalized$() {
   }
}
