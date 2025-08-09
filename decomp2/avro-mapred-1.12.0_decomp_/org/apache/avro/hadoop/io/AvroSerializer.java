package org.apache.avro.hadoop.io;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.avro.Schema;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.mapred.AvroWrapper;
import org.apache.avro.reflect.ReflectDatumWriter;
import org.apache.hadoop.io.serializer.Serializer;

public class AvroSerializer implements Serializer {
   private static final EncoderFactory ENCODER_FACTORY = new EncoderFactory();
   private final Schema mWriterSchema;
   private final DatumWriter mAvroDatumWriter;
   private BinaryEncoder mAvroEncoder;
   private OutputStream mOutputStream;

   public AvroSerializer(Schema writerSchema) {
      if (null == writerSchema) {
         throw new IllegalArgumentException("Writer schema may not be null");
      } else {
         this.mWriterSchema = writerSchema;
         this.mAvroDatumWriter = new ReflectDatumWriter(writerSchema);
      }
   }

   public AvroSerializer(Schema writerSchema, DatumWriter datumWriter) {
      if (null == writerSchema) {
         throw new IllegalArgumentException("Writer schema may not be null");
      } else {
         this.mWriterSchema = writerSchema;
         this.mAvroDatumWriter = datumWriter;
      }
   }

   public Schema getWriterSchema() {
      return this.mWriterSchema;
   }

   public void open(OutputStream outputStream) throws IOException {
      this.mOutputStream = outputStream;
      this.mAvroEncoder = ENCODER_FACTORY.binaryEncoder(outputStream, this.mAvroEncoder);
   }

   public void serialize(AvroWrapper avroWrapper) throws IOException {
      this.mAvroDatumWriter.write(avroWrapper.datum(), this.mAvroEncoder);
      this.mAvroEncoder.flush();
   }

   public void close() throws IOException {
      this.mOutputStream.close();
   }
}
