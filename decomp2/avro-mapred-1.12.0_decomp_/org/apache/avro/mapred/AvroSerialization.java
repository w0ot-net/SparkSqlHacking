package org.apache.avro.mapred;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.EncoderFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.io.serializer.Deserializer;
import org.apache.hadoop.io.serializer.Serialization;
import org.apache.hadoop.io.serializer.Serializer;

public class AvroSerialization extends Configured implements Serialization {
   private static final DecoderFactory FACTORY = DecoderFactory.get();

   public boolean accept(Class c) {
      return AvroWrapper.class.isAssignableFrom(c);
   }

   public Deserializer getDeserializer(Class c) {
      Configuration conf = this.getConf();
      boolean isKey = AvroKey.class.isAssignableFrom(c);
      Schema schema = isKey ? Pair.getKeySchema(AvroJob.getMapOutputSchema(conf)) : Pair.getValueSchema(AvroJob.getMapOutputSchema(conf));
      GenericData dataModel = AvroJob.createMapOutputDataModel(conf);
      DatumReader<T> datumReader = dataModel.createDatumReader(schema);
      return new AvroWrapperDeserializer(datumReader, isKey);
   }

   public Serializer getSerializer(Class c) {
      boolean isFinalOutput = c.equals(AvroWrapper.class);
      Configuration conf = this.getConf();
      Schema schema = isFinalOutput ? AvroJob.getOutputSchema(conf) : (AvroKey.class.isAssignableFrom(c) ? Pair.getKeySchema(AvroJob.getMapOutputSchema(conf)) : Pair.getValueSchema(AvroJob.getMapOutputSchema(conf)));
      GenericData dataModel = AvroJob.createDataModel(conf);
      return new AvroWrapperSerializer(dataModel.createDatumWriter(schema));
   }

   private class AvroWrapperDeserializer implements Deserializer {
      private DatumReader reader;
      private BinaryDecoder decoder;
      private boolean isKey;

      public AvroWrapperDeserializer(DatumReader reader, boolean isKey) {
         this.reader = reader;
         this.isKey = isKey;
      }

      public void open(InputStream in) {
         this.decoder = AvroSerialization.FACTORY.directBinaryDecoder(in, this.decoder);
      }

      public AvroWrapper deserialize(AvroWrapper wrapper) throws IOException {
         T datum = (T)this.reader.read(wrapper == null ? null : wrapper.datum(), this.decoder);
         if (wrapper == null) {
            wrapper = (AvroWrapper<T>)(this.isKey ? new AvroKey(datum) : new AvroValue(datum));
         } else {
            wrapper.datum(datum);
         }

         return wrapper;
      }

      public void close() throws IOException {
         this.decoder.inputStream().close();
      }
   }

   private class AvroWrapperSerializer implements Serializer {
      private DatumWriter writer;
      private OutputStream out;
      private BinaryEncoder encoder;

      public AvroWrapperSerializer(DatumWriter writer) {
         this.writer = writer;
      }

      public void open(OutputStream out) {
         this.out = out;
         this.encoder = (new EncoderFactory()).binaryEncoder(out, (BinaryEncoder)null);
      }

      public void serialize(AvroWrapper wrapper) throws IOException {
         this.writer.write(wrapper.datum(), this.encoder);
         this.encoder.flush();
      }

      public void close() throws IOException {
         this.out.close();
      }
   }
}
