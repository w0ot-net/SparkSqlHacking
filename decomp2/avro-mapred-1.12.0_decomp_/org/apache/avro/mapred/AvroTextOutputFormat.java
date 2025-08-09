package org.apache.avro.mapred;

import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import org.apache.avro.Schema;
import org.apache.avro.Schema.Type;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.reflect.ReflectDatumWriter;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RecordWriter;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.util.Progressable;

public class AvroTextOutputFormat extends FileOutputFormat {
   public RecordWriter getRecordWriter(FileSystem ignore, JobConf job, String name, Progressable prog) throws IOException {
      Schema schema = Schema.create(Type.BYTES);
      byte[] keyValueSeparator = job.get("mapreduce.output.textoutputformat.separator", "\t").getBytes(StandardCharsets.UTF_8);
      DataFileWriter<ByteBuffer> writer = new DataFileWriter(new ReflectDatumWriter());
      AvroOutputFormat.configureDataFileWriter(writer, job);
      Path path = FileOutputFormat.getTaskOutputPath(job, name + ".avro");
      writer.create(schema, path.getFileSystem(job).create(path));
      return new AvroTextRecordWriter(writer, keyValueSeparator);
   }

   class AvroTextRecordWriter implements RecordWriter {
      private final DataFileWriter writer;
      private final byte[] keyValueSeparator;

      public AvroTextRecordWriter(DataFileWriter writer, byte[] keyValueSeparator) {
         this.writer = writer;
         this.keyValueSeparator = keyValueSeparator;
      }

      public void write(Object key, Object value) throws IOException {
         boolean nullKey = key == null || key instanceof NullWritable;
         boolean nullValue = value == null || value instanceof NullWritable;
         if (!nullKey || !nullValue) {
            if (!nullKey && nullValue) {
               this.writer.append(this.toByteBuffer(key));
            } else if (nullKey && !nullValue) {
               this.writer.append(this.toByteBuffer(value));
            } else {
               this.writer.append(this.toByteBuffer(key, this.keyValueSeparator, value));
            }
         }

      }

      public void close(Reporter reporter) throws IOException {
         this.writer.close();
      }

      private ByteBuffer toByteBuffer(Object o) throws IOException {
         if (o instanceof Text) {
            Text to = (Text)o;
            return ByteBuffer.wrap(to.getBytes(), 0, to.getLength());
         } else {
            return ByteBuffer.wrap(o.toString().getBytes(StandardCharsets.UTF_8));
         }
      }

      private ByteBuffer toByteBuffer(Object key, byte[] sep, Object value) throws IOException {
         byte[] keyBytes;
         int keyLength;
         if (key instanceof Text) {
            Text tkey = (Text)key;
            keyBytes = tkey.getBytes();
            keyLength = tkey.getLength();
         } else {
            keyBytes = key.toString().getBytes(StandardCharsets.UTF_8);
            keyLength = keyBytes.length;
         }

         byte[] valBytes;
         int valLength;
         if (value instanceof Text) {
            Text tval = (Text)value;
            valBytes = tval.getBytes();
            valLength = tval.getLength();
         } else {
            valBytes = value.toString().getBytes(StandardCharsets.UTF_8);
            valLength = valBytes.length;
         }

         ByteBuffer buf = ByteBuffer.allocate(keyLength + sep.length + valLength);
         buf.put(keyBytes, 0, keyLength);
         buf.put(sep);
         buf.put(valBytes, 0, valLength);
         ((Buffer)buf).rewind();
         return buf;
      }
   }
}
