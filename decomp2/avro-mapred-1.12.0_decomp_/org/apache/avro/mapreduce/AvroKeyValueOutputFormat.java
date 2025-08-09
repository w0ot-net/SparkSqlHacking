package org.apache.avro.mapreduce;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.avro.generic.GenericData;
import org.apache.avro.hadoop.io.AvroDatumConverter;
import org.apache.avro.hadoop.io.AvroDatumConverterFactory;
import org.apache.avro.hadoop.io.AvroSerialization;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

public class AvroKeyValueOutputFormat extends AvroOutputFormatBase {
   public RecordWriter getRecordWriter(TaskAttemptContext context) throws IOException {
      Configuration conf = context.getConfiguration();
      AvroDatumConverterFactory converterFactory = new AvroDatumConverterFactory(conf);
      AvroDatumConverter<K, ?> keyConverter = converterFactory.create(context.getOutputKeyClass());
      AvroDatumConverter<V, ?> valueConverter = converterFactory.create(context.getOutputValueClass());
      GenericData dataModel = AvroSerialization.createDataModel(conf);
      OutputStream out = this.getAvroFileOutputStream(context);

      try {
         return new AvroKeyValueRecordWriter(keyConverter, valueConverter, dataModel, getCompressionCodec(context), out, getSyncInterval(context));
      } catch (IOException e) {
         out.close();
         throw e;
      }
   }
}
