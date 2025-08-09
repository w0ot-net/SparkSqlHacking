package org.apache.spark.scheduler;

import java.io.Serializable;
import java.util.Properties;
import org.apache.spark.JobArtifactSet;
import org.apache.spark.rdd.RDD;
import org.apache.spark.util.CallSite;
import scala.Function2;
import scala.Option;
import scala.Some;
import scala.Tuple8;
import scala.None.;
import scala.runtime.AbstractFunction8;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class JobSubmitted$ extends AbstractFunction8 implements Serializable {
   public static final JobSubmitted$ MODULE$ = new JobSubmitted$();

   public Properties $lessinit$greater$default$8() {
      return null;
   }

   public final String toString() {
      return "JobSubmitted";
   }

   public JobSubmitted apply(final int jobId, final RDD finalRDD, final Function2 func, final int[] partitions, final CallSite callSite, final JobListener listener, final JobArtifactSet artifactSet, final Properties properties) {
      return new JobSubmitted(jobId, finalRDD, func, partitions, callSite, listener, artifactSet, properties);
   }

   public Properties apply$default$8() {
      return null;
   }

   public Option unapply(final JobSubmitted x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple8(BoxesRunTime.boxToInteger(x$0.jobId()), x$0.finalRDD(), x$0.func(), x$0.partitions(), x$0.callSite(), x$0.listener(), x$0.artifactSet(), x$0.properties())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(JobSubmitted$.class);
   }

   private JobSubmitted$() {
   }
}
