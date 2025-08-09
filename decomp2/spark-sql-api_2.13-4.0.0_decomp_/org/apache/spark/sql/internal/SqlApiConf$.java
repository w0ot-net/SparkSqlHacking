package org.apache.spark.sql.internal;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.util.Try.;

public final class SqlApiConf$ {
   public static final SqlApiConf$ MODULE$ = new SqlApiConf$();
   private static final String ANSI_ENABLED_KEY;
   private static final String LEGACY_TIME_PARSER_POLICY_KEY;
   private static final String CASE_SENSITIVE_KEY;
   private static final String SESSION_LOCAL_TIMEZONE_KEY;
   private static final String ARROW_EXECUTION_USE_LARGE_VAR_TYPES;
   private static final String LOCAL_RELATION_CACHE_THRESHOLD_KEY;

   static {
      ANSI_ENABLED_KEY = SqlApiConfHelper$.MODULE$.ANSI_ENABLED_KEY();
      LEGACY_TIME_PARSER_POLICY_KEY = SqlApiConfHelper$.MODULE$.LEGACY_TIME_PARSER_POLICY_KEY();
      CASE_SENSITIVE_KEY = SqlApiConfHelper$.MODULE$.CASE_SENSITIVE_KEY();
      SESSION_LOCAL_TIMEZONE_KEY = SqlApiConfHelper$.MODULE$.SESSION_LOCAL_TIMEZONE_KEY();
      ARROW_EXECUTION_USE_LARGE_VAR_TYPES = SqlApiConfHelper$.MODULE$.ARROW_EXECUTION_USE_LARGE_VAR_TYPES();
      LOCAL_RELATION_CACHE_THRESHOLD_KEY = SqlApiConfHelper$.MODULE$.LOCAL_RELATION_CACHE_THRESHOLD_KEY();
      .MODULE$.apply(() -> org.apache.spark.util.SparkClassUtils..MODULE$.classForName("org.apache.spark.sql.internal.SQLConf$", org.apache.spark.util.SparkClassUtils..MODULE$.classForName$default$2(), org.apache.spark.util.SparkClassUtils..MODULE$.classForName$default$3()));
   }

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

   public String ARROW_EXECUTION_USE_LARGE_VAR_TYPES() {
      return ARROW_EXECUTION_USE_LARGE_VAR_TYPES;
   }

   public String LOCAL_RELATION_CACHE_THRESHOLD_KEY() {
      return LOCAL_RELATION_CACHE_THRESHOLD_KEY;
   }

   public SqlApiConf get() {
      return (SqlApiConf)((Function0)SqlApiConfHelper$.MODULE$.getConfGetter().get()).apply();
   }

   private SqlApiConf$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
