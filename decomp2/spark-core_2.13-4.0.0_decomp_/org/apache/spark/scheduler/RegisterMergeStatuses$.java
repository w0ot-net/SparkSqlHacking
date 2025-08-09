package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class RegisterMergeStatuses$ extends AbstractFunction2 implements Serializable {
   public static final RegisterMergeStatuses$ MODULE$ = new RegisterMergeStatuses$();

   public final String toString() {
      return "RegisterMergeStatuses";
   }

   public RegisterMergeStatuses apply(final ShuffleMapStage stage, final Seq mergeStatuses) {
      return new RegisterMergeStatuses(stage, mergeStatuses);
   }

   public Option unapply(final RegisterMergeStatuses x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.stage(), x$0.mergeStatuses())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RegisterMergeStatuses$.class);
   }

   private RegisterMergeStatuses$() {
   }
}
