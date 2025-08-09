package org.apache.avro.mapreduce;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.avro.Schema;
import org.apache.avro.file.CodecFactory;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.hadoop.io.AvroDatumConverter;
import org.apache.avro.hadoop.io.AvroKeyValue;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

public class AvroKeyValueRecordWriter extends RecordWriter implements Syncable {
   private final DataFileWriter mAvroFileWriter;
   private final Schema mKeyValuePairSchema;
   private final AvroKeyValue mOutputRecord;
   private final AvroDatumConverter mKeyConverter;
   private final AvroDatumConverter mValueConverter;

   public AvroKeyValueRecordWriter(AvroDatumConverter keyConverter, AvroDatumConverter valueConverter, GenericData dataModel, CodecFactory compressionCodec, OutputStream outputStream, int syncInterval) throws IOException {
      this.mKeyValuePairSchema = AvroKeyValue.getSchema(keyConverter.getWriterSchema(), valueConverter.getWriterSchema());
      this.mAvroFileWriter = new DataFileWriter(dataModel.createDatumWriter(this.mKeyValuePairSchema));
      this.mAvroFileWriter.setCodec(compressionCodec);
      this.mAvroFileWriter.setSyncInterval(syncInterval);
      this.mAvroFileWriter.create(this.mKeyValuePairSchema, outputStream);
      this.mKeyConverter = keyConverter;
      this.mValueConverter = valueConverter;
      this.mOutputRecord = new AvroKeyValue(new GenericData.Record(this.mKeyValuePairSchema));
   }

   public AvroKeyValueRecordWriter(AvroDatumConverter keyConverter, AvroDatumConverter valueConverter, GenericData dataModel, CodecFactory compressionCodec, OutputStream outputStream) throws IOException {
      this(keyConverter, valueConverter, dataModel, compressionCodec, outputStream, 64000);
   }

   public Schema getWriterSchema() {
      return this.mKeyValuePairSchema;
   }

   public void write(Object key, Object value) throws IOException {
      this.mOutputRecord.setKey(this.mKeyConverter.convert(key));
      this.mOutputRecord.setValue(this.mValueConverter.convert(value));
      this.mAvroFileWriter.append(this.mOutputRecord.get());
   }

   public void close(TaskAttemptContext context) throws IOException {
      this.mAvroFileWriter.close();
   }

   public long sync() throws IOException {
      return this.mAvroFileWriter.sync();
   }
}
