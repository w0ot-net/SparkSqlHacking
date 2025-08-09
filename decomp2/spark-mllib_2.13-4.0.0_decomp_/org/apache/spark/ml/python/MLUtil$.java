package org.apache.spark.ml.python;

import java.lang.invoke.SerializedLambda;
import java.nio.file.Paths;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.spark.SparkContext;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.SparkSession.;

public final class MLUtil$ {
   public static final MLUtil$ MODULE$ = new MLUtil$();

   public void copyFileFromLocalToFs(final String localPath, final String destPath) {
      SparkContext sparkContext = ((SparkSession).MODULE$.getActiveSession().get()).sparkContext();
      Configuration hadoopConf = sparkContext.hadoopConfiguration();
      scala.Predef..MODULE$.assert(Paths.get(destPath).isAbsolute(), () -> "Destination path must be an absolute path on cloud storage.");
      Path destFSPath = new Path(destPath);
      FileSystem fs = destFSPath.getFileSystem(hadoopConf);
      fs.copyFromLocalFile(false, true, new Path(localPath), destFSPath);
   }

   private MLUtil$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
