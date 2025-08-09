package org.apache.avro.hadoop.io;

import org.apache.avro.Schema;
import org.apache.avro.io.DatumReader;
import org.apache.avro.mapred.AvroValue;
import org.apache.avro.mapred.AvroWrapper;

public class AvroValueDeserializer extends AvroDeserializer {
   public AvroValueDeserializer(Schema writerSchema, Schema readerSchema, ClassLoader classLoader) {
      super(writerSchema, readerSchema, classLoader);
   }

   public AvroValueDeserializer(Schema writerSchema, Schema readerSchema, DatumReader datumReader) {
      super(writerSchema, readerSchema, datumReader);
   }

   protected AvroWrapper createAvroWrapper() {
      return new AvroValue((Object)null);
   }
}
