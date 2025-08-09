package org.apache.avro.util;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

public class NonCopyingByteArrayOutputStream extends ByteArrayOutputStream {
   public NonCopyingByteArrayOutputStream(int size) {
      super(size);
   }

   public ByteBuffer asByteBuffer() {
      return ByteBuffer.wrap(super.buf, 0, super.count);
   }
}
