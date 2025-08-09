package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import org.apache.spark.streaming.Time;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.Predef.;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction3;
import scala.runtime.ModuleSerializationProxy;

public final class JobSet$ extends AbstractFunction3 implements Serializable {
   public static final JobSet$ MODULE$ = new JobSet$();

   public Map $lessinit$greater$default$3() {
      return .MODULE$.Map().empty();
   }

   public final String toString() {
      return "JobSet";
   }

   public JobSet apply(final Time time, final Seq jobs, final Map streamIdToInputInfo) {
      return new JobSet(time, jobs, streamIdToInputInfo);
   }

   public Map apply$default$3() {
      return .MODULE$.Map().empty();
   }

   public Option unapply(final JobSet x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(x$0.time(), x$0.jobs(), x$0.streamIdToInputInfo())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(JobSet$.class);
   }

   private JobSet$() {
   }
}
