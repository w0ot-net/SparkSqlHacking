package org.apache.spark.scheduler;

import java.io.Serializable;
import java.util.Properties;
import scala.Option;
import scala.Some;
import scala.Tuple4;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction4;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class SparkListenerJobStart$ extends AbstractFunction4 implements Serializable {
   public static final SparkListenerJobStart$ MODULE$ = new SparkListenerJobStart$();

   public Properties $lessinit$greater$default$4() {
      return null;
   }

   public final String toString() {
      return "SparkListenerJobStart";
   }

   public SparkListenerJobStart apply(final int jobId, final long time, final Seq stageInfos, final Properties properties) {
      return new SparkListenerJobStart(jobId, time, stageInfos, properties);
   }

   public Properties apply$default$4() {
      return null;
   }

   public Option unapply(final SparkListenerJobStart x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple4(BoxesRunTime.boxToInteger(x$0.jobId()), BoxesRunTime.boxToLong(x$0.time()), x$0.stageInfos(), x$0.properties())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkListenerJobStart$.class);
   }

   private SparkListenerJobStart$() {
   }
}
