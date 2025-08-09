package org.apache.spark.sql.hive.thriftserver.ui;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkContext;
import org.apache.spark.sql.errors.QueryExecutionErrors.;
import org.apache.spark.ui.SparkUI;

public final class ThriftServerTab$ {
   public static final ThriftServerTab$ MODULE$ = new ThriftServerTab$();

   public SparkUI getSparkUI(final SparkContext sparkContext) {
      return (SparkUI)sparkContext.ui().getOrElse(() -> {
         throw .MODULE$.parentSparkUIToAttachTabNotFoundError();
      });
   }

   private ThriftServerTab$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
