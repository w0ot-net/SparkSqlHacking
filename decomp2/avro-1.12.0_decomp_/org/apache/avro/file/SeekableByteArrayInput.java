package org.apache.avro.file;

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.IOException;

public class SeekableByteArrayInput extends ByteArrayInputStream implements SeekableInput {
   public SeekableByteArrayInput(byte[] data) {
      super(data);
   }

   public long length() throws IOException {
      return (long)this.count;
   }

   public void seek(long p) throws IOException {
      if (p >= (long)this.count) {
         throw new EOFException();
      } else {
         if (p >= 0L) {
            this.pos = (int)p;
         }

      }
   }

   public long tell() throws IOException {
      return (long)this.pos;
   }
}
