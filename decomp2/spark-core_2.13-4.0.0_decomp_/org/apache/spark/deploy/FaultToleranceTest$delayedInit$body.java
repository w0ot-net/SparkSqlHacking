package org.apache.spark.deploy;

import scala.runtime.AbstractFunction0;
import scala.runtime.BoxedUnit;

public final class FaultToleranceTest$delayedInit$body extends AbstractFunction0 {
   private final FaultToleranceTest$ $outer;

   public final Object apply() {
      this.$outer.delayedEndpoint$org$apache$spark$deploy$FaultToleranceTest$1();
      return BoxedUnit.UNIT;
   }

   public FaultToleranceTest$delayedInit$body(final FaultToleranceTest$ $outer) {
      if ($outer == null) {
         throw null;
      } else {
         this.$outer = $outer;
         super();
      }
   }
}
