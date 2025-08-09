package org.apache.avro.mapred;

import java.io.IOException;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.FileReader;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RecordReader;

public class AvroRecordReader implements RecordReader {
   private FileReader reader;
   private long start;
   private long end;

   public AvroRecordReader(JobConf job, FileSplit split) throws IOException {
      this(DataFileReader.openReader(new FsInput(split.getPath(), job), AvroJob.createInputDataModel(job).createDatumReader(AvroJob.getInputSchema(job))), split);
   }

   protected AvroRecordReader(FileReader reader, FileSplit split) throws IOException {
      this.reader = reader;
      reader.sync(split.getStart());
      this.start = reader.tell();
      this.end = split.getStart() + split.getLength();
   }

   public AvroWrapper createKey() {
      return new AvroWrapper((Object)null);
   }

   public NullWritable createValue() {
      return NullWritable.get();
   }

   public boolean next(AvroWrapper wrapper, NullWritable ignore) throws IOException {
      if (this.reader.hasNext() && !this.reader.pastSync(this.end)) {
         wrapper.datum(this.reader.next(wrapper.datum()));
         return true;
      } else {
         return false;
      }
   }

   public float getProgress() throws IOException {
      return this.end == this.start ? 0.0F : Math.min(1.0F, (float)(this.getPos() - this.start) / (float)(this.end - this.start));
   }

   public long getPos() throws IOException {
      return this.reader.tell();
   }

   public void close() throws IOException {
      this.reader.close();
   }
}
