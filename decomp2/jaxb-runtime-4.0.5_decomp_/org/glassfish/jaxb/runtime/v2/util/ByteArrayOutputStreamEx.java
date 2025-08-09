package org.glassfish.jaxb.runtime.v2.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.Base64Data;

public final class ByteArrayOutputStreamEx extends ByteArrayOutputStream {
   public ByteArrayOutputStreamEx() {
   }

   public ByteArrayOutputStreamEx(int size) {
      super(size);
   }

   public void set(Base64Data dt, String mimeType) {
      dt.set(this.buf, this.count, mimeType);
   }

   public byte[] getBuffer() {
      return this.buf;
   }

   public void readFrom(InputStream is) throws IOException {
      while(true) {
         if (this.count == this.buf.length) {
            byte[] data = new byte[this.buf.length * 2];
            System.arraycopy(this.buf, 0, data, 0, this.buf.length);
            this.buf = data;
         }

         int sz = is.read(this.buf, this.count, this.buf.length - this.count);
         if (sz < 0) {
            return;
         }

         this.count += sz;
      }
   }
}
