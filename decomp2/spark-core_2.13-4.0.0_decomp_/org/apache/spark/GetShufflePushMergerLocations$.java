package org.apache.spark;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class GetShufflePushMergerLocations$ extends AbstractFunction1 implements Serializable {
   public static final GetShufflePushMergerLocations$ MODULE$ = new GetShufflePushMergerLocations$();

   public final String toString() {
      return "GetShufflePushMergerLocations";
   }

   public GetShufflePushMergerLocations apply(final int shuffleId) {
      return new GetShufflePushMergerLocations(shuffleId);
   }

   public Option unapply(final GetShufflePushMergerLocations x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(BoxesRunTime.boxToInteger(x$0.shuffleId())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(GetShufflePushMergerLocations$.class);
   }

   private GetShufflePushMergerLocations$() {
   }
}
