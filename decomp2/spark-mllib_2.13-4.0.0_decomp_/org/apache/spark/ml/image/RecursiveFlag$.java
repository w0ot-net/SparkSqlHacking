package org.apache.spark.ml.image;

import org.apache.hadoop.conf.Configuration;
import org.apache.spark.sql.SparkSession;
import scala.Function0;
import scala.Option;
import scala.Option.;

public final class RecursiveFlag$ {
   public static final RecursiveFlag$ MODULE$ = new RecursiveFlag$();

   public Object withRecursiveFlag(final boolean value, final SparkSession spark, final Function0 f) {
      String flagName = "mapreduce.input.fileinputformat.input.dir.recursive";
      Configuration hadoopConf = spark.sparkContext().hadoopConfiguration();
      Option old = .MODULE$.apply(hadoopConf.get(flagName));
      hadoopConf.set(flagName, Boolean.toString(value));

      Object var10000;
      try {
         var10000 = f.apply();
      } finally {
         if (old.isDefined()) {
            hadoopConf.set(flagName, (String)old.get());
         } else {
            hadoopConf.unset(flagName);
         }

      }

      return var10000;
   }

   private RecursiveFlag$() {
   }
}
