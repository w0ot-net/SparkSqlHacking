package org.apache.spark.deploy.history;

import java.net.URI;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.config.package$;
import org.apache.spark.util.Utils$;
import scala.Option;
import scala.collection.StringOps.;
import scala.runtime.BoxesRunTime;

public final class EventLogFileWriter$ {
   public static final EventLogFileWriter$ MODULE$ = new EventLogFileWriter$();
   private static final String IN_PROGRESS = ".inprogress";
   private static final String COMPACTED = ".compact";
   private static final FsPermission LOG_FILE_PERMISSIONS = new FsPermission((short)Integer.parseInt("660", 8));
   private static final FsPermission LOG_FOLDER_PERMISSIONS = new FsPermission((short)Integer.parseInt("770", 8));

   public String IN_PROGRESS() {
      return IN_PROGRESS;
   }

   public String COMPACTED() {
      return COMPACTED;
   }

   public FsPermission LOG_FILE_PERMISSIONS() {
      return LOG_FILE_PERMISSIONS;
   }

   public FsPermission LOG_FOLDER_PERMISSIONS() {
      return LOG_FOLDER_PERMISSIONS;
   }

   public EventLogFileWriter apply(final String appId, final Option appAttemptId, final URI logBaseDir, final SparkConf sparkConf, final Configuration hadoopConf) {
      return (EventLogFileWriter)(BoxesRunTime.unboxToBoolean(sparkConf.get(package$.MODULE$.EVENT_LOG_ENABLE_ROLLING())) ? new RollingEventLogFilesWriter(appId, appAttemptId, logBaseDir, sparkConf, hadoopConf) : new SingleEventLogFileWriter(appId, appAttemptId, logBaseDir, sparkConf, hadoopConf));
   }

   public String nameForAppAndAttempt(final String appId, final Option appAttemptId) {
      return Utils$.MODULE$.nameForAppAndAttempt(appId, appAttemptId);
   }

   public Option codecName(final Path log) {
      String logName = .MODULE$.stripSuffix$extension(scala.Predef..MODULE$.augmentString(.MODULE$.stripSuffix$extension(scala.Predef..MODULE$.augmentString(log.getName()), this.COMPACTED())), this.IN_PROGRESS());
      return scala.collection.ArrayOps..MODULE$.lastOption$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.tail$extension(scala.Predef..MODULE$.refArrayOps((Object[])logName.split("\\.")))));
   }

   public boolean isCompacted(final Path log) {
      return log.getName().endsWith(this.COMPACTED());
   }

   private EventLogFileWriter$() {
   }
}
