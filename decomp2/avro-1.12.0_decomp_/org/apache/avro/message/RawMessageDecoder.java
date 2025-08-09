package org.apache.avro.message;

import java.io.IOException;
import java.io.InputStream;
import org.apache.avro.AvroRuntimeException;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DecoderFactory;

public class RawMessageDecoder extends MessageDecoder.BaseDecoder {
   private static final ThreadLocal DECODER = new ThreadLocal();
   private final DatumReader reader;

   public RawMessageDecoder(GenericData model, Schema schema) {
      this(model, schema, schema);
   }

   public RawMessageDecoder(GenericData model, Schema writeSchema, Schema readSchema) {
      this.reader = model.createDatumReader(writeSchema, readSchema);
   }

   public Object decode(InputStream stream, Object reuse) {
      BinaryDecoder decoder = DecoderFactory.get().directBinaryDecoder(stream, (BinaryDecoder)DECODER.get());
      DECODER.set(decoder);

      try {
         return this.reader.read(reuse, decoder);
      } catch (IOException e) {
         throw new AvroRuntimeException("Decoding datum failed", e);
      }
   }
}
