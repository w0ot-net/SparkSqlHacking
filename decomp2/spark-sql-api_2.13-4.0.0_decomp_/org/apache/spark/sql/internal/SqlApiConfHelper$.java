package org.apache.spark.sql.internal;

import java.lang.invoke.SerializedLambda;
import java.util.concurrent.atomic.AtomicReference;
import scala.Function0;

public final class SqlApiConfHelper$ {
   public static final SqlApiConfHelper$ MODULE$ = new SqlApiConfHelper$();
   private static final String ANSI_ENABLED_KEY = "spark.sql.ansi.enabled";
   private static final String LEGACY_TIME_PARSER_POLICY_KEY = "spark.sql.legacy.timeParserPolicy";
   private static final String CASE_SENSITIVE_KEY = "spark.sql.caseSensitive";
   private static final String SESSION_LOCAL_TIMEZONE_KEY = "spark.sql.session.timeZone";
   private static final String LOCAL_RELATION_CACHE_THRESHOLD_KEY = "spark.sql.session.localRelationCacheThreshold";
   private static final String ARROW_EXECUTION_USE_LARGE_VAR_TYPES = "spark.sql.execution.arrow.useLargeVarTypes";
   private static final AtomicReference confGetter = new AtomicReference((Function0)() -> DefaultSqlApiConf$.MODULE$);

   public String ANSI_ENABLED_KEY() {
      return ANSI_ENABLED_KEY;
   }

   public String LEGACY_TIME_PARSER_POLICY_KEY() {
      return LEGACY_TIME_PARSER_POLICY_KEY;
   }

   public String CASE_SENSITIVE_KEY() {
      return CASE_SENSITIVE_KEY;
   }

   public String SESSION_LOCAL_TIMEZONE_KEY() {
      return SESSION_LOCAL_TIMEZONE_KEY;
   }

   public String LOCAL_RELATION_CACHE_THRESHOLD_KEY() {
      return LOCAL_RELATION_CACHE_THRESHOLD_KEY;
   }

   public String ARROW_EXECUTION_USE_LARGE_VAR_TYPES() {
      return ARROW_EXECUTION_USE_LARGE_VAR_TYPES;
   }

   public AtomicReference confGetter() {
      return confGetter;
   }

   public AtomicReference getConfGetter() {
      return this.confGetter();
   }

   public void setConfGetter(final Function0 getter) {
      this.confGetter().set(getter);
   }

   private SqlApiConfHelper$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
