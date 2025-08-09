package org.apache.spark.sql.hive.thriftserver;

import org.apache.spark.sql.SparkSession;

public final class SparkSQLDriver$ {
   public static final SparkSQLDriver$ MODULE$ = new SparkSQLDriver$();

   public SparkSession $lessinit$greater$default$1() {
      return SparkSQLEnv$.MODULE$.sparkSession();
   }

   private SparkSQLDriver$() {
   }
}
