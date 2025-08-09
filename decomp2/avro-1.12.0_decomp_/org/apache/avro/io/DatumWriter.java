package org.apache.avro.io;

import java.io.IOException;
import org.apache.avro.Schema;

public interface DatumWriter {
   void setSchema(Schema schema);

   void write(Object datum, Encoder out) throws IOException;
}
