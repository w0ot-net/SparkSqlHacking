package org.apache.spark;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple4;
import scala.collection.immutable.Seq;
import scala.package.;
import scala.runtime.AbstractFunction4;
import scala.runtime.ModuleSerializationProxy;

public final class TaskKilled$ extends AbstractFunction4 implements Serializable {
   public static final TaskKilled$ MODULE$ = new TaskKilled$();

   public Seq $lessinit$greater$default$2() {
      return (Seq).MODULE$.Seq().empty();
   }

   public Seq $lessinit$greater$default$3() {
      return scala.collection.immutable.Nil..MODULE$;
   }

   public Seq $lessinit$greater$default$4() {
      return (Seq).MODULE$.Seq().empty();
   }

   public final String toString() {
      return "TaskKilled";
   }

   public TaskKilled apply(final String reason, final Seq accumUpdates, final Seq accums, final Seq metricPeaks) {
      return new TaskKilled(reason, accumUpdates, accums, metricPeaks);
   }

   public Seq apply$default$2() {
      return (Seq).MODULE$.Seq().empty();
   }

   public Seq apply$default$3() {
      return scala.collection.immutable.Nil..MODULE$;
   }

   public Seq apply$default$4() {
      return (Seq).MODULE$.Seq().empty();
   }

   public Option unapply(final TaskKilled x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple4(x$0.reason(), x$0.accumUpdates(), x$0.accums(), x$0.metricPeaks())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TaskKilled$.class);
   }

   private TaskKilled$() {
   }
}
