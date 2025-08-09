package org.apache.spark.ml.image;

import org.apache.commons.io.FilenameUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;
import org.apache.spark.sql.SparkSession;
import scala.Function0;
import scala.runtime.BoxedUnit;

public final class SamplePathFilter$ {
   public static final SamplePathFilter$ MODULE$ = new SamplePathFilter$();
   private static final String ratioParam = "sampleRatio";
   private static final String seedParam = "seed";

   public String ratioParam() {
      return ratioParam;
   }

   public String seedParam() {
      return seedParam;
   }

   public boolean isFile(final Path path) {
      boolean var3;
      label23: {
         String var10000 = FilenameUtils.getExtension(path.toString());
         String var2 = "";
         if (var10000 == null) {
            if (var2 != null) {
               break label23;
            }
         } else if (!var10000.equals(var2)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   public Object withPathFilter(final double sampleRatio, final SparkSession spark, final long seed, final Function0 f) {
      boolean sampleImages = sampleRatio < (double)1;
      if (sampleImages) {
         String flagName = "mapreduce.input.pathFilter.class";
         Configuration hadoopConf = spark.sparkContext().hadoopConfiguration();
         Class old = hadoopConf.getClass(flagName, (Class)null);
         hadoopConf.setDouble(this.ratioParam(), sampleRatio);
         hadoopConf.setLong(this.seedParam(), seed);
         hadoopConf.setClass(flagName, SamplePathFilter.class, PathFilter.class);

         Object var10000;
         try {
            var10000 = f.apply();
         } finally {
            hadoopConf.unset(this.ratioParam());
            hadoopConf.unset(this.seedParam());
            if (old == null) {
               hadoopConf.unset(flagName);
               BoxedUnit var16 = BoxedUnit.UNIT;
            } else {
               hadoopConf.setClass(flagName, old, PathFilter.class);
               BoxedUnit var10001 = BoxedUnit.UNIT;
            }

         }

         return var10000;
      } else {
         return f.apply();
      }
   }

   private SamplePathFilter$() {
   }
}
