package org.apache.parquet.hadoop.mapred;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.FileOutputCommitter;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.JobContext;
import org.apache.parquet.hadoop.ParquetOutputCommitter;
import org.apache.parquet.hadoop.util.ContextUtil;

public class MapredParquetOutputCommitter extends FileOutputCommitter {
   public void commitJob(JobContext jobContext) throws IOException {
      super.commitJob(jobContext);
      Configuration conf = ContextUtil.getConfiguration(jobContext);
      Path outputPath = FileOutputFormat.getOutputPath(new JobConf(conf));
      ParquetOutputCommitter.writeMetaDataFile(conf, outputPath);
   }
}
