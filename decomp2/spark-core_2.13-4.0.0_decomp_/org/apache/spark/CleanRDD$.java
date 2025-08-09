package org.apache.spark;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class CleanRDD$ extends AbstractFunction1 implements Serializable {
   public static final CleanRDD$ MODULE$ = new CleanRDD$();

   public final String toString() {
      return "CleanRDD";
   }

   public CleanRDD apply(final int rddId) {
      return new CleanRDD(rddId);
   }

   public Option unapply(final CleanRDD x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(BoxesRunTime.boxToInteger(x$0.rddId())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(CleanRDD$.class);
   }

   private CleanRDD$() {
   }
}
