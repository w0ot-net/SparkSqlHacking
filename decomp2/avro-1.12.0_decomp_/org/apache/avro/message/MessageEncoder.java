package org.apache.avro.message;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public interface MessageEncoder {
   ByteBuffer encode(Object datum) throws IOException;

   void encode(Object datum, OutputStream stream) throws IOException;
}
