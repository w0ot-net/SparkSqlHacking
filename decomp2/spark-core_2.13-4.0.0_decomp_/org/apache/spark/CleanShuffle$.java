package org.apache.spark;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class CleanShuffle$ extends AbstractFunction1 implements Serializable {
   public static final CleanShuffle$ MODULE$ = new CleanShuffle$();

   public final String toString() {
      return "CleanShuffle";
   }

   public CleanShuffle apply(final int shuffleId) {
      return new CleanShuffle(shuffleId);
   }

   public Option unapply(final CleanShuffle x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(BoxesRunTime.boxToInteger(x$0.shuffleId())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(CleanShuffle$.class);
   }

   private CleanShuffle$() {
   }
}
