package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class SpeculativeTaskSubmitted$ extends AbstractFunction2 implements Serializable {
   public static final SpeculativeTaskSubmitted$ MODULE$ = new SpeculativeTaskSubmitted$();

   public int $lessinit$greater$default$2() {
      return -1;
   }

   public final String toString() {
      return "SpeculativeTaskSubmitted";
   }

   public SpeculativeTaskSubmitted apply(final Task task, final int taskIndex) {
      return new SpeculativeTaskSubmitted(task, taskIndex);
   }

   public int apply$default$2() {
      return -1;
   }

   public Option unapply(final SpeculativeTaskSubmitted x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.task(), BoxesRunTime.boxToInteger(x$0.taskIndex()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SpeculativeTaskSubmitted$.class);
   }

   private SpeculativeTaskSubmitted$() {
   }
}
