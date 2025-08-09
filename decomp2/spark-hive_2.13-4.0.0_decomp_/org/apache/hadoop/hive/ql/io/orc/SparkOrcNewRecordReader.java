package org.apache.hadoop.hive.ql.io.orc;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

public class SparkOrcNewRecordReader extends RecordReader {
   private final org.apache.hadoop.hive.ql.io.orc.RecordReader reader;
   private final int numColumns;
   OrcStruct value;
   private float progress = 0.0F;
   private ObjectInspector objectInspector;

   public SparkOrcNewRecordReader(Reader file, Configuration conf, long offset, long length) throws IOException {
      this.numColumns = file.getSchema().getChildren().size();
      this.value = new OrcStruct(this.numColumns);
      this.reader = OrcInputFormat.createReaderFromFile(file, conf, offset, length);
      this.objectInspector = file.getObjectInspector();
   }

   public void close() throws IOException {
      this.reader.close();
   }

   public NullWritable getCurrentKey() throws IOException, InterruptedException {
      return NullWritable.get();
   }

   public OrcStruct getCurrentValue() throws IOException, InterruptedException {
      return this.value;
   }

   public float getProgress() throws IOException, InterruptedException {
      return this.progress;
   }

   public void initialize(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
   }

   public boolean nextKeyValue() throws IOException, InterruptedException {
      if (this.reader.hasNext()) {
         this.reader.next(this.value);
         this.progress = this.reader.getProgress();
         return true;
      } else {
         return false;
      }
   }

   public ObjectInspector getObjectInspector() {
      return this.objectInspector;
   }
}
