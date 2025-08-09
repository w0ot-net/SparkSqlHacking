package org.apache.spark.scheduler;

import java.io.Serializable;
import java.util.Properties;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class SparkListenerStageSubmitted$ extends AbstractFunction2 implements Serializable {
   public static final SparkListenerStageSubmitted$ MODULE$ = new SparkListenerStageSubmitted$();

   public Properties $lessinit$greater$default$2() {
      return null;
   }

   public final String toString() {
      return "SparkListenerStageSubmitted";
   }

   public SparkListenerStageSubmitted apply(final StageInfo stageInfo, final Properties properties) {
      return new SparkListenerStageSubmitted(stageInfo, properties);
   }

   public Properties apply$default$2() {
      return null;
   }

   public Option unapply(final SparkListenerStageSubmitted x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.stageInfo(), x$0.properties())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkListenerStageSubmitted$.class);
   }

   private SparkListenerStageSubmitted$() {
   }
}
