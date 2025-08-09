package org.apache.avro.hadoop.io;

import org.apache.avro.Schema;
import org.apache.avro.io.DatumReader;
import org.apache.avro.mapred.AvroKey;
import org.apache.avro.mapred.AvroWrapper;

public class AvroKeyDeserializer extends AvroDeserializer {
   public AvroKeyDeserializer(Schema writerSchema, Schema readerSchema, ClassLoader classLoader) {
      super(writerSchema, readerSchema, classLoader);
   }

   public AvroKeyDeserializer(Schema writerSchema, Schema readerSchema, DatumReader datumReader) {
      super(writerSchema, readerSchema, datumReader);
   }

   protected AvroWrapper createAvroWrapper() {
      return new AvroKey((Object)null);
   }
}
