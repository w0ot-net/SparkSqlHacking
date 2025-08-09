package org.apache.avro.mapred;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapred.InputSplit;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RecordReader;
import org.apache.hadoop.mapred.Reporter;

public class AvroAsTextInputFormat extends FileInputFormat {
   protected FileStatus[] listStatus(JobConf job) throws IOException {
      if (job.getBoolean("avro.mapred.ignore.inputs.without.extension", true)) {
         List<FileStatus> result = new ArrayList();

         for(FileStatus file : super.listStatus(job)) {
            if (file.getPath().getName().endsWith(".avro")) {
               result.add(file);
            }
         }

         return (FileStatus[])result.toArray(new FileStatus[0]);
      } else {
         return super.listStatus(job);
      }
   }

   public RecordReader getRecordReader(InputSplit split, JobConf job, Reporter reporter) throws IOException {
      reporter.setStatus(split.toString());
      return new AvroAsTextRecordReader(job, (FileSplit)split);
   }
}
