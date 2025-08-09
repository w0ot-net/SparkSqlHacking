package org.apache.orc.mapreduce;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.orc.OrcConf;
import org.apache.orc.OrcFile;
import org.apache.orc.Writer;

public class OrcOutputFormat extends FileOutputFormat {
   private static final String EXTENSION = ".orc";
   public static final String SKIP_TEMP_DIRECTORY = "orc.mapreduce.output.skip-temporary-directory";

   public RecordWriter getRecordWriter(TaskAttemptContext taskAttemptContext) throws IOException {
      Configuration conf = taskAttemptContext.getConfiguration();
      Path filename = this.getDefaultWorkFile(taskAttemptContext, ".orc");
      Writer writer = OrcFile.createWriter(filename, org.apache.orc.mapred.OrcOutputFormat.buildOptions(conf));
      return new OrcMapreduceRecordWriter(writer, OrcConf.ROW_BATCH_SIZE.getInt(conf), OrcConf.ROW_BATCH_CHILD_LIMIT.getInt(conf));
   }

   public Path getDefaultWorkFile(TaskAttemptContext context, String extension) throws IOException {
      return context.getConfiguration().getBoolean("orc.mapreduce.output.skip-temporary-directory", false) ? new Path(getOutputPath(context), getUniqueFile(context, getOutputName(context), extension)) : super.getDefaultWorkFile(context, extension);
   }
}
