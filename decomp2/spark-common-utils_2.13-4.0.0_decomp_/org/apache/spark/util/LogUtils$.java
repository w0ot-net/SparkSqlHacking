package org.apache.spark.util;

import org.apache.spark.annotation.DeveloperApi;
import scala.collection.StringOps.;

@DeveloperApi
public final class LogUtils$ {
   public static final LogUtils$ MODULE$ = new LogUtils$();
   private static final String SPARK_LOG_SCHEMA;

   static {
      SPARK_LOG_SCHEMA = .MODULE$.stripMargin$extension(scala.Predef..MODULE$.augmentString("\n    |ts TIMESTAMP,\n    |level STRING,\n    |msg STRING,\n    |context map<STRING, STRING>,\n    |exception STRUCT<\n    |  class STRING,\n    |  msg STRING,\n    |  stacktrace ARRAY<STRUCT<\n    |    class STRING,\n    |    method STRING,\n    |    file STRING,\n    |    line STRING\n    |  >>\n    |>,\n    |logger STRING"));
   }

   public String SPARK_LOG_SCHEMA() {
      return SPARK_LOG_SCHEMA;
   }

   private LogUtils$() {
   }
}
