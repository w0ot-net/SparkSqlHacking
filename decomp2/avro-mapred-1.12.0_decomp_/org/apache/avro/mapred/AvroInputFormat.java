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

public class AvroInputFormat extends FileInputFormat {
   public static final String IGNORE_FILES_WITHOUT_EXTENSION_KEY = "avro.mapred.ignore.inputs.without.extension";
   public static final boolean IGNORE_INPUTS_WITHOUT_EXTENSION_DEFAULT = true;

   protected FileStatus[] listStatus(JobConf job) throws IOException {
      FileStatus[] status = super.listStatus(job);
      if (job.getBoolean("avro.mapred.ignore.inputs.without.extension", true)) {
         List<FileStatus> result = new ArrayList(status.length);

         for(FileStatus file : status) {
            if (file.getPath().getName().endsWith(".avro")) {
               result.add(file);
            }
         }

         status = (FileStatus[])result.toArray(new FileStatus[0]);
      }

      return status;
   }

   public RecordReader getRecordReader(InputSplit split, JobConf job, Reporter reporter) throws IOException {
      reporter.setStatus(split.toString());
      return new AvroRecordReader(job, (FileSplit)split);
   }
}
