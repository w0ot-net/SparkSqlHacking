package org.apache.avro.specific;

import java.io.IOException;
import java.io.ObjectOutput;
import java.io.OutputStream;

class ExternalizableOutput extends OutputStream {
   private final ObjectOutput out;

   public ExternalizableOutput(ObjectOutput out) {
      this.out = out;
   }

   public void flush() throws IOException {
      this.out.flush();
   }

   public void close() throws IOException {
      this.out.close();
   }

   public void write(int c) throws IOException {
      this.out.write(c);
   }

   public void write(byte[] b) throws IOException {
      this.out.write(b);
   }

   public void write(byte[] b, int offset, int len) throws IOException {
      this.out.write(b, offset, len);
   }
}
