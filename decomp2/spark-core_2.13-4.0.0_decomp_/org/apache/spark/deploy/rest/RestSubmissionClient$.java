package org.apache.spark.deploy.rest;

import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.immutable.;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.runtime.BoxesRunTime;

public final class RestSubmissionClient$ {
   public static final RestSubmissionClient$ MODULE$ = new RestSubmissionClient$();
   private static final Seq supportedMasterPrefixes;
   private static final Set EXCLUDED_SPARK_ENV_VARS;
   private static final int org$apache$spark$deploy$rest$RestSubmissionClient$$REPORT_DRIVER_STATUS_INTERVAL;
   private static final int org$apache$spark$deploy$rest$RestSubmissionClient$$REPORT_DRIVER_STATUS_MAX_TRIES;
   private static final String PROTOCOL_VERSION;

   static {
      supportedMasterPrefixes = new .colon.colon("spark://", scala.collection.immutable.Nil..MODULE$);
      EXCLUDED_SPARK_ENV_VARS = (Set)scala.Predef..MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"SPARK_ENV_LOADED", "SPARK_HOME", "SPARK_CONF_DIR"})));
      org$apache$spark$deploy$rest$RestSubmissionClient$$REPORT_DRIVER_STATUS_INTERVAL = 1000;
      org$apache$spark$deploy$rest$RestSubmissionClient$$REPORT_DRIVER_STATUS_MAX_TRIES = 10;
      PROTOCOL_VERSION = "v1";
   }

   public Seq supportedMasterPrefixes() {
      return supportedMasterPrefixes;
   }

   private Set EXCLUDED_SPARK_ENV_VARS() {
      return EXCLUDED_SPARK_ENV_VARS;
   }

   public int org$apache$spark$deploy$rest$RestSubmissionClient$$REPORT_DRIVER_STATUS_INTERVAL() {
      return org$apache$spark$deploy$rest$RestSubmissionClient$$REPORT_DRIVER_STATUS_INTERVAL;
   }

   public int org$apache$spark$deploy$rest$RestSubmissionClient$$REPORT_DRIVER_STATUS_MAX_TRIES() {
      return org$apache$spark$deploy$rest$RestSubmissionClient$$REPORT_DRIVER_STATUS_MAX_TRIES;
   }

   public String PROTOCOL_VERSION() {
      return PROTOCOL_VERSION;
   }

   public Map filterSystemEnvironment(final Map env) {
      return (Map)env.filter((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$filterSystemEnvironment$1(x0$1)));
   }

   public boolean supportsRestClient(final String master) {
      return this.supportedMasterPrefixes().exists((x$1) -> BoxesRunTime.boxToBoolean($anonfun$supportsRestClient$1(master, x$1)));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$filterSystemEnvironment$1(final Tuple2 x0$1) {
      if (x0$1 == null) {
         throw new MatchError(x0$1);
      } else {
         String k = (String)x0$1._1();
         return k.startsWith("SPARK_") && !MODULE$.EXCLUDED_SPARK_ENV_VARS().contains(k);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$supportsRestClient$1(final String master$3, final String x$1) {
      return master$3.startsWith(x$1);
   }

   private RestSubmissionClient$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
