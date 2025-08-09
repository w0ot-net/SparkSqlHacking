package org.apache.spark.scheduler;

import java.io.Serializable;
import java.util.Properties;
import org.apache.spark.JobArtifactSet;
import org.apache.spark.ShuffleDependency;
import org.apache.spark.util.CallSite;
import scala.Option;
import scala.Some;
import scala.Tuple6;
import scala.None.;
import scala.runtime.AbstractFunction6;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class MapStageSubmitted$ extends AbstractFunction6 implements Serializable {
   public static final MapStageSubmitted$ MODULE$ = new MapStageSubmitted$();

   public Properties $lessinit$greater$default$6() {
      return null;
   }

   public final String toString() {
      return "MapStageSubmitted";
   }

   public MapStageSubmitted apply(final int jobId, final ShuffleDependency dependency, final CallSite callSite, final JobListener listener, final JobArtifactSet artifactSet, final Properties properties) {
      return new MapStageSubmitted(jobId, dependency, callSite, listener, artifactSet, properties);
   }

   public Properties apply$default$6() {
      return null;
   }

   public Option unapply(final MapStageSubmitted x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple6(BoxesRunTime.boxToInteger(x$0.jobId()), x$0.dependency(), x$0.callSite(), x$0.listener(), x$0.artifactSet(), x$0.properties())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(MapStageSubmitted$.class);
   }

   private MapStageSubmitted$() {
   }
}
