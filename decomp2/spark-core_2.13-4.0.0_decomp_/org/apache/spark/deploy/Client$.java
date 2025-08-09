package org.apache.spark.deploy;

import org.apache.spark.SparkConf;
import scala.sys.package.;

public final class Client$ {
   public static final Client$ MODULE$ = new Client$();

   public void main(final String[] args) {
      if (!.MODULE$.props().contains("SPARK_SUBMIT")) {
         scala.Predef..MODULE$.println("WARNING: This client is deprecated and will be removed in a future version of Spark");
         scala.Predef..MODULE$.println("Use ./bin/spark-submit with \"--master spark://host:port\"");
      }

      (new ClientApp()).start(args, new SparkConf());
   }

   private Client$() {
   }
}
