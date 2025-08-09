package org.apache.avro.mapreduce;

import java.io.IOException;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.CombineFileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.CombineFileRecordReader;
import org.apache.hadoop.mapreduce.lib.input.CombineFileRecordReaderWrapper;
import org.apache.hadoop.mapreduce.lib.input.CombineFileSplit;

public class CombineAvroKeyValueFileInputFormat extends CombineFileInputFormat {
   public RecordReader createRecordReader(InputSplit inputSplit, TaskAttemptContext taskAttemptContext) throws IOException {
      return new CombineFileRecordReader((CombineFileSplit)inputSplit, taskAttemptContext, AvroKeyValueFileRecordReaderWrapper.class);
   }

   private static class AvroKeyValueFileRecordReaderWrapper extends CombineFileRecordReaderWrapper {
      public AvroKeyValueFileRecordReaderWrapper(CombineFileSplit split, TaskAttemptContext context, Integer idx) throws IOException, InterruptedException {
         super(new AvroKeyValueInputFormat(), split, context, idx);
      }
   }
}
