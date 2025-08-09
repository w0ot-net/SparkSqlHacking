package org.apache.avro.io;

import java.io.IOException;
import org.apache.avro.Schema;

public interface DatumReader {
   void setSchema(Schema schema);

   Object read(Object reuse, Decoder in) throws IOException;
}
