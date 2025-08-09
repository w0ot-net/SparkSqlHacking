package org.apache.avro.mapred;

import java.io.IOException;
import org.apache.avro.file.FileReader;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapred.JobConf;

public class SequenceFileRecordReader extends AvroRecordReader {
   public SequenceFileRecordReader(JobConf job, FileSplit split) throws IOException {
      super((FileReader)(new SequenceFileReader(split.getPath().toUri(), job)), split);
   }
}
