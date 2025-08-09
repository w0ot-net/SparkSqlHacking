package org.apache.avro.specific;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;

class ExternalizableInput extends InputStream {
   private final ObjectInput in;

   public ExternalizableInput(ObjectInput in) {
      this.in = in;
   }

   public int available() throws IOException {
      return this.in.available();
   }

   public void close() throws IOException {
      this.in.close();
   }

   public boolean markSupported() {
      return false;
   }

   public int read() throws IOException {
      return this.in.read();
   }

   public int read(byte[] b) throws IOException {
      return this.in.read(b);
   }

   public int read(byte[] b, int offset, int len) throws IOException {
      return this.in.read(b, offset, len);
   }

   public long skip(long n) throws IOException {
      return this.in.skip(n);
   }
}
