package org.apache.spark;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class JobArtifactState$ extends AbstractFunction2 implements Serializable {
   public static final JobArtifactState$ MODULE$ = new JobArtifactState$();

   public final String toString() {
      return "JobArtifactState";
   }

   public JobArtifactState apply(final String uuid, final Option replClassDirUri) {
      return new JobArtifactState(uuid, replClassDirUri);
   }

   public Option unapply(final JobArtifactState x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.uuid(), x$0.replClassDirUri())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(JobArtifactState$.class);
   }

   private JobArtifactState$() {
   }
}
