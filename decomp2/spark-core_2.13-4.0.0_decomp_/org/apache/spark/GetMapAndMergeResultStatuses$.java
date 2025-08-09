package org.apache.spark;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class GetMapAndMergeResultStatuses$ extends AbstractFunction1 implements Serializable {
   public static final GetMapAndMergeResultStatuses$ MODULE$ = new GetMapAndMergeResultStatuses$();

   public final String toString() {
      return "GetMapAndMergeResultStatuses";
   }

   public GetMapAndMergeResultStatuses apply(final int shuffleId) {
      return new GetMapAndMergeResultStatuses(shuffleId);
   }

   public Option unapply(final GetMapAndMergeResultStatuses x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(BoxesRunTime.boxToInteger(x$0.shuffleId())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(GetMapAndMergeResultStatuses$.class);
   }

   private GetMapAndMergeResultStatuses$() {
   }
}
