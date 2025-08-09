package io.jsonwebtoken.impl.io;

import io.jsonwebtoken.impl.lang.Bytes;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public final class BytesInputStream extends ByteArrayInputStream {
   BytesInputStream(byte[] buf) {
      super(Bytes.isEmpty(buf) ? Bytes.EMPTY : buf);
   }

   public byte[] getBytes() {
      return this.buf;
   }

   public void close() throws IOException {
      this.reset();
   }
}
