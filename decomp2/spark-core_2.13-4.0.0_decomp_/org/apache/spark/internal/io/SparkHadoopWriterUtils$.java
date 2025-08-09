package org.apache.spark.internal.io;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.JobID;
import org.apache.spark.SparkConf;
import org.apache.spark.TaskContext;
import org.apache.spark.deploy.SparkHadoopUtil$;
import org.apache.spark.executor.OutputMetrics;
import scala.Function0;
import scala.Tuple2;
import scala.runtime.BoxesRunTime;
import scala.util.DynamicVariable;
import scala.util.Random;

public final class SparkHadoopWriterUtils$ {
   public static final SparkHadoopWriterUtils$ MODULE$ = new SparkHadoopWriterUtils$();
   private static final int RECORDS_BETWEEN_BYTES_WRITTEN_METRIC_UPDATES = 256;
   private static final Random RAND = new Random();
   private static final DateTimeFormatter DATE_TIME_FORMATTER;
   private static final DynamicVariable disableOutputSpecValidation;

   static {
      DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss", Locale.US).withZone(ZoneId.systemDefault());
      disableOutputSpecValidation = new DynamicVariable(BoxesRunTime.boxToBoolean(false));
   }

   private int RECORDS_BETWEEN_BYTES_WRITTEN_METRIC_UPDATES() {
      return RECORDS_BETWEEN_BYTES_WRITTEN_METRIC_UPDATES;
   }

   private Random RAND() {
      return RAND;
   }

   private DateTimeFormatter DATE_TIME_FORMATTER() {
      return DATE_TIME_FORMATTER;
   }

   public JobID createJobID(final Date time, final int id) {
      String jobTrackerID = this.createJobTrackerID(time);
      return this.createJobID(jobTrackerID, id);
   }

   public JobID createJobID(final String jobTrackerID, final int id) {
      if (id < 0) {
         throw new IllegalArgumentException("Job number is negative");
      } else {
         return new JobID(jobTrackerID, id);
      }
   }

   public String createJobTrackerID(final Date time) {
      String base = this.DATE_TIME_FORMATTER().format(time.toInstant());
      long l1 = this.RAND().nextLong();
      if (l1 < 0L) {
         l1 = -l1;
      }

      return base + l1;
   }

   public Path createPathFromString(final String path, final JobConf conf) {
      if (path == null) {
         throw new IllegalArgumentException("Output path is null");
      } else {
         Path outputPath = new Path(path);
         FileSystem fs = outputPath.getFileSystem(conf);
         if (fs == null) {
            throw new IllegalArgumentException("Incorrectly formatted output path");
         } else {
            return outputPath.makeQualified(fs.getUri(), fs.getWorkingDirectory());
         }
      }
   }

   public boolean isOutputSpecValidationEnabled(final SparkConf conf) {
      boolean validationDisabled = BoxesRunTime.unboxToBoolean(this.disableOutputSpecValidation().value());
      boolean enabledInConf = conf.getBoolean("spark.hadoop.validateOutputSpecs", true);
      return enabledInConf && !validationDisabled;
   }

   public Tuple2 initHadoopOutputMetrics(final TaskContext context) {
      Function0 bytesWrittenCallback = SparkHadoopUtil$.MODULE$.get().getFSBytesWrittenOnThreadCallback();
      return new Tuple2(context.taskMetrics().outputMetrics(), bytesWrittenCallback);
   }

   public void maybeUpdateOutputMetrics(final OutputMetrics outputMetrics, final Function0 callback, final long recordsWritten) {
      if (recordsWritten % (long)this.RECORDS_BETWEEN_BYTES_WRITTEN_METRIC_UPDATES() == 0L) {
         outputMetrics.setBytesWritten(callback.apply$mcJ$sp());
         outputMetrics.setRecordsWritten(recordsWritten);
      }
   }

   public DynamicVariable disableOutputSpecValidation() {
      return disableOutputSpecValidation;
   }

   private SparkHadoopWriterUtils$() {
   }
}
