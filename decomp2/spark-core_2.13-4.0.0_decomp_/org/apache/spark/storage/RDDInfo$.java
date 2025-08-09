package org.apache.spark.storage;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.Dependency;
import org.apache.spark.SparkEnv;
import org.apache.spark.SparkEnv$;
import org.apache.spark.internal.config.package$;
import org.apache.spark.rdd.DeterministicLevel$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.util.Utils$;
import scala.Enumeration;
import scala.Option;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.BoxesRunTime;

public final class RDDInfo$ {
   public static final RDDInfo$ MODULE$ = new RDDInfo$();

   public String $lessinit$greater$default$7() {
      return "";
   }

   public Option $lessinit$greater$default$8() {
      return .MODULE$;
   }

   public Enumeration.Value $lessinit$greater$default$9() {
      return DeterministicLevel$.MODULE$.DETERMINATE();
   }

   public RDDInfo fromRdd(final RDD rdd) {
      String rddName = (String)scala.Option..MODULE$.apply(rdd.name()).getOrElse(() -> Utils$.MODULE$.getFormattedClassName(rdd));
      Seq parentIds = (Seq)rdd.dependencies().map((x$1) -> BoxesRunTime.boxToInteger($anonfun$fromRdd$2(x$1)));
      boolean ifCallSiteLongForm = scala.Option..MODULE$.apply(SparkEnv$.MODULE$.get()).exists((x$2) -> BoxesRunTime.boxToBoolean($anonfun$fromRdd$3(x$2)));
      String callSite = ifCallSiteLongForm ? rdd.creationSite().longForm() : rdd.creationSite().shortForm();
      return new RDDInfo(rdd.id(), rddName, rdd.partitions().length, rdd.getStorageLevel(), rdd.isBarrier(), parentIds, callSite, rdd.scope(), rdd.outputDeterministicLevel());
   }

   // $FF: synthetic method
   public static final int $anonfun$fromRdd$2(final Dependency x$1) {
      return x$1.rdd().id();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$fromRdd$3(final SparkEnv x$2) {
      return BoxesRunTime.unboxToBoolean(x$2.conf().get(package$.MODULE$.EVENT_LOG_CALLSITE_LONG_FORM()));
   }

   private RDDInfo$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
