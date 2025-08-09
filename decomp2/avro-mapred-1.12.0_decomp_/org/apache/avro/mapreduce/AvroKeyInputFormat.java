package org.apache.avro.mapreduce;

import java.io.IOException;
import org.apache.avro.Schema;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AvroKeyInputFormat extends FileInputFormat {
   private static final Logger LOG = LoggerFactory.getLogger(AvroKeyInputFormat.class);

   public RecordReader createRecordReader(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
      Schema readerSchema = AvroJob.getInputKeySchema(context.getConfiguration());
      if (null == readerSchema) {
         LOG.warn("Reader schema was not set. Use AvroJob.setInputKeySchema() if desired.");
         LOG.info("Using a reader schema equal to the writer schema.");
      }

      return new AvroKeyRecordReader(readerSchema);
   }
}
