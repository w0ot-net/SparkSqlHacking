package org.apache.avro.mapreduce;

import java.io.IOException;
import org.apache.avro.Schema;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AvroKeyValueInputFormat extends FileInputFormat {
   private static final Logger LOG = LoggerFactory.getLogger(AvroKeyValueInputFormat.class);

   public RecordReader createRecordReader(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
      Schema keyReaderSchema = AvroJob.getInputKeySchema(context.getConfiguration());
      if (null == keyReaderSchema) {
         LOG.warn("Key reader schema was not set. Use AvroJob.setInputKeySchema() if desired.");
         LOG.info("Using a key reader schema equal to the writer schema.");
      }

      Schema valueReaderSchema = AvroJob.getInputValueSchema(context.getConfiguration());
      if (null == valueReaderSchema) {
         LOG.warn("Value reader schema was not set. Use AvroJob.setInputValueSchema() if desired.");
         LOG.info("Using a value reader schema equal to the writer schema.");
      }

      return new AvroKeyValueRecordReader(keyReaderSchema, valueReaderSchema);
   }
}
