package org.apache.parquet.hadoop;

import java.io.IOException;
import java.util.List;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputCommitter;
import org.apache.parquet.hadoop.util.ContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParquetOutputCommitter extends FileOutputCommitter {
   private static final Logger LOG = LoggerFactory.getLogger(ParquetOutputCommitter.class);
   private final Path outputPath;

   public ParquetOutputCommitter(Path outputPath, TaskAttemptContext context) throws IOException {
      super(outputPath, context);
      this.outputPath = outputPath;
   }

   public void commitJob(JobContext jobContext) throws IOException {
      super.commitJob(jobContext);
      Configuration configuration = ContextUtil.getConfiguration(jobContext);
      writeMetaDataFile(configuration, this.outputPath);
   }

   public static void writeMetaDataFile(Configuration configuration, Path outputPath) {
      ParquetOutputFormat.JobSummaryLevel level = ParquetOutputFormat.getJobSummaryLevel(configuration);
      if (level != ParquetOutputFormat.JobSummaryLevel.NONE) {
         try {
            FileSystem fileSystem = outputPath.getFileSystem(configuration);
            FileStatus outputStatus = fileSystem.getFileStatus(outputPath);
            List<Footer> footers;
            switch (level) {
               case ALL:
                  footers = ParquetFileReader.readAllFootersInParallel(configuration, outputStatus, false);
                  break;
               case COMMON_ONLY:
                  footers = ParquetFileReader.readAllFootersInParallel(configuration, outputStatus, true);
                  break;
               default:
                  throw new IllegalArgumentException("Unrecognized job summary level: " + level);
            }

            if (footers.isEmpty()) {
               return;
            }

            try {
               ParquetFileWriter.writeMetadataFile(configuration, outputPath, footers, level);
            } catch (Exception e) {
               LOG.warn("could not write summary file(s) for " + outputPath, e);
               Path metadataPath = new Path(outputPath, "_metadata");

               try {
                  if (fileSystem.exists(metadataPath)) {
                     fileSystem.delete(metadataPath, true);
                  }
               } catch (Exception e2) {
                  LOG.warn("could not delete metadata file" + outputPath, e2);
               }

               try {
                  Path commonMetadataPath = new Path(outputPath, "_common_metadata");
                  if (fileSystem.exists(commonMetadataPath)) {
                     fileSystem.delete(commonMetadataPath, true);
                  }
               } catch (Exception e2) {
                  LOG.warn("could not delete metadata file" + outputPath, e2);
               }
            }
         } catch (Exception e) {
            LOG.warn("could not write summary file for " + outputPath, e);
         }

      }
   }
}
