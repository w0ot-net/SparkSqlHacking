package org.apache.avro.mapred.tether;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.EncoderFactory;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.io.serializer.Deserializer;
import org.apache.hadoop.io.serializer.Serialization;
import org.apache.hadoop.io.serializer.Serializer;

class TetherKeySerialization extends Configured implements Serialization {
   private static final DecoderFactory FACTORY = DecoderFactory.get();

   public boolean accept(Class c) {
      return TetherData.class.isAssignableFrom(c);
   }

   public Deserializer getDeserializer(Class c) {
      return new TetherDataDeserializer();
   }

   public Serializer getSerializer(Class c) {
      return new TetherDataSerializer();
   }

   private class TetherDataDeserializer implements Deserializer {
      private BinaryDecoder decoder;

      private TetherDataDeserializer() {
      }

      public void open(InputStream in) {
         this.decoder = TetherKeySerialization.FACTORY.directBinaryDecoder(in, this.decoder);
      }

      public TetherData deserialize(TetherData datum) throws IOException {
         if (datum == null) {
            datum = new TetherData();
         }

         datum.buffer(this.decoder.readBytes(datum.buffer()));
         return datum;
      }

      public void close() throws IOException {
         this.decoder.inputStream().close();
      }
   }

   private class TetherDataSerializer implements Serializer {
      private OutputStream out;
      private BinaryEncoder encoder;

      private TetherDataSerializer() {
      }

      public void open(OutputStream out) {
         this.out = out;
         this.encoder = EncoderFactory.get().directBinaryEncoder(out, this.encoder);
      }

      public void serialize(TetherData datum) throws IOException {
         this.encoder.writeBytes(datum.buffer());
         this.encoder.flush();
      }

      public void close() throws IOException {
         this.encoder.flush();
         this.out.close();
      }
   }
}
