package org.apache.spark;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Option;
import scala.None.;
import scala.collection.IterableOnceOps;
import scala.collection.Map;
import scala.runtime.ModuleSerializationProxy;

public final class JobArtifactSet$ implements Serializable {
   public static final JobArtifactSet$ MODULE$ = new JobArtifactSet$();
   private static final JobArtifactSet emptyJobArtifactSet;
   private static Option lastSeenState;
   private static final ThreadLocal currentClientSessionState;

   static {
      emptyJobArtifactSet = new JobArtifactSet(.MODULE$, scala.Predef..MODULE$.Map().empty(), scala.Predef..MODULE$.Map().empty(), scala.Predef..MODULE$.Map().empty());
      lastSeenState = .MODULE$;
      currentClientSessionState = new ThreadLocal() {
         public Option initialValue() {
            return .MODULE$;
         }
      };
   }

   public JobArtifactSet emptyJobArtifactSet() {
      return emptyJobArtifactSet;
   }

   public JobArtifactSet defaultJobArtifactSet() {
      return (JobArtifactSet)SparkContext$.MODULE$.getActive().map((sc) -> MODULE$.getActiveOrDefault(sc)).getOrElse(() -> MODULE$.emptyJobArtifactSet());
   }

   public Option lastSeenState() {
      return lastSeenState;
   }

   public void lastSeenState_$eq(final Option x$1) {
      lastSeenState = x$1;
   }

   public Option getCurrentJobArtifactState() {
      return (Option)currentClientSessionState.get();
   }

   public Object withActiveJobArtifactState(final JobArtifactState state, final Function0 block) {
      Option oldState = (Option)currentClientSessionState.get();
      currentClientSessionState.set(scala.Option..MODULE$.apply(state));
      this.lastSeenState_$eq(scala.Option..MODULE$.apply(state));

      Object var10000;
      try {
         var10000 = block.apply();
      } finally {
         currentClientSessionState.set(oldState);
      }

      return var10000;
   }

   public JobArtifactSet getActiveOrDefault(final SparkContext sc) {
      Option maybeState = ((Option)currentClientSessionState.get()).map((s) -> {
         Option x$1 = s.replClassDirUri().orElse(() -> sc.conf().getOption("spark.repl.class.uri"));
         String x$2 = s.copy$default$1();
         return s.copy(x$2, x$1);
      });
      return new JobArtifactSet(maybeState, ((IterableOnceOps)maybeState.map((s) -> (Map)sc.addedJars().getOrElse(s.uuid(), () -> scala.Predef..MODULE$.Map().empty())).getOrElse(() -> sc.allAddedJars())).toMap(scala..less.colon.less..MODULE$.refl()), ((IterableOnceOps)maybeState.map((s) -> (Map)sc.addedFiles().getOrElse(s.uuid(), () -> scala.Predef..MODULE$.Map().empty())).getOrElse(() -> sc.allAddedFiles())).toMap(scala..less.colon.less..MODULE$.refl()), ((IterableOnceOps)maybeState.map((s) -> (Map)sc.addedArchives().getOrElse(s.uuid(), () -> scala.Predef..MODULE$.Map().empty())).getOrElse(() -> sc.allAddedArchives())).toMap(scala..less.colon.less..MODULE$.refl()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(JobArtifactSet$.class);
   }

   private JobArtifactSet$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
