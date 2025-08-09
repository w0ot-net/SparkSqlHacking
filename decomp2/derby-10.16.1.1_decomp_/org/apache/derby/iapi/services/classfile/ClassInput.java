package org.apache.derby.iapi.services.classfile;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

class ClassInput extends DataInputStream {
   ClassInput(InputStream var1) {
      super(var1);
   }

   int getU1() throws IOException {
      return this.readUnsignedByte();
   }

   int getU2() throws IOException {
      return this.readUnsignedShort();
   }

   int getU4() throws IOException {
      return this.readInt();
   }

   byte[] getU1Array(int var1) throws IOException {
      byte[] var2 = new byte[var1];
      this.readFully(var2);
      return var2;
   }
}
