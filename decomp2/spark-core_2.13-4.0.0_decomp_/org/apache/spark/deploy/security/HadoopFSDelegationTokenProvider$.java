package org.apache.spark.deploy.security;

import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.package$;
import scala.Option;
import scala.None.;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.immutable.Set;
import scala.collection.immutable.SetOps;

public final class HadoopFSDelegationTokenProvider$ {
   public static final HadoopFSDelegationTokenProvider$ MODULE$ = new HadoopFSDelegationTokenProvider$();

   public Set hadoopFSsToAccess(final SparkConf sparkConf, final Configuration hadoopConf) {
      FileSystem defaultFS = FileSystem.get(hadoopConf);
      Set filesystemsToAccess = ((IterableOnceOps)((IterableOps)sparkConf.get(package$.MODULE$.KERBEROS_FILESYSTEMS_TO_ACCESS())).map((x$4) -> (new Path(x$4)).getFileSystem(hadoopConf))).toSet();
      String master = sparkConf.get("spark.master", (String)null);
      Option stagingFS = (Option)(master != null && master.contains("yarn") ? ((Option)sparkConf.get((ConfigEntry)package$.MODULE$.STAGING_DIR())).map((x$5) -> (new Path(x$5)).getFileSystem(hadoopConf)) : .MODULE$);
      return (Set)((SetOps)filesystemsToAccess.$plus$plus(stagingFS)).$plus(defaultFS);
   }

   private HadoopFSDelegationTokenProvider$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
