package org.apache.avro.mapreduce;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.avro.Schema;
import org.apache.avro.file.CodecFactory;
import org.apache.avro.generic.GenericData;
import org.apache.avro.hadoop.io.AvroSerialization;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

public class AvroKeyOutputFormat extends AvroOutputFormatBase {
   private final RecordWriterFactory mRecordWriterFactory;

   public AvroKeyOutputFormat() {
      this(new RecordWriterFactory());
   }

   protected AvroKeyOutputFormat(RecordWriterFactory recordWriterFactory) {
      this.mRecordWriterFactory = recordWriterFactory;
   }

   public RecordWriter getRecordWriter(TaskAttemptContext context) throws IOException {
      Configuration conf = context.getConfiguration();
      Schema writerSchema = AvroJob.getOutputKeySchema(conf);
      boolean isMapOnly = context.getNumReduceTasks() == 0;
      if (isMapOnly) {
         Schema mapOutputSchema = AvroJob.getMapOutputKeySchema(conf);
         if (mapOutputSchema != null) {
            writerSchema = mapOutputSchema;
         }
      }

      if (null == writerSchema) {
         throw new IOException("AvroKeyOutputFormat requires an output schema. Use AvroJob.setOutputKeySchema().");
      } else {
         GenericData dataModel = AvroSerialization.createDataModel(conf);
         OutputStream out = this.getAvroFileOutputStream(context);

         try {
            return this.mRecordWriterFactory.create(writerSchema, dataModel, getCompressionCodec(context), out, getSyncInterval(context));
         } catch (IOException e) {
            out.close();
            throw e;
         }
      }
   }

   protected static class RecordWriterFactory {
      protected RecordWriter create(Schema writerSchema, GenericData dataModel, CodecFactory compressionCodec, OutputStream outputStream, int syncInterval) throws IOException {
         return new AvroKeyRecordWriter(writerSchema, dataModel, compressionCodec, outputStream, syncInterval);
      }
   }
}
