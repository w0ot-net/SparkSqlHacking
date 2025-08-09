package org.apache.spark.deploy.history;

import java.lang.invoke.SerializedLambda;
import java.net.URI;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.Path;
import scala.Option;
import scala.Predef.;

public final class RollingEventLogFilesWriter$ {
   public static final RollingEventLogFilesWriter$ MODULE$ = new RollingEventLogFilesWriter$();
   private static final String EVENT_LOG_DIR_NAME_PREFIX = "eventlog_v2_";
   private static final String EVENT_LOG_FILE_NAME_PREFIX = "events_";
   private static final String APPSTATUS_FILE_NAME_PREFIX = "appstatus_";

   public String EVENT_LOG_DIR_NAME_PREFIX() {
      return EVENT_LOG_DIR_NAME_PREFIX;
   }

   public String EVENT_LOG_FILE_NAME_PREFIX() {
      return EVENT_LOG_FILE_NAME_PREFIX;
   }

   public String APPSTATUS_FILE_NAME_PREFIX() {
      return APPSTATUS_FILE_NAME_PREFIX;
   }

   public Path getAppEventLogDirPath(final URI logBaseDir, final String appId, final Option appAttemptId) {
      Path var10002 = new Path(logBaseDir);
      String var10003 = this.EVENT_LOG_DIR_NAME_PREFIX();
      return new Path(var10002, var10003 + EventLogFileWriter$.MODULE$.nameForAppAndAttempt(appId, appAttemptId));
   }

   public Path getAppStatusFilePath(final Path appLogDir, final String appId, final Option appAttemptId, final boolean inProgress) {
      String var10000 = this.APPSTATUS_FILE_NAME_PREFIX();
      String base = var10000 + EventLogFileWriter$.MODULE$.nameForAppAndAttempt(appId, appAttemptId);
      String name = inProgress ? base + EventLogFileWriter$.MODULE$.IN_PROGRESS() : base;
      return new Path(appLogDir, name);
   }

   public Path getEventLogFilePath(final Path appLogDir, final String appId, final Option appAttemptId, final long index, final Option codecName) {
      String base = this.EVENT_LOG_FILE_NAME_PREFIX() + index + "_" + EventLogFileWriter$.MODULE$.nameForAppAndAttempt(appId, appAttemptId);
      String codec = (String)codecName.map((x$8) -> "." + x$8).getOrElse(() -> "");
      return new Path(appLogDir, base + codec);
   }

   public boolean isEventLogDir(final FileStatus status) {
      return status.isDirectory() && status.getPath().getName().startsWith(this.EVENT_LOG_DIR_NAME_PREFIX());
   }

   public boolean isEventLogFile(final String fileName) {
      return fileName.startsWith(this.EVENT_LOG_FILE_NAME_PREFIX());
   }

   public boolean isEventLogFile(final FileStatus status) {
      return status.isFile() && this.isEventLogFile(status.getPath().getName());
   }

   public boolean isAppStatusFile(final FileStatus status) {
      return status.isFile() && status.getPath().getName().startsWith(this.APPSTATUS_FILE_NAME_PREFIX());
   }

   public long getEventLogFileIndex(final String eventLogFileName) {
      .MODULE$.require(this.isEventLogFile(eventLogFileName), () -> "Not an event log file!");
      String index = scala.collection.StringOps..MODULE$.stripPrefix$extension(.MODULE$.augmentString(eventLogFileName), this.EVENT_LOG_FILE_NAME_PREFIX()).split("_")[0];
      return scala.collection.StringOps..MODULE$.toLong$extension(.MODULE$.augmentString(index));
   }

   private RollingEventLogFilesWriter$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
