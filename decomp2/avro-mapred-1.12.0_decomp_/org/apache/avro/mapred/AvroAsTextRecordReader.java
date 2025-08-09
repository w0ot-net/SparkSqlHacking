package org.apache.avro.mapred;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.FileReader;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RecordReader;

class AvroAsTextRecordReader implements RecordReader {
   private FileReader reader;
   private Object datum;
   private long start;
   private long end;

   public AvroAsTextRecordReader(JobConf job, FileSplit split) throws IOException {
      this(DataFileReader.openReader(new FsInput(split.getPath(), job), new GenericDatumReader()), split);
   }

   protected AvroAsTextRecordReader(FileReader reader, FileSplit split) throws IOException {
      this.reader = reader;
      reader.sync(split.getStart());
      this.start = reader.tell();
      this.end = split.getStart() + split.getLength();
   }

   public Text createKey() {
      return new Text();
   }

   public Text createValue() {
      return new Text();
   }

   public boolean next(Text key, Text ignore) throws IOException {
      if (this.reader.hasNext() && !this.reader.pastSync(this.end)) {
         this.datum = this.reader.next(this.datum);
         if (this.datum instanceof ByteBuffer) {
            ByteBuffer b = (ByteBuffer)this.datum;
            if (b.hasArray()) {
               int offset = b.arrayOffset();
               int start = b.position();
               int length = b.remaining();
               key.set(b.array(), offset + start, offset + start + length);
            } else {
               byte[] bytes = new byte[b.remaining()];
               b.duplicate().get(bytes);
               key.set(bytes);
            }
         } else {
            key.set(GenericData.get().toString(this.datum));
         }

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
