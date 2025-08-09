package org.apache.derby.iapi.services.classfile;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.derby.iapi.services.io.AccessibleByteArrayOutputStream;

public final class ClassFormatOutput extends DataOutputStream {
   public ClassFormatOutput() {
      this(512);
   }

   public ClassFormatOutput(int var1) {
      this(new AccessibleByteArrayOutputStream(var1));
   }

   public ClassFormatOutput(OutputStream var1) {
      super(var1);
   }

   public void putU1(int var1) throws IOException {
      if (var1 > 255) {
         limit("U1", 255, var1);
      }

      this.write(var1);
   }

   public void putU2(int var1) throws IOException {
      this.putU2("U2", var1);
   }

   public void putU2(String var1, int var2) throws IOException {
      if (var2 > 65535) {
         limit(var1, 65535, var2);
      }

      this.write(var2 >> 8);
      this.write(var2);
   }

   public void putU4(int var1) throws IOException {
      this.writeInt(var1);
   }

   public void writeTo(OutputStream var1) throws IOException {
      ((AccessibleByteArrayOutputStream)this.out).writeTo(var1);
   }

   public byte[] getData() {
      return ((AccessibleByteArrayOutputStream)this.out).getInternalByteArray();
   }

   static void limit(String var0, int var1, int var2) throws IOException {
      throw new IOException(var0 + "(" + var2 + " > " + var1 + ")");
   }
}
