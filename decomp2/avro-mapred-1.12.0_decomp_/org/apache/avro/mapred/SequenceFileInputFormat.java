package org.apache.avro.mapred;

import java.io.IOException;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapred.InputSplit;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RecordReader;
import org.apache.hadoop.mapred.Reporter;

public class SequenceFileInputFormat extends FileInputFormat {
   public RecordReader getRecordReader(InputSplit split, JobConf job, Reporter reporter) throws IOException {
      reporter.setStatus(split.toString());
      return new SequenceFileRecordReader(job, (FileSplit)split);
   }
}
