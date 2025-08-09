package org.apache.derby.iapi.services.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AccessibleByteArrayOutputStream extends ByteArrayOutputStream {
   public AccessibleByteArrayOutputStream() {
   }

   public AccessibleByteArrayOutputStream(int var1) {
      super(var1);
   }

   public byte[] getInternalByteArray() {
      return this.buf;
   }

   public void readFrom(InputStream var1) throws IOException {
      byte[] var2 = new byte[8192];

      while(true) {
         int var3 = var1.read(var2, 0, this.buf.length);
         if (var3 == -1) {
            return;
         }

         this.write(var2, 0, var3);
      }
   }

   public InputStream getInputStream() {
      return new ByteArrayInputStream(this.buf, 0, this.count);
   }

   public static InputStream copyStream(InputStream var0, int var1) throws IOException {
      AccessibleByteArrayOutputStream var2 = new AccessibleByteArrayOutputStream(var1);
      var2.readFrom(var0);
      return var2.getInputStream();
   }
}
