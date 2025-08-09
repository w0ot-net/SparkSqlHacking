package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class SparkListenerUnpersistRDD$ extends AbstractFunction1 implements Serializable {
   public static final SparkListenerUnpersistRDD$ MODULE$ = new SparkListenerUnpersistRDD$();

   public final String toString() {
      return "SparkListenerUnpersistRDD";
   }

   public SparkListenerUnpersistRDD apply(final int rddId) {
      return new SparkListenerUnpersistRDD(rddId);
   }

   public Option unapply(final SparkListenerUnpersistRDD x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(BoxesRunTime.boxToInteger(x$0.rddId())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkListenerUnpersistRDD$.class);
   }

   private SparkListenerUnpersistRDD$() {
   }
}
