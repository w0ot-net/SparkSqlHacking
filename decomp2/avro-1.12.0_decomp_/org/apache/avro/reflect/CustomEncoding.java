package org.apache.avro.reflect;

import java.io.IOException;
import org.apache.avro.Schema;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.Encoder;

public abstract class CustomEncoding {
   protected Schema schema;

   protected abstract void write(Object datum, Encoder out) throws IOException;

   protected abstract Object read(Object reuse, Decoder in) throws IOException;

   Object read(Decoder in) throws IOException {
      return this.read((Object)null, in);
   }

   protected Schema getSchema() {
      return this.schema;
   }
}
