package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class SparkListenerMiscellaneousProcessAdded$ extends AbstractFunction3 implements Serializable {
   public static final SparkListenerMiscellaneousProcessAdded$ MODULE$ = new SparkListenerMiscellaneousProcessAdded$();

   public final String toString() {
      return "SparkListenerMiscellaneousProcessAdded";
   }

   public SparkListenerMiscellaneousProcessAdded apply(final long time, final String processId, final MiscellaneousProcessDetails info) {
      return new SparkListenerMiscellaneousProcessAdded(time, processId, info);
   }

   public Option unapply(final SparkListenerMiscellaneousProcessAdded x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToLong(x$0.time()), x$0.processId(), x$0.info())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkListenerMiscellaneousProcessAdded$.class);
   }

   private SparkListenerMiscellaneousProcessAdded$() {
   }
}
