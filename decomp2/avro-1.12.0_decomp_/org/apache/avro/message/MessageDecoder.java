package org.apache.avro.message;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import org.apache.avro.util.ReusableByteArrayInputStream;
import org.apache.avro.util.ReusableByteBufferInputStream;
import org.apache.avro.util.internal.ThreadLocalWithInitial;

public interface MessageDecoder {
   Object decode(InputStream stream) throws IOException;

   Object decode(InputStream stream, Object reuse) throws IOException;

   Object decode(ByteBuffer encoded) throws IOException;

   Object decode(ByteBuffer encoded, Object reuse) throws IOException;

   Object decode(byte[] encoded) throws IOException;

   Object decode(byte[] encoded, Object reuse) throws IOException;

   public abstract static class BaseDecoder implements MessageDecoder {
      private static final ThreadLocal BYTE_ARRAY_IN = ThreadLocalWithInitial.of(ReusableByteArrayInputStream::new);
      private static final ThreadLocal BYTE_BUFFER_IN = ThreadLocalWithInitial.of(ReusableByteBufferInputStream::new);

      public Object decode(InputStream stream) throws IOException {
         return this.decode((InputStream)stream, (Object)null);
      }

      public Object decode(ByteBuffer encoded) throws IOException {
         return this.decode((ByteBuffer)encoded, (Object)null);
      }

      public Object decode(byte[] encoded) throws IOException {
         return this.decode((byte[])encoded, (Object)null);
      }

      public Object decode(ByteBuffer encoded, Object reuse) throws IOException {
         ReusableByteBufferInputStream in = (ReusableByteBufferInputStream)BYTE_BUFFER_IN.get();
         in.setByteBuffer(encoded);
         return this.decode((InputStream)in, reuse);
      }

      public Object decode(byte[] encoded, Object reuse) throws IOException {
         ReusableByteArrayInputStream in = (ReusableByteArrayInputStream)BYTE_ARRAY_IN.get();
         in.setByteArray(encoded, 0, encoded.length);
         return this.decode((InputStream)in, reuse);
      }
   }
}
