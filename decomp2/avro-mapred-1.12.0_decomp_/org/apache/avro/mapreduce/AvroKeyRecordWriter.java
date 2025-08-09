package org.apache.avro.mapreduce;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.avro.Schema;
import org.apache.avro.file.CodecFactory;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.mapred.AvroKey;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

public class AvroKeyRecordWriter extends RecordWriter implements Syncable {
   private final DataFileWriter mAvroFileWriter;

   public AvroKeyRecordWriter(Schema writerSchema, GenericData dataModel, CodecFactory compressionCodec, OutputStream outputStream, int syncInterval) throws IOException {
      this.mAvroFileWriter = new DataFileWriter(dataModel.createDatumWriter(writerSchema));
      this.mAvroFileWriter.setCodec(compressionCodec);
      this.mAvroFileWriter.setSyncInterval(syncInterval);
      this.mAvroFileWriter.create(writerSchema, outputStream);
   }

   public AvroKeyRecordWriter(Schema writerSchema, GenericData dataModel, CodecFactory compressionCodec, OutputStream outputStream) throws IOException {
      this(writerSchema, dataModel, compressionCodec, outputStream, 64000);
   }

   public void write(AvroKey record, NullWritable ignore) throws IOException {
      this.mAvroFileWriter.append(record.datum());
   }

   public void close(TaskAttemptContext context) throws IOException {
      this.mAvroFileWriter.close();
   }

   public long sync() throws IOException {
      return this.mAvroFileWriter.sync();
   }
}
