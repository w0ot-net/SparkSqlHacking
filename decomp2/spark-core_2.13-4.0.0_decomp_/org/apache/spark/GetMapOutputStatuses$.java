package org.apache.spark;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class GetMapOutputStatuses$ extends AbstractFunction1 implements Serializable {
   public static final GetMapOutputStatuses$ MODULE$ = new GetMapOutputStatuses$();

   public final String toString() {
      return "GetMapOutputStatuses";
   }

   public GetMapOutputStatuses apply(final int shuffleId) {
      return new GetMapOutputStatuses(shuffleId);
   }

   public Option unapply(final GetMapOutputStatuses x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(BoxesRunTime.boxToInteger(x$0.shuffleId())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(GetMapOutputStatuses$.class);
   }

   private GetMapOutputStatuses$() {
   }
}
