package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class StageCancelled$ extends AbstractFunction2 implements Serializable {
   public static final StageCancelled$ MODULE$ = new StageCancelled$();

   public final String toString() {
      return "StageCancelled";
   }

   public StageCancelled apply(final int stageId, final Option reason) {
      return new StageCancelled(stageId, reason);
   }

   public Option unapply(final StageCancelled x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(BoxesRunTime.boxToInteger(x$0.stageId()), x$0.reason())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(StageCancelled$.class);
   }

   private StageCancelled$() {
   }
}
